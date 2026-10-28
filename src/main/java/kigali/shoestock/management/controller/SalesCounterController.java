package kigali.shoestock.management.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kigali.shoestock.management.domain.SalesCounter;
import kigali.shoestock.management.service.SalesCounterService;

@RestController
@RequestMapping (value="/api/sales-counters")
public class SalesCounterController {

    @Autowired
    private SalesCounterService salesCounterServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveSalesCounter(@RequestBody SalesCounter salesCounter){

        String returnedMessage = salesCounterServe.saveSalesCounter(salesCounter);

        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping (value="/all")
    public ResponseEntity<?> getAllSalesCounters(){
        return new ResponseEntity<>(salesCounterServe.getAllSalesCounters(), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getSalesCounterById(@PathVariable UUID id){

        Optional<SalesCounter> found = salesCounterServe.getSalesCounterById(id);

        if(found.isPresent()){
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The sales counter with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updateSalesCounter(@PathVariable UUID id, @RequestBody SalesCounter salesCounter){

        String returnedMessage = salesCounterServe.updateSalesCounter(id, salesCounter);

        if(returnedMessage.equalsIgnoreCase("Sales counter updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteSalesCounter(@PathVariable UUID id){

        String returnedMessage = salesCounterServe.deleteSalesCounter(id);

        if(returnedMessage.equalsIgnoreCase("Sales counter deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
