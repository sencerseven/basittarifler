package com.sencerseven.basittarifler.repository;

import com.sencerseven.basittarifler.domain.Posts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    Optional<Posts> findById(Long id);

    List<Posts> findPostsByActive(Pageable pageable,boolean Active);

}
