package com.rga.estimator2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller    // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private ResourceRepository resourceRepository;
	
	
	@RequestMapping("/tables")
	public String greeting(@RequestParam(value = "rateCard", required = false, defaultValue = "World") String rateCard,
			Model model) {
		Iterable<Resource> res = resourceRepository.findByRateCard(rateCard);
		List<Resource> resWithRate =  new ArrayList<Resource>();
		
		ArrayList<String> positions =  new ArrayList<String>();
		ArrayList<String> departments =  new ArrayList<String>();

		for (Resource resource : res) {
			{
				if(resource.getRate() != 0) resWithRate.add(resource);
				
			}
			positions.add(resource.getDepartment() + " - "+ resource.getPosition());
			departments.add(resource.getDepartment());
		}
		model.addAttribute("res", (Iterable<Resource>)resWithRate);
		model.addAttribute("positionArray", positions);
		model.addAttribute("departmentArray", departments);
		model.addAttribute("months",12);

		return "tables";
	}


	@GetMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String position) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Resource n = new Resource();
		n.setName(name);
		n.setPosition(position);
		resourceRepository.save(n); 
		return "Saved"; 
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Resource> getAllUsers() {
		// This returns a JSON or XML with the users
		return resourceRepository.findAll();
	}
	
	@GetMapping(path="/createProject")
	public @ResponseBody Iterable<Resource> createProject() {
		
		Iterable<Resource> allResources = resourceRepository.findByRateCard(DataUtils.SLA2017);
		// This returns a JSON or XML with the users
		return allResources;
	}
	
	@GetMapping(path="/getYearCost")
	public @ResponseBody Iterable<Resource> createProject(@RequestParam String department , @RequestParam String position, @RequestParam String rateCard) {
		
		Iterable<Resource> allResources = resourceRepository.findByRateCard(rateCard);
		Iterable<Resource> results = new ArrayList<Resource>();

		for (Resource resource : allResources) {
			if(department.equals(resource.getDepartment()) && position.equals(resource.getPosition()))
			{
				System.out.println("Annual cost of "+resource.getPosition()+ " is :"+resource.getRate()*1920);
			}
		}
		
		// This returns a JSON or XML with the users
		return null;
	}
	
	@GetMapping(path="/load")
	public @ResponseBody Iterable<Resource> loadAllUsers() {
		// This returns a JSON or XML with the users
		 try {
			List<Resource> resources = DataLoader.loadData(DataUtils.SLA2017);
			resourceRepository.save(resources);
			List<Resource> resourcesFalabella = DataLoader.loadData(DataUtils.FALABELLA);
			resourceRepository.save(resourcesFalabella);
			List<Resource> resourcesSantiago = DataLoader.loadData(DataUtils.SANTIAGO2017);
			resourceRepository.save(resourcesSantiago);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
	
	
}
