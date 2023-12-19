package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Address;

public interface Addressrepo extends CrudRepository<Address,Integer> {

}
