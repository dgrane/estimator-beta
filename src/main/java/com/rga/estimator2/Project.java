package com.rga.estimator2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "PROJECT")
@Data
public class Project {    
    @Id 
    @GeneratedValue
    @Column(name = "project_id")
	private Long id;
    
    private int duration = 2; //DEFAULT is MONTHS
    
    @ManyToMany(mappedBy = "projects")
    private Set<Resource> resources = new HashSet<>();
    
    
    private String rateCard;
    
    private int exchangeRate;

    private int totalHours = 1920;
    
    private boolean inDollars;
    
	public String getRateCard() {
		return rateCard;
	}


	public void setRateCard(String rateCard) {
		this.rateCard = rateCard;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getTotalHours() {
		return totalHours;
	}


	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}


	public int getExchangeRate() {
		return exchangeRate;
	}


	public void setExchangeRate(int exchangeRate) {
		this.exchangeRate = exchangeRate;
	}


	public boolean isInDollars() {
		return inDollars;
	}


	public void setInDollars(boolean inDollars) {
		this.inDollars = inDollars;
	}


	public Set<Resource> getResources() {
		return resources;
	}


	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}


	public double getCurrentCost() {
		double totalCost = 0;
		for (Resource resource : resources) {
			totalCost+=resource.getCurrentCost();
		}
		
		return totalCost;
	}

	public double getTotalResourcesHours() {
		double resourcesHours = 0;
		for (Resource resource : resources) {
			resourcesHours+=resource.getHours();
		}
		
		return resourcesHours;
		
	}

	public double getBlendRate() {
		
		return getCurrentCost()/getTotalResourcesHours();
		
	}
     
    

	
	
}
