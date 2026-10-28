package kigali.shoestock.management.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.ShopAttendant;

@Repository
public interface ShopAttendantRepository extends JpaRepository<ShopAttendant, UUID>{

}
