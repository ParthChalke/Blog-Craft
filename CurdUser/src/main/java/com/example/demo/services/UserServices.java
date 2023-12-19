package com.example.demo.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Projection.UserProjection;
import com.example.demo.models.Address;
import com.example.demo.models.Blog;
import com.example.demo.models.User;
import com.example.demo.repo.Addressrepo;
import com.example.demo.repo.Blogrepo;
import com.example.demo.repo.UserRepo;

@Service
public class UserServices {

	private static final String addressRepo = null;

	@Autowired
	UserRepo userRepo;

	@Autowired
	Addressrepo addressrepo;
	
	@Autowired
	Blogrepo blogrepo;

	public User getUserWithPassword(int id) {
		return this.userRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	public Iterable<UserProjection> getAllUsers() {
		return this.userRepo.findAllUsersBy();
	}

	public UserProjection getUserById(int id) {
		return this.userRepo.findUserById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	public UserProjection getUserByEmail(String email) {
		return this.userRepo.findByEmail(email).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	public Iterable<UserProjection> getUserByAnyEmail(String email) {
		return this.userRepo.findByEmailContaining(email);
	}

	public Iterable<UserProjection> getUserByName(String name) {
		return this.userRepo.findByName(name);
	}

	public Iterable<UserProjection> getUserByEmailAndName(String name, String email) {
		return this.userRepo.findByNameOrEmail(name, email);
	}

	public Iterable<UserProjection> getUserByEmailOrName(String name, String email) {
		return this.userRepo.findByNameContainingOrEmailContaining(name, email);
	}

	public User createuser(User userModel) {
		return this.userRepo.save(userModel);
	}

	public void deleteUser(int id) {
		this.userRepo.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		});
	}

	public User updateUser(int id, User usermodel) {
		User userdata = getUserWithPassword(id);
		usermodel.setId(id);
		usermodel.setCreatedate(userdata.getCreatedate());
		return this.userRepo.save(usermodel);
	}

	public Address createAddress(int id, Address address) {
		User userdata = getUserWithPassword(id);
		Address addressdata = this.addressrepo.save(address);
		userdata.setAddress(addressdata);
		this.userRepo.save(userdata);
		return addressdata;
	}

	public Object getAddress(int id) {
User userdata = getUserWithPassword(id);
		return userdata.getAddress();
	}
	
	public Blog createBlogs(int id, Blog blog) {
		User userdata = getUserWithPassword(id);
        blog.setUser(userdata);
	    Blog saveBlog=this.blogrepo.save(blog);
	    return saveBlog;
	}


}
