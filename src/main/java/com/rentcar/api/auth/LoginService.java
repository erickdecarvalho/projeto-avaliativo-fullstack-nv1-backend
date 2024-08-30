package com.rentcar.api.auth;


import com.rentcar.api.config.JwtServiceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String logar(Login login) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(),
						login.getPassword()
						)
				);
		Usuario user = repository.findByUsername(login.getUsername()).get();
		String jwtToken = jwtService.generateToken(user);
		
		return jwtToken;
	}

	public void registerUser(RegisterRequest registerRequest) {
		if (loginRepository.existsByUsername(registerRequest.getUsername())) {
			throw new RuntimeException("Username already exists");
		}
		if (loginRepository.existsByEmail(registerRequest.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		Usuario usuario = new Usuario();
		usuario.setUsername(registerRequest.getUsername());
		usuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		usuario.setEmail(registerRequest.getEmail());
		usuario.setRole("USER");
		loginRepository.save(usuario);
	}
}
