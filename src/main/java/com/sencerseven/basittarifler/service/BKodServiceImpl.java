package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.BKod;
import com.sencerseven.basittarifler.repository.BKodRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BKodServiceImpl implements BKodService {

    BKodRepository bKodRepository;

    public BKodServiceImpl(BKodRepository bKodRepository) {
        this.bKodRepository = bKodRepository;
    }

    @Override
    public Set<BKod> findAllByBkod(int page, int size, String bkod) {
        if(bkod.isEmpty()){
            return null;
        }
        return bKodRepository.findAllByBkod(PageRequest.of(page,size),bkod).stream().collect(Collectors.toSet());

    }
}
