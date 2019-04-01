package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.BKod;

import java.util.Set;

public interface BKodService {

    Set<BKod> findAllByBkod(int page, int size, String bkod);

}
