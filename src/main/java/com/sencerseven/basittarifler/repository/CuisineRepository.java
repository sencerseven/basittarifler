package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

}
