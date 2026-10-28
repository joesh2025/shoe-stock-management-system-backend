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

import kigali.shoestock.management.domain.ShoeLine;
import kigali.shoestock.management.service.ShoeLineService;

@RestController
@RequestMapping (value="/api/shoe-lines")
public class ShoeLineController {

    @Autowired
    private ShoeLineService shoeLineServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveShoeLine(@RequestBody ShoeLine shoeLine){

        String returnedMessage = shoeLineServe.saveShoeLine(shoeLine);

        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping (value="/all")
    public ResponseEntity<?> getAllShoeLines(){
        return new ResponseEntity<>(shoeLineServe.getAllShoeLines(), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getShoeLineById(@PathVariable UUID id){

        Optional<ShoeLine> found = shoeLineServe.getShoeLineById(id);

        if(found.isPresent()){
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The shoe line with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updateShoeLine(@PathVariable UUID id, @RequestBody ShoeLine shoeLine){

        String returnedMessage = shoeLineServe.updateShoeLine(id, shoeLine);

        if(returnedMessage.equalsIgnoreCase("Shoe line updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteShoeLine(@PathVariable UUID id){

        String returnedMessage = shoeLineServe.deleteShoeLine(id);

        if(returnedMessage.equalsIgnoreCase("Shoe line deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
