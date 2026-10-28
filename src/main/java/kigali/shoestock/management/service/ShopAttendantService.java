package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.SalesCounter;
import kigali.shoestock.management.domain.ShoeLine;
import kigali.shoestock.management.domain.ShopAttendant;
import kigali.shoestock.management.repository.SalesCounterRepository;
import kigali.shoestock.management.repository.ShoeLineRepository;
import kigali.shoestock.management.repository.ShopAttendantRepository;

@Service
public class ShopAttendantService {

    @Autowired
    private ShopAttendantRepository shopAttendantRepo;

    @Autowired
    private SalesCounterRepository salesCounterRepo;

    @Autowired
    private ShoeLineRepository shoeLineRepo;

    public String saveShopAttendant(ShopAttendant shopAttendant){

        shopAttendantRepo.save(shopAttendant);

        return "Shop attendant saved successfully";
    }

    public List<ShopAttendant> getAllShopAttendants(){
        return shopAttendantRepo.findAll();
    }

    public List<ShopAttendant> getShopAttendantsByShoeLine(String name){
        return shopAttendantRepo.findShopAttendantsByShoeLineName(name);
    }

    public String addShoeLine(UUID shopAttendantId, UUID shoeLineId){

        Optional<ShopAttendant> found = shopAttendantRepo.findById(shopAttendantId);

        if(found.isEmpty()){
            return "The shop attendant with that id does not exist";
        }

        Optional<ShoeLine> shoeLine = shoeLineRepo.findById(shoeLineId);

        if(shoeLine.isEmpty()){
            return "That shoe line does not exist";
        }

        ShopAttendant shopAttendant = found.get();

        if(shopAttendant.getShoeLines().contains(shoeLine.get())){
            return "The shop attendant already has that shoe line";
        }

        shopAttendant.getShoeLines().add(shoeLine.get());
        shopAttendantRepo.save(shopAttendant);

        return "Shoe line added to the shop attendant";
    }

    public String changeSalesCounter(UUID shopAttendantId, int counterNumber){

        Optional<ShopAttendant> found = shopAttendantRepo.findById(shopAttendantId);

        if(found.isEmpty()){
            return "The shop attendant with that id does not exist";
        }

        SalesCounter salesCounter = salesCounterRepo.findByCounterNumber(counterNumber);

        if(salesCounter == null){
            return "The sales counter with that counterNumber does not exist";
        }

        ShopAttendant shopAttendant = found.get();

        boolean taken = false;

        if(shopAttendant.getSalesCounter() == null || !shopAttendant.getSalesCounter().getId().equals(salesCounter.getId())){
            taken = shopAttendantRepo.existsBySalesCounter(salesCounter);
        }

        if(taken){
            return "That sales counter is taken by another shop attendant";
        }

        shopAttendant.setSalesCounter(salesCounter);
        shopAttendantRepo.save(shopAttendant);

        return "Sales counter changed successfully";
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
