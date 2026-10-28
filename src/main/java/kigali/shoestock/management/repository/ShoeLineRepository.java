package kigali.shoestock.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.ShoeLine;

@Repository
public interface ShoeLineRepository extends JpaRepository<ShoeLine, UUID>{

}
