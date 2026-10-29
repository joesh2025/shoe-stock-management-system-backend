package kigali.shoestock.management.controller;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.domain.ShoeSale;
import kigali.shoestock.management.service.ShoeSaleService;

@RestController
@RequestMapping (value="/api/shoe-sales")
public class ShoeSaleController {

    @Autowired
    private ShoeSaleService shoeSaleServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveShoeSale(@RequestBody ShoeSale shoeSale){

        String returnedMessage = shoeSaleServe.saveShoeSale(shoeSale);

        if(returnedMessage.equalsIgnoreCase("Shoe sale saved successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equalsIgnoreCase("Shoe sale date cannot be in the past")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }
    }

    @PatchMapping (value="/{id}/status")
    public ResponseEntity<?> changeShoeSaleStatus(@PathVariable UUID id, @RequestParam SaleStatus status){

        String returnedMessage = shoeSaleServe.changeShoeSaleStatus(id, status);

        if(returnedMessage.equalsIgnoreCase("The shoe sale with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else if(returnedMessage.equalsIgnoreCase("Shoe sale status updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping (value="/all")
    public ResponseEntity<?> getAllShoeSales(){
        return new ResponseEntity<>(shoeSaleServe.getAllShoeSales(), HttpStatus.OK);
    }

    @GetMapping (value="/stats/by-status")
    public ResponseEntity<?> getShoeSalesPerStatus(){

        return new ResponseEntity<>(shoeSaleServe.getShoeSalesPerStatus(), HttpStatus.OK);
    }

    @PatchMapping (value="/cancel-day")
    public ResponseEntity<?> cancelShoeSalesOnDate(@RequestParam UUID shopAttendantId, @RequestParam LocalDate date){

        return new ResponseEntity<>(shoeSaleServe.cancelShoeSalesOnDate(shopAttendantId, date), HttpStatus.OK);
    }

    @GetMapping (value="/by-status")
    public ResponseEntity<?> getShoeSalesByStatus(@RequestParam SaleStatus status){

        return new ResponseEntity<>(shoeSaleServe.getShoeSalesByStatus(status), HttpStatus.OK);
    }

    @GetMapping (value="/between")
    public ResponseEntity<?> getShoeSalesBetween(@RequestParam String start, @RequestParam String end){

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        return new ResponseEntity<>(shoeSaleServe.getShoeSalesBetween(startDate, endDate), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getShoeSaleById(@PathVariable UUID id){

        Optional<ShoeSale> found = shoeSaleServe.getShoeSaleById(id);

        if(found.isPresent()){
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The shoe sale with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updateShoeSale(@PathVariable UUID id, @RequestBody ShoeSale shoeSale){

        String returnedMessage = shoeSaleServe.updateShoeSale(id, shoeSale);

        if(returnedMessage.equalsIgnoreCase("Shoe sale updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteShoeSale(@PathVariable UUID id){

        String returnedMessage = shoeSaleServe.deleteShoeSale(id);

        if(returnedMessage.equalsIgnoreCase("Shoe sale deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else if(returnedMessage.equalsIgnoreCase("That shoe sale has stock movements, delete them first")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CONFLICT);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
