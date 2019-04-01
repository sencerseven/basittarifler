package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Posts;

import java.util.Set;

public interface PostsService {

    public Posts findById(Long id) throws Exception;

    public Set<Posts> findAll() throws Exception;

    public Set<Posts> findAllByPage(int page,int size,boolean active) throws Exception;
}
