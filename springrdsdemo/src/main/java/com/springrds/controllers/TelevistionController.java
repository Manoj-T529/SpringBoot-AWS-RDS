package com.springrds.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrds.model.Television;
import com.springrds.repository.TelevisionRepository;

@RestController
@RequestMapping("/api")
public class TelevistionController {

	@Autowired
	private TelevisionRepository televisionRepository;
	
	
	@PostMapping("/save")
	public Television save(@RequestBody Television television)
	{
		System.out.println(television.getTvName() +" "+television.getTvSize());
		
		return televisionRepository.save(television);
	}
	
	@GetMapping("get/{id}")
	public Optional<Television> finddata(@PathVariable("id") Long TelevisionId)
	{
		return televisionRepository.findById(TelevisionId);
	}
	
	@GetMapping("/find")
	public List<Television> findAllData()
	{
		return televisionRepository.findAll();
	}
	
	
}
