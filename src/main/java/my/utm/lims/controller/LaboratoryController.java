package my.utm.lims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import my.utm.lims.model.Laboratory;
import my.utm.lims.repository.LaboratoryRepository;


@RestController
@RequestMapping("/api")
public class LaboratoryController {

	@Autowired
	LaboratoryRepository labRepo;
	
	@RequestMapping(value = "/")
	public String root() {
		return "Welcome to Lims Laboratory Service";
	}
	
	@RequestMapping(value = "/lab/", method = RequestMethod.GET)
	public ResponseEntity<List<Laboratory>> listAllLabs() {
		List<Laboratory> allLabs = labRepo.findAll();
		if (allLabs.isEmpty()) {
			return new ResponseEntity<List<Laboratory>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Laboratory>>(allLabs, HttpStatus.OK);
	}

	@RequestMapping(value = "/lab/{id}", method = RequestMethod.GET)
	public ResponseEntity<Laboratory> getLab(@PathVariable("id") Long id) {
		Laboratory theLab = labRepo.findOne(id);
		if (theLab == null) {
			return new ResponseEntity<Laboratory>(theLab, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Laboratory>(theLab, HttpStatus.OK);
	}

	@RequestMapping(value = "/lab/", method = RequestMethod.POST)
	public ResponseEntity<Laboratory> createLab(@RequestBody Laboratory lab, 
			UriComponentsBuilder ucBuilder) {
		labRepo.create(lab);
		return new ResponseEntity<Laboratory>(lab, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/lab/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Laboratory> deleteLab(@PathVariable("id") Long id) {
		labRepo.delete(id);
		return new ResponseEntity<Laboratory>(HttpStatus.ACCEPTED);
	}
}
