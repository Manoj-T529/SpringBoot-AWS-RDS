package com.springrds.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Televison")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Television {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long TelevisionId;
	
	private String TvName;
	
	private int TvSize;
	
	private long cost;
}
