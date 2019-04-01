package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Cuisine;
import com.sencerseven.basittarifler.repository.CuisineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineServiceImpl implements CuisineService{

    CuisineRepository cuisineRepository;

    public CuisineServiceImpl(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }

    @Override
    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    @Override
    public Cuisine getById(Long id) {

        Optional<Cuisine> optionalCuisine = cuisineRepository.findById(id);

        if(!optionalCuisine.isPresent())
            return null;


        return optionalCuisine.get();



    }


}
