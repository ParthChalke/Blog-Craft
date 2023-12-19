package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Blog;
import com.example.demo.models.User;
import com.example.demo.repo.Blogrepo;
import com.example.demo.repo.UserRepo;

public class BlogServices {
@Autowired
Blogrepo blogrepo;

public Iterable<Blog> getllAllUser(){
	return this.blogrepo.findAll();
}

public Blog createBlog(Blog blog) {
	return this.blogrepo.save(blog);
}
public Blog GetById (int id) {
	return this.blogrepo.findById(id).orElseThrow(()->{
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	});
}

public Blog update (int id, Blog blog) {
	this.blogrepo.findById(id).orElseThrow(()->{
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	});
	blog.setId(id);
	return this.blogrepo.save(blog);
}

}
