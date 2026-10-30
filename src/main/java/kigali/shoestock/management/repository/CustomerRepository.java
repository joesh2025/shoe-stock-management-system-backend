package kigali.shoestock.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID>{

    List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
    boolean existsById(UUID id);

    @Query("SELECT DISTINCT s.customer FROM ShoeSale s WHERE s.shopAttendant.id = :shopAttendantId")
    List<Customer> findCustomersOfShopAttendant(UUID shopAttendantId);

}
