package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.models.Blog;
import com.example.demo.services.BlogServices;
import com.example.demo.wrappers.ResponseWrapper;

import jakarta.validation.Valid;

public class BlogController {
@Autowired
BlogServices blogServices;

@GetMapping("")
public ResponseEntity getAllBlog() {
	ResponseWrapper responseWrapper=new ResponseWrapper();
	responseWrapper.setMsg("all blogs :");
	responseWrapper.setData(this.blogServices.getllAllUser());
	return new ResponseEntity(responseWrapper,HttpStatus.OK);
	
}
@GetMapping("{id}")
public ResponseEntity getById(@PathVariable int id) {
	ResponseWrapper responseWrapper=new ResponseWrapper();
	responseWrapper.setMsg("blog with id "+id);
	responseWrapper.setData(this.blogServices.GetById(id));
	return new ResponseEntity(responseWrapper,HttpStatus.OK);
	
}
@PostMapping("")
public ResponseEntity createBlog(@RequestBody @Valid Blog blog) {
ResponseWrapper responseWrapper=new ResponseWrapper();
responseWrapper.setMsg("data created");
responseWrapper.setData(this.blogServices.createBlog(blog));
return new ResponseEntity(responseWrapper, HttpStatus.CREATED);
}

@PutMapping("{id}")
 public ResponseEntity updateBlog(@PathVariable int id,@RequestBody Blog blog) {
	this.blogServices.update(id,blog);
	ResponseWrapper responseWrapper=new ResponseWrapper();
	responseWrapper.setMsg("data updated");
	responseWrapper.setData(this.blogServices.update(id,blog));
	return new ResponseEntity(responseWrapper, HttpStatus.OK);

}
}
