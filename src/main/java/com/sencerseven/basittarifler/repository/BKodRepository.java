package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.BKod;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BKodRepository extends JpaRepository<BKod,Long> {

    List<BKod> findAllByBkod(Pageable pageable , String bkod);
}
