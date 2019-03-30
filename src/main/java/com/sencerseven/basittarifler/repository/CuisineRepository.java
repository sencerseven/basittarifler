package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Cuisine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<Cuisine, Long> {

}
