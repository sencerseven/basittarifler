package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Cuisine;

import java.util.List;


public interface CuisineService {

    public List<Cuisine> findAll();

    public Cuisine getById(Long id);
}
