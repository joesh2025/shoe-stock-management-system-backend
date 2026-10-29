package kigali.shoestock.management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.domain.ShoeSale;
import kigali.shoestock.management.repository.ShoeSaleRepository;
import kigali.shoestock.management.repository.StockMovementRepository;

@Service
public class ShoeSaleService {

    @Autowired
    private ShoeSaleRepository shoeSaleRepo;

    @Autowired
    private StockMovementRepository stockMovementRepo;

    public String saveShoeSale(ShoeSale shoeSale){

        if(shoeSale.getSaleDate().isBefore(LocalDate.now())){
            return "Shoe sale date cannot be in the past";
        }

        if(shoeSaleRepo.existsByShopAttendantIdAndSaleDateAndStatusNot(shoeSale.getShopAttendant().getId(), shoeSale.getSaleDate(), SaleStatus.CANCELLED)){
            return "Shop attendant already has a sale on that date";
        }

        shoeSale.setStatus(SaleStatus.QUOTED);

        shoeSaleRepo.save(shoeSale);

        return "Shoe sale saved successfully";
    }

    public String changeShoeSaleStatus(UUID id, SaleStatus status){

        Optional<ShoeSale> found = shoeSaleRepo.findById(id);

        if(found.isEmpty()){
            return "The shoe sale with that id does not exist";
        }

        ShoeSale shoeSale = found.get();
        SaleStatus current = shoeSale.getStatus();

        boolean allowed = false;

        if(current == SaleStatus.QUOTED && (status == SaleStatus.RESERVED || status == SaleStatus.CANCELLED)){
            allowed = true;
        }else if(current == SaleStatus.RESERVED && (status == SaleStatus.SOLD || status == SaleStatus.CANCELLED)){
            allowed = true;
        }

        if(!allowed){
            return "Cannot change status from " + current + " to " + status;
        }

        shoeSale.setStatus(status);
        shoeSaleRepo.save(shoeSale);

        return "Shoe sale status updated successfully";
    }

    public List<ShoeSale> getAllShoeSales(){
        return shoeSaleRepo.findAll();
    }

    public List<ShoeSale> getShoeSalesByStatus(SaleStatus status){
        return shoeSaleRepo.findByStatusOrderBySaleDateAsc(status);
    }

    public List<ShoeSale> getShoeSalesBetween(LocalDate start, LocalDate end){
        return shoeSaleRepo.findBySaleDateBetweenOrderBySaleDate(start, end);
    }

    public Optional<ShoeSale> getShoeSaleById(UUID id){
        return shoeSaleRepo.findById(id);
    }

    public String updateShoeSale(UUID id, ShoeSale shoeSale){

        Optional<ShoeSale> found = shoeSaleRepo.findById(id);

        if(found.isEmpty()){
            return "The shoe sale with that id does not exist";
        }

        ShoeSale existing = found.get();
        existing.setSaleDate(shoeSale.getSaleDate());
        existing.setShoeName(shoeSale.getShoeName());
        existing.setSize(shoeSale.getSize());
        existing.setCustomer(shoeSale.getCustomer());
        existing.setShopAttendant(shoeSale.getShopAttendant());

        shoeSaleRepo.save(existing);

        return "Shoe sale updated successfully";
    }

    public String deleteShoeSale(UUID id){

        Optional<ShoeSale> found = shoeSaleRepo.findById(id);

        if(found.isEmpty()){
            return "The shoe sale with that id does not exist";
        }

        if(stockMovementRepo.existsByShoeSale(found.get())){
            return "That shoe sale has stock movements, delete them first";
        }

        shoeSaleRepo.deleteById(id);

        return "Shoe sale deleted successfully";
    }
}
