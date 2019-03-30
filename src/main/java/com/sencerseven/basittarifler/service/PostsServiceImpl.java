package com.sencerseven.basittarifler.service;

import com.sencerseven.basittarifler.domain.Posts;
import com.sencerseven.basittarifler.repository.PostsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostsServiceImpl implements PostsService {

    PostsRepository postsRepository;

    public PostsServiceImpl(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public Posts findById(Long id) throws Exception {
        Optional<Posts> postsOptional = postsRepository.findById(id);

        if(!postsOptional.isPresent() || postsOptional.filter(posts -> posts.getId() != id).isPresent())
            throw new Exception("not found Post item");

        return postsOptional.get();
    }

    @Override
    public Set<Posts> findAll() throws Exception {
        Set<Posts> posts = new HashSet<>();
        postsRepository.findAll().iterator().forEachRemaining(posts::add);
        return posts;
    }

    @Override
    public Set<Posts> findAllByPage(int page, int size, boolean active) throws Exception {
        Set<Posts> posts = new HashSet<>();
        postsRepository.findPostsByActive(PageRequest.of(page,size),active).iterator().forEachRemaining(posts::add);
        return posts;
    }


}
