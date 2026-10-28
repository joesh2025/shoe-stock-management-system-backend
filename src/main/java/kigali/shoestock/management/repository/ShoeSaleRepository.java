package kigali.shoestock.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.ShoeSale;

@Repository
public interface ShoeSaleRepository extends JpaRepository<ShoeSale, UUID>{

}
