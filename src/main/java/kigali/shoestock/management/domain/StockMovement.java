package kigali.shoestock.management.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="stock_movement")
public class StockMovement {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    @Column (name="shoe_name")
    private String shoeName;

    @Column (name="note")
    private String note;

    @Column (name="quantity")
    private int quantity;

    @Column (name="moved_date")
    private LocalDate movedDate;

    @ManyToOne
    @JoinColumn (name="shoe_sale_id")
    private ShoeSale shoeSale;

    public UUID getId() {
        return id;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getMovedDate() {
        return movedDate;
    }

    public void setMovedDate(LocalDate movedDate) {
        this.movedDate = movedDate;
    }

    public ShoeSale getShoeSale() {
        return shoeSale;
    }

    public void setShoeSale(ShoeSale shoeSale) {
        this.shoeSale = shoeSale;
    }
}
