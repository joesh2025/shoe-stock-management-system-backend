package kigali.shoestock.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.SalesCounter;
import kigali.shoestock.management.domain.ShopAttendant;

@Repository
public interface ShopAttendantRepository extends JpaRepository<ShopAttendant, UUID>{

    @Query("SELECT sa FROM ShopAttendant sa JOIN sa.shoeLines sl WHERE LOWER(sl.name) = LOWER(:name)")
    List<ShopAttendant> findShopAttendantsByShoeLineName(String name);

    boolean existsBySalesCounter(SalesCounter salesCounter);

}
