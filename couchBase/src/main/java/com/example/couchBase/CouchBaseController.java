package com.example.couchBase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CouchBaseController {
	
	@Autowired
	private CouchBasePersonService couchBasePersonService;
	
	@RequestMapping(path = "/")
	public String display(){
		couchBasePersonService.start();
		return "Welcome to Couch Base Operations";
	}
	

	
	@RequestMapping(method = RequestMethod.POST,value = "/addPerson")
	public void addPerson(@RequestBody CouchBasePerson person) {
		couchBasePersonService.addPerson(person);
	}
	
	@RequestMapping(method = RequestMethod.PUT,value = "/person/{companyId}")
	public void updatePerson(@RequestBody CouchBasePerson person, @PathVariable("companyId") String companyId) {
		couchBasePersonService.updatePerson(person, companyId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value = "/person/{companyId}")
	public void deletePerson(@PathVariable("companyId") String companyId) {
		couchBasePersonService.deletePerson(companyId);
	}
	
	

}
