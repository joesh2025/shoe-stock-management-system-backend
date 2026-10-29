package kigali.shoestock.management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.domain.ShoeSale;
import kigali.shoestock.management.domain.StockMovement;
import kigali.shoestock.management.repository.ShoeSaleRepository;
import kigali.shoestock.management.repository.StockMovementRepository;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepo;

    @Autowired
    private ShoeSaleRepository shoeSaleRepo;

    public String saveStockMovement(UUID shoeSaleId, StockMovement stockMovement){

        Optional<ShoeSale> found = shoeSaleRepo.findById(shoeSaleId);

        if(found.isEmpty()){
            return "The shoe sale with that id does not exist";
        }

        ShoeSale shoeSale = found.get();

        if(shoeSale.getStatus() != SaleStatus.SOLD){
            return "Stock movements are only for SOLD shoe sales";
        }

        stockMovement.setShoeSale(shoeSale);
        stockMovement.setMovedDate(LocalDate.now());

        stockMovementRepo.save(stockMovement);

        return "Stock movement saved successfully";
    }

    public List<StockMovement> getStockMovementsByCustomerCode(String customerCode){
        return stockMovementRepo.getStockMovementsOfCustomer(customerCode);
    }

    public String deleteStockMovement(UUID id){

        Optional<StockMovement> found = stockMovementRepo.findById(id);

        if(found.isEmpty()){
            return "The stock movement with that id does not exist";
        }

        stockMovementRepo.deleteById(id);

        return "Stock movement deleted successfully";
    }
}
