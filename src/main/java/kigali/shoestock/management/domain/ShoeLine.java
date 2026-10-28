package kigali.shoestock.management.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="shoe_line")
public class ShoeLine {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="name")
    private String name;

    @ManyToMany (mappedBy = "shoeLines")
    @JsonIgnore
    private List<ShopAttendant> shopAttendants = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShopAttendant> getShopAttendants() {
        return shopAttendants;
    }

    public void setShopAttendants(List<ShopAttendant> shopAttendants) {
        this.shopAttendants = shopAttendants;
    }
}
