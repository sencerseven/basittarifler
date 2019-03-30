package com.sencerseven.basittarifler.service.specification;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T,U> extends BaseSpecification<T,U> {
    @Override
    public Specification<T> getFilter(U request) {
        return null;
    }
}
