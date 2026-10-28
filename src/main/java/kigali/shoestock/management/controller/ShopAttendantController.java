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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kigali.shoestock.management.domain.ShopAttendant;
import kigali.shoestock.management.service.ShopAttendantService;

@RestController
@RequestMapping (value="/api/shop-attendants")
public class ShopAttendantController {

    @Autowired
    private ShopAttendantService shopAttendantServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveShopAttendant(@RequestBody ShopAttendant shopAttendant){

        String returnedMessage = shopAttendantServe.saveShopAttendant(shopAttendant);

        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping (value="/all")
    public ResponseEntity<?> getAllShopAttendants(){
        return new ResponseEntity<>(shopAttendantServe.getAllShopAttendants(), HttpStatus.OK);
    }

    @GetMapping (value="/by-shoe-line")
    public ResponseEntity<?> getShopAttendantsByShoeLine(@RequestParam String name){

        return new ResponseEntity<>(shopAttendantServe.getShopAttendantsByShoeLine(name), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getShopAttendantById(@PathVariable UUID id){

        Optional<ShopAttendant> found = shopAttendantServe.getShopAttendantById(id);

        if(found.isPresent()){
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The shop attendant with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updateShopAttendant(@PathVariable UUID id, @RequestBody ShopAttendant shopAttendant){

        String returnedMessage = shopAttendantServe.updateShopAttendant(id, shopAttendant);

        if(returnedMessage.equalsIgnoreCase("Shop attendant updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteShopAttendant(@PathVariable UUID id){

        String returnedMessage = shopAttendantServe.deleteShopAttendant(id);

        if(returnedMessage.equalsIgnoreCase("Shop attendant deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
