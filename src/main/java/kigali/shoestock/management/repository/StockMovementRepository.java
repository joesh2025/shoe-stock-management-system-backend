package kigali.shoestock.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.ShoeSale;
import kigali.shoestock.management.domain.StockMovement;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, UUID>{

    @Query("SELECT d FROM StockMovement d WHERE d.shoeSale.customer.customerCode = :customerCode ORDER BY d.movedDate DESC")
    List<StockMovement> getStockMovementsOfCustomer(@Param("customerCode") String customerCode);

    boolean existsByShoeSale(ShoeSale shoeSale);

}
