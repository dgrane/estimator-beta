package com.rga.estimator2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/test")
public class GreetingController {

	public GreetingController(ResourceRepository resourceRepository) {
		super();
		this.resourceRepository = resourceRepository;
	}

	@Autowired
	private ResourceRepository resourceRepository;

	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping("/resources")
	public String resources(
			@RequestParam(value = "rateCard", required = false, defaultValue = "SLA2017") String rateCard, Model model) {
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

		return "resources";
	}

}
