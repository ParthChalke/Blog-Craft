package com.example.demo.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Address;
import com.example.demo.models.Blog;
import com.example.demo.models.User;
import com.example.demo.services.UserServices;
import com.example.demo.wrappers.ResponseWrapper;

@RestController
@RequestMapping("/users")
public class UserControllers {
@Autowired
UserServices userServices;

@GetMapping("")
@Cacheable("data")
public ResponseEntity getAllUsers(){
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("all users");
	reWrapper.setData(this.userServices.getAllUsers());
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}

void responseDelay(){
	try {
		Thread.sleep(3000);
	}catch (InterruptedException e) {
		e.printStackTrace();
	}
}

@GetMapping("{id}")
public ResponseEntity getUserById(@PathVariable int id) {
//	User user=this.userServices.getById(id);
//	if(user==null) {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"You can't delete because user with id "+id+" not found");
//	}
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("users");
	reWrapper.setData(this.userServices.getUserById(id));
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}

@GetMapping("/find-by")
public ResponseEntity getUserByEmail(@RequestParam String email) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("user");
	reWrapper.setData(this.userServices.getUserByEmail(email));
	return new ResponseEntity(reWrapper,HttpStatus.OK);

}

@GetMapping("/find-by-any")
public ResponseEntity getUserByAnyEmail(@RequestParam String email) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("user");
	reWrapper.setData(this.userServices.getUserByAnyEmail (email));
	return new ResponseEntity(reWrapper,HttpStatus.OK);

}

@GetMapping("/find-by-name")
public ResponseEntity getUserByName(@RequestParam String name) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("user");
	reWrapper.setData(this.userServices.getUserByName (name));
	return new ResponseEntity(reWrapper,HttpStatus.OK);

}

@GetMapping("/find-by-nameOrEmail")
public ResponseEntity getUserByAnyName(@RequestParam (required=false) String name,@RequestParam (required=false) String email) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("user");
	reWrapper.setData(this.userServices.getUserByEmailAndName(name, email));
	return new ResponseEntity(reWrapper,HttpStatus.OK);

}

@GetMapping("/Search")
public ResponseEntity getUserByNameOrEmail(@RequestParam (required=false) String name,@RequestParam (required=false) String email) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("user");
	reWrapper.setData(this.userServices.getUserByEmailAndName(name, email));
	return new ResponseEntity(reWrapper,HttpStatus.OK);

}




@PostMapping("")
public ResponseEntity createUser(@RequestBody User user) {
	this.userServices.createuser(user);
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("Data created");
	reWrapper.setData(this.userServices.createuser(user));
	return new ResponseEntity(reWrapper,HttpStatus.CREATED);
}


@DeleteMapping("{id}")
public ResponseEntity deleteUser(@PathVariable int id) {
//	User user=this.userServices.getUserById(id);
//	if(user==null) {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"You can't delete because user with id "+id+" not found");
//		//return new ResponseEntity("User with id "+id+" not found...!!!",HttpStatus.NOT_FOUND);
//	}
	this.userServices.deleteUser(id);
	return new ResponseEntity(HttpStatus.NO_CONTENT);
}


@PutMapping("{id}")
public ResponseEntity updateUser(@PathVariable int id,@RequestBody User updateData) {
//	User user=this.userServices.getUserById(id);
//	if(user==null) {
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"You can't update because user with id "+id+" not found");
//	}
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("User updated Sucessfully");
	reWrapper.setData(this.userServices.updateUser(id, updateData));
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}


@PostMapping("/{id}/address")
public ResponseEntity addAddress(@PathVariable int id,@RequestBody Address address) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("Address created Sucessfully");
	reWrapper.setData(this.userServices.createAddress(id, address));
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}

@GetMapping("/{id}/address")
public ResponseEntity getAddress(@PathVariable int id) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("Address");
	reWrapper.setData(this.userServices.getAddress(id));
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}

@PostMapping("/{id}/blog")
public ResponseEntity addBlog(@PathVariable int id,@RequestBody Blog blog) {
	ResponseWrapper reWrapper=new ResponseWrapper();
	reWrapper.setMsg("Blog created Sucessfully");
	reWrapper.setData(this.userServices.createBlogs(id, blog));
	return new ResponseEntity(reWrapper,HttpStatus.OK);
}


}
