package kigali.shoestock.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.SalesCounter;

@Repository
public interface SalesCounterRepository extends JpaRepository<SalesCounter, UUID>{

    @Query("SELECT sc FROM SalesCounter sc WHERE NOT EXISTS (SELECT sa FROM ShopAttendant sa WHERE sa.salesCounter = sc) ORDER BY sc.counterNumber")
    List<SalesCounter> findFreeSalesCounters();

    SalesCounter findByCounterNumber(int counterNumber);

}
