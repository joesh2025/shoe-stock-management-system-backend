package kigali.shoestock.management.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="shop_attendant")
public class ShopAttendant {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="first_name")
    private String firstName;

    @Column (name="last_name")
    private String lastName;

    @Column (name="phone")
    private String phone;

    @ManyToOne
    @JoinColumn (name="sales_counter_id")
    private SalesCounter salesCounter;

    @ManyToMany
    @JoinTable (name="shop_attendant_shoe_line")
    private List<ShoeLine> shoeLines = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public SalesCounter getSalesCounter() {
        return salesCounter;
    }

    public void setSalesCounter(SalesCounter salesCounter) {
        this.salesCounter = salesCounter;
    }

    public List<ShoeLine> getShoeLines() {
        return shoeLines;
    }

    public void setShoeLines(List<ShoeLine> shoeLines) {
        this.shoeLines = shoeLines;
    }
}
