package com.rga.estimator2;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Estimator2ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private ResourceRepository resourceRepository;
	

	@Test
	public void testPepsiChile() {
		//Create Project
		Project pepsiChile =  new Project();
		pepsiChile.setRateCard(DataUtils.SANTIAGO2017);
		pepsiChile.setDuration(DataUtils.MONTHS);
		pepsiChile.setDuration(12);
		pepsiChile.setExchangeRate(610);
		
		Resource commManager = resourceRepository.findByPositionAndRateCard(Positions.STRAT_COMMUNITY_MANAGER.url(), pepsiChile.getRateCard());
		Resource socialStrategist = resourceRepository.findByPositionAndRateCard(Positions.STRAT_SOCIAL_STRATEGIST.url(), pepsiChile.getRateCard());
		Resource prod = resourceRepository.findByDepartmentAndPositionAndRateCard("PROD",Positions.PROD_PRODUCER.url(), pepsiChile.getRateCard());
		Resource contentProd = resourceRepository.findByDepartmentAndPositionAndRateCard("CONT", Positions.CONT_PRODUCER.url() ,pepsiChile.getRateCard());
	
		commManager.setHours(pepsiChile.getTotalHours());
		socialStrategist.setHours(pepsiChile.getTotalHours());
		prod.setHours(pepsiChile.getTotalHours());
		contentProd.setHours(pepsiChile.getTotalHours());

		assertTrue(commManager.getCurrentCost() == 41844480);
		assertTrue(socialStrategist.getCurrentCost() == 57776640);
		assertTrue(prod.getCurrentCost() == 58757760);
		assertTrue(contentProd.getCurrentCost() == 58757760);
		
		pepsiChile.getResources().add(commManager);
		pepsiChile.getResources().add(socialStrategist);
		pepsiChile.getResources().add(prod);
		pepsiChile.getResources().add(contentProd);
		
		assertTrue(pepsiChile.getCurrentCost()==217136640);
		
		double currentCost = pepsiChile.getCurrentCost();
		double totalHours  =  pepsiChile.getTotalResourcesHours();
		assertTrue((currentCost/totalHours) == 28273);
	}
	
	
	@Test 
	@Ignore
	public void printAllPositions()
	{
		Iterable<Resource> allResources =  resourceRepository.findByRateCard(DataUtils.SLA2017);

		for (Resource resource : allResources) {
			String pos = resource.getPosition().toUpperCase().replaceAll(",", "_") ;
			String b= pos.replaceAll("\\s+","_");
			String c = b.replaceAll("R/GA", "RGA");
			String d = c.replaceAll("&", "_");
			String e = d.replaceAll("__", "_");
			String f = e.replaceAll("/", "_");
			String g = f.replaceAll("\\+", " ");
			System.out.println( resource.getDepartment()+"_"+g+"(\""+resource.getPosition() +"\") ,");
		}
	}
	
	
	
	
	
	public ResourceRepository getResourceRepository() {
		return resourceRepository;
	}
	
	public void setResourceRepository(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

}
