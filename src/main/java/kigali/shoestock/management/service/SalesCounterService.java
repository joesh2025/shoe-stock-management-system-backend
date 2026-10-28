package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.SalesCounter;
import kigali.shoestock.management.repository.SalesCounterRepository;

@Service
public class SalesCounterService {

    @Autowired
    private SalesCounterRepository salesCounterRepo;

    public String saveSalesCounter(SalesCounter salesCounter){

        salesCounterRepo.save(salesCounter);

        return "Sales counter saved successfully";
    }

    public List<SalesCounter> getAllSalesCounters(){
        return salesCounterRepo.findAll();
    }

    public Optional<SalesCounter> getSalesCounterById(UUID id){
        return salesCounterRepo.findById(id);
    }

    public String updateSalesCounter(UUID id, SalesCounter salesCounter){

        Optional<SalesCounter> found = salesCounterRepo.findById(id);

        if(found.isEmpty()){
            return "The sales counter with that id does not exist";
        }

        SalesCounter existing = found.get();
        existing.setCounterNumber(salesCounter.getCounterNumber());
        existing.setName(salesCounter.getName());

        salesCounterRepo.save(existing);

        return "Sales counter updated successfully";
    }

    public String deleteSalesCounter(UUID id){

        Optional<SalesCounter> found = salesCounterRepo.findById(id);

        if(found.isEmpty()){
            return "The sales counter with that id does not exist";
        }

        salesCounterRepo.deleteById(id);

        return "Sales counter deleted successfully";
    }
}
