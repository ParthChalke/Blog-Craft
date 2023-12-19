package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Projection.UserProjection;
import com.example.demo.models.User;

public interface UserRepo extends CrudRepository<User,Integer> {

	public Iterable<UserProjection> findAllUsersBy();
	
	public Optional<UserProjection> findUserById(int id);
	
	public Optional<UserProjection> findByEmail(String email);

	public Iterable<UserProjection> findByEmailContaining(String email);
	
	public Iterable<UserProjection> findByName(String name);
	
	public Iterable<UserProjection> findByNameOrEmail(String name,String email);
	
	public Iterable<UserProjection> findByNameContainingOrEmailContaining(String name,String email);


}