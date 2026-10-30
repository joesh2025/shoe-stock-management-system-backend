package kigali.shoestock.management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.domain.ShoeSale;

@Repository
public interface ShoeSaleRepository extends JpaRepository<ShoeSale, UUID>{

    List<ShoeSale> findByStatusOrderBySaleDateAsc(SaleStatus status);

    List<ShoeSale> findBySaleDateBetweenOrderBySaleDate(LocalDate start, LocalDate end);

    boolean existsByShopAttendantIdAndSaleDateAndStatusNot(UUID shopAttendantId, LocalDate saleDate, SaleStatus status);

    long countBySaleDate(LocalDate saleDate);

    long countByStatus(SaleStatus status);

    @Query("SELECT e.status, COUNT(e) FROM ShoeSale e GROUP BY e.status")
    List<Object[]> getShoeSalesPerStatus();

    @Modifying
    @Query("UPDATE ShoeSale e SET e.status = kigali.shoestock.management.domain.SaleStatus.CANCELLED "
        + "WHERE e.shopAttendant.id = :shopAttendantId AND e.saleDate = :saleDate "
        + "AND e.status <> kigali.shoestock.management.domain.SaleStatus.SOLD")
    int cancelShoeSalesOnDate(@Param("shopAttendantId") UUID shopAttendantId, @Param("saleDate") LocalDate saleDate);

}
