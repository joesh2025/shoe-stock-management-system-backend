package kigali.shoestock.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

}
