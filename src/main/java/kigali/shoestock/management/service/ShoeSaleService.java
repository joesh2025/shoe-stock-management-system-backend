package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.ShoeSale;
import kigali.shoestock.management.repository.ShoeSaleRepository;

@Service
public class ShoeSaleService {

    @Autowired
    private ShoeSaleRepository shoeSaleRepo;

    public String saveShoeSale(ShoeSale shoeSale){

        shoeSaleRepo.save(shoeSale);

        return "Shoe sale saved successfully";
    }

    public List<ShoeSale> getAllShoeSales(){
        return shoeSaleRepo.findAll();
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
        existing.setStatus(shoeSale.getStatus());
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

        shoeSaleRepo.deleteById(id);

        return "Shoe sale deleted successfully";
    }
}
