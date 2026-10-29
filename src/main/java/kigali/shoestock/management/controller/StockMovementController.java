package kigali.shoestock.management.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.shoestock.management.domain.StockMovement;
import kigali.shoestock.management.service.StockMovementService;

@RestController
@RequestMapping (value="/api/stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveStockMovement(@RequestParam UUID shoeSaleId, @RequestBody StockMovement stockMovement){

        String returnedMessage = stockMovementServe.saveStockMovement(shoeSaleId, stockMovement);

        if(returnedMessage.equalsIgnoreCase("Stock movement saved successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
        }else if(returnedMessage.equalsIgnoreCase("The shoe sale with that id does not exist")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping (value="/customer/{customerCode}")
    public ResponseEntity<?> getStockMovementsByCustomerCode(@PathVariable String customerCode){

        return new ResponseEntity<>(stockMovementServe.getStockMovementsByCustomerCode(customerCode), HttpStatus.OK);
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteStockMovement(@PathVariable UUID id){

        String returnedMessage = stockMovementServe.deleteStockMovement(id);

        if(returnedMessage.equalsIgnoreCase("Stock movement deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
