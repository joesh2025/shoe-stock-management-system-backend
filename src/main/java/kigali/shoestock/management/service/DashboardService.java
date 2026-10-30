package kigali.shoestock.management.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.SaleStatus;
import kigali.shoestock.management.repository.CustomerRepository;
import kigali.shoestock.management.repository.SalesCounterRepository;
import kigali.shoestock.management.repository.ShoeSaleRepository;
import kigali.shoestock.management.repository.ShopAttendantRepository;
import kigali.shoestock.management.repository.StockMovementRepository;

@Service
public class DashboardService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ShopAttendantRepository shopAttendantRepo;

    @Autowired
    private SalesCounterRepository salesCounterRepo;

    @Autowired
    private ShoeSaleRepository shoeSaleRepo;

    @Autowired
    private StockMovementRepository stockMovementRepo;

    public Map<String, Long> getDashboardSummary(){

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("customers", customerRepo.count());
        summary.put("shopAttendants", shopAttendantRepo.count());
        summary.put("freeCounters", (long) salesCounterRepo.findFreeSalesCounters().size());
        summary.put("salesToday", shoeSaleRepo.countBySaleDate(LocalDate.now()));
        summary.put("quoted", shoeSaleRepo.countByStatus(SaleStatus.QUOTED));
        summary.put("stockMovements", stockMovementRepo.count());

        return summary;
    }
}
