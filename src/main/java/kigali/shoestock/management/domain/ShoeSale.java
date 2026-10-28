package kigali.shoestock.management.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="shoe_sale")
public class ShoeSale {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="sale_date")
    private LocalDate saleDate;

    @Enumerated (EnumType.STRING)
    @Column (name="status")
    private SaleStatus status;

    @Column (name="shoe_name")
    private String shoeName;

    @Column (name="size")
    private int size;

    @ManyToOne
    @JoinColumn (name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn (name="shop_attendant_id")
    private ShopAttendant shopAttendant;

    public UUID getId() {
        return id;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShopAttendant getShopAttendant() {
        return shopAttendant;
    }

    public void setShopAttendant(ShopAttendant shopAttendant) {
        this.shopAttendant = shopAttendant;
    }
}
