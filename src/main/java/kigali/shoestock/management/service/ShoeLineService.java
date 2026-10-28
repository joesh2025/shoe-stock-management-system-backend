package kigali.shoestock.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kigali.shoestock.management.domain.ShoeLine;
import kigali.shoestock.management.repository.ShoeLineRepository;

@Service
public class ShoeLineService {

    @Autowired
    private ShoeLineRepository shoeLineRepo;

    public String saveShoeLine(ShoeLine shoeLine){

        shoeLineRepo.save(shoeLine);

        return "Shoe line saved successfully";
    }

    public List<ShoeLine> getAllShoeLines(){
        return shoeLineRepo.findAll();
    }

    public Optional<ShoeLine> getShoeLineById(UUID id){
        return shoeLineRepo.findById(id);
    }

    public String updateShoeLine(UUID id, ShoeLine shoeLine){

        Optional<ShoeLine> found = shoeLineRepo.findById(id);

        if(found.isEmpty()){
            return "The shoe line with that id does not exist";
        }

        ShoeLine existing = found.get();
        existing.setName(shoeLine.getName());

        shoeLineRepo.save(existing);

        return "Shoe line updated successfully";
    }

    public String deleteShoeLine(UUID id){

        Optional<ShoeLine> found = shoeLineRepo.findById(id);

        if(found.isEmpty()){
            return "The shoe line with that id does not exist";
        }

        shoeLineRepo.deleteById(id);

        return "Shoe line deleted successfully";
    }
}
