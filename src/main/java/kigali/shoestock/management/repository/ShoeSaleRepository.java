package kigali.shoestock.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.domain.ShoeSale;

@Repository
public interface ShoeSaleRepository extends JpaRepository<ShoeSale, UUID>{

    List<ShoeSale> findByStatusOrderBySaleDateAsc(SaleStatus status);

    List<ShoeSale> findBySaleDateBetweenOrderBySaleDate(LocalDate start, LocalDate end);

}
