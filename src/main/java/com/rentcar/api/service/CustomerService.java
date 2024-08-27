package com.rentcar.api.service;

import com.rentcar.api.dto.CustomerCreateResponseDTO;
import com.rentcar.api.dto.RegisterCustomerDTO;
import com.rentcar.api.mapper.CustomerMapper;
import com.rentcar.api.model.Customer;
import com.rentcar.api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerCreateResponseDTO registerCustomer(RegisterCustomerDTO registerCustomerDTO) {
        Customer customer = CustomerMapper.registerCustomerDTOToCustomer(registerCustomerDTO);
        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.customerToCustomerCreateResponseDTO(savedCustomer);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id).get();
    }

    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }
}
