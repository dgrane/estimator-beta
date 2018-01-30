package com.rga.estimator2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Resource")
@Data
public class Resource {

	@Id
	@GeneratedValue
	@Column(name = "resource_id")
	private Long id;

	private String name;

	private String position;
	
	private String department; 
	
	private String rateCard;
	
	private int rate; 
	
	private int hours;



	public Resource(String name, String position, String department, int rate) {
		super();
		this.setName(name);
		this.setPosition(position); 
		this.setDepartment(department);
		this.setRate(rate); 
	}


	public Resource() {
		// TODO Auto-generated constructor stub
	}

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "Resource_Project", joinColumns = { @JoinColumn(name = "resource_id") }, inverseJoinColumns = {
			@JoinColumn(name = "project_id") })
	Set<Project> projects = new HashSet<>();

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int  getRate() {
		return rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}


	public String getRateCard() {
		return rateCard;
	}


	public void setRateCard(String rateCard) {
		this.rateCard = rateCard;
	}


	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}


	public int getCurrentCost() {
		return hours * rate;
	}
}
