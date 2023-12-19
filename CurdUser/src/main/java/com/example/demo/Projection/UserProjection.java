package com.example.demo.Projection;

import java.time.Instant;
import java.util.List;

import com.example.demo.models.Address;
import com.example.demo.models.Blog;

public interface UserProjection {

	public Integer getId();
	public String getName();
	public String getEmail();
	public Instant getCreatedate();
	public Instant getUpdateDate();
	public Address getAddress();
	public List<Blog> getBlog();
}
