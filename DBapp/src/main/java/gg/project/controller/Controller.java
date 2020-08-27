package gg.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gg.project.service.PrincipalService;

@RestController	
public class Controller {
	
	@Autowired
	PrincipalService service;
	
	/**
	 * Metodo per gestire la chiamata GET alla rotta /record/data
	 * per ottenere l'elenco di tutti i file presenti nell'account dropbox
	 * 
	 */
	@RequestMapping(value="/files", method = RequestMethod.GET) 
	public ResponseEntity<Object> getFiles(){
		return new ResponseEntity<>(service.getRecords(), HttpStatus.OK);
	}
	
}
