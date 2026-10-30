package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.Customer;
import kigali.shoestock.management.repository.CustomerRepository;
import kigali.shoestock.management.repository.ShopAttendantRepository;
import kigali.shoestock.management.repository.ShoeSaleRepository;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ShopAttendantRepository shopAttendantRepo;

    @Autowired
    private ShoeSaleRepository shoeSaleRepo;

    public String saveCustomer(Customer customer){

        customerRepo.save(customer);

        return "Customer saved successfully";
    }

    public List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    public List<Customer> getCustomersByLastName(String lastName){
        return customerRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public List<Customer> getCustomersOfShopAttendant(UUID shopAttendantId){

        if(shopAttendantRepo.findById(shopAttendantId).isEmpty()){
            return null;
        }

        return customerRepo.findCustomersOfShopAttendant(shopAttendantId);
    }

    public Optional<Customer> getCustomerById(UUID id){
        return customerRepo.findById(id);
    }

    public String updateCustomer(UUID id, Customer customer){

        Optional<Customer> found = customerRepo.findById(id);

        if(found.isEmpty()){
            return "The customer with that id does not exist";
        }

        Customer existing = found.get();
        existing.setFirstName(customer.getFirstName());
        existing.setLastName(customer.getLastName());
        existing.setCustomerCode(customer.getCustomerCode());
        existing.setPhone(customer.getPhone());

        customerRepo.save(existing);

        return "Customer updated successfully";
    }

    public String deleteCustomer(UUID id) {

    Optional<Customer> found = customerRepo.findById(id);

    if (found.isEmpty()) {
        return "The customer with that id does not exist";
    }

    if (shoeSaleRepo.existsByCustomer_Id(id)) {
        return "Cannot delete this customer because they have existing sales records";
    }

    customerRepo.deleteById(id);

    return "Customer deleted successfully";
    }
}
