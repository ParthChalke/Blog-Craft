package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Blog;

public interface Blogrepo extends CrudRepository<Blog, Integer> {

}
