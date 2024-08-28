package com.rentcar.api.specification;

import com.rentcar.api.model.Vehicle;
import com.rentcar.api.model.VehicleType;
import org.springframework.data.jpa.domain.Specification;

public class VehicleSpecification {
    public static Specification<Vehicle> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Vehicle> vehicleTypeEquals(VehicleType vehicleType) {
        return (root, query, criteriaBuilder) ->
                vehicleType == null ? null : criteriaBuilder.equal(root.get("vehicleType"), vehicleType);
    }

    public static Specification<Vehicle> chassiEquals(String chassi) {
        return (root, query, criteriaBuilder) ->
                chassi == null ? null : criteriaBuilder.equal(root.get("chassi"), chassi);
    }

    public static Specification<Vehicle> yearEquals(Integer year) {
        return (root, query, criteriaBuilder) ->
                year == null ? null : criteriaBuilder.equal(root.get("year"), year);
    }

    public static Specification<Vehicle> colorEquals(String color) {
        return (root, query, criteriaBuilder) ->
                color == null ? null : criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Vehicle> isRentedEquals(Boolean isRented) {
        return (root, query, criteriaBuilder) ->
                isRented == null ? null : criteriaBuilder.equal(root.get("isRented"), isRented);
    }
}
