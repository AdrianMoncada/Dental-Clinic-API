package com.dh.dentalClinic.service;

import com.dh.dentalClinic.persistence.entities.Dentist;
import com.dh.dentalClinic.persistence.entities.Patient;
import com.dh.dentalClinic.persistence.repository.DentistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DentistService {

    private static final Logger logger = Logger.getLogger(DentistService.class);

    @Autowired
    DentistRepository repository;

    public String save(Dentist d){
        if (repository.save(d)!= null){
            logger.info("Dentist was succesfully saved");
            return "New dentist saved";
        }else {
            logger.error("There was something wrong...");
            return "There was something wrong...";
        }
    }
    public List<Dentist> getAll(){
        logger.info("Searching all dentists...");
        return repository.findAll();
    }

    public Dentist getById(Long id){

        if(repository.existsById(id)){
            Dentist dentist = repository.findById(id).get();
            logger.info("Looking for dentist with id:" + id);
            return dentist;
        }
        logger.info("Dentist was not found");
        return null;
    }

    public String delete(Long id) {
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            logger.info("Dentist was succesfully deleted");
            return "Dentist id: " + id + " was succesfully deleted.";
        }
        logger.error("Dentist was not found");
        return "Dentist with id: " + id + " was not found.";
    }



    public String updateDentist(Dentist d){
        Long dentistId = d.getId();

        if(repository.findById(dentistId).isPresent()) {
            Dentist dentistaAModificar = repository.getById(dentistId);

            dentistaAModificar.setFirstName(d.getFirstName());
            dentistaAModificar.setLastName(d.getLastName());
            dentistaAModificar.setRegistration(d.getRegistration());
            dentistaAModificar.setPatients(d.getPatients());

            repository.save(dentistaAModificar);
            logger.info("Dentist " + dentistId +" was succesfully modified.");
            return "Dentist with Id: " + dentistId + " was modified.";

        } else {
            logger.error("Dentist doesn't exist");
            return "Dentist with Id " + dentistId + " does not exist.";
        }
    }
}
