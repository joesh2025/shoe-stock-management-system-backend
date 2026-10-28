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

import kigali.shoestock.management.domain.Customer;
import kigali.shoestock.management.service.CustomerService;

@RestController
@RequestMapping (value="/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerServe;

    @PostMapping (value="/save")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer){

        String returnedMessage = customerServe.saveCustomer(customer);

        return new ResponseEntity<>(returnedMessage, HttpStatus.CREATED);
    }

    @GetMapping (value="/all")
    public ResponseEntity<?> getAllCustomers(){
        return new ResponseEntity<>(customerServe.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable UUID id){

        Optional<Customer> found = customerServe.getCustomerById(id);

        if(found.isPresent()){
            return new ResponseEntity<>(found.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("The customer with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable UUID id, @RequestBody Customer customer){

        String returnedMessage = customerServe.updateCustomer(id, customer);

        if(returnedMessage.equalsIgnoreCase("Customer updated successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping (value="/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable UUID id){

        String returnedMessage = customerServe.deleteCustomer(id);

        if(returnedMessage.equalsIgnoreCase("Customer deleted successfully")){
            return new ResponseEntity<>(returnedMessage, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(returnedMessage, HttpStatus.NOT_FOUND);
        }
    }
}
