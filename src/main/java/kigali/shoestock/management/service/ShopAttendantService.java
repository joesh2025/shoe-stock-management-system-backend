package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.ShopAttendant;
import kigali.shoestock.management.repository.ShopAttendantRepository;

@Service
public class ShopAttendantService {

    @Autowired
    private ShopAttendantRepository shopAttendantRepo;

    public String saveShopAttendant(ShopAttendant shopAttendant){

        shopAttendantRepo.save(shopAttendant);

        return "Shop attendant saved successfully";
    }

    public List<ShopAttendant> getAllShopAttendants(){
        return shopAttendantRepo.findAll();
    }

    public Optional<ShopAttendant> getShopAttendantById(UUID id){
        return shopAttendantRepo.findById(id);
    }

    public String updateShopAttendant(UUID id, ShopAttendant shopAttendant){

        Optional<ShopAttendant> found = shopAttendantRepo.findById(id);

        if(found.isEmpty()){
            return "The shop attendant with that id does not exist";
        }

        ShopAttendant existing = found.get();
        existing.setFirstName(shopAttendant.getFirstName());
        existing.setLastName(shopAttendant.getLastName());
        existing.setPhone(shopAttendant.getPhone());
        existing.setSalesCounter(shopAttendant.getSalesCounter());
        existing.setShoeLines(shopAttendant.getShoeLines());

        shopAttendantRepo.save(existing);

        return "Shop attendant updated successfully";
    }

    public String deleteShopAttendant(UUID id){

        Optional<ShopAttendant> found = shopAttendantRepo.findById(id);

        if(found.isEmpty()){
            return "The shop attendant with that id does not exist";
        }

        shopAttendantRepo.deleteById(id);

        return "Shop attendant deleted successfully";
    }
}
