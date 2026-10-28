package kigali.shoestock.management.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kigali.shoestock.management.domain.ShoeLine;

@Repository
public interface ShoeLineRepository extends JpaRepository<ShoeLine, UUID>{

    @Query("SELECT sl FROM ShoeLine sl WHERE sl.shopAttendants IS EMPTY")
    List<ShoeLine> findUnusedShoeLines();

}
