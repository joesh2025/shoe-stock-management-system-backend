package kigali.shoestock.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.SalesCounter;

@Repository
public interface SalesCounterRepository extends JpaRepository<SalesCounter, UUID>{

}
