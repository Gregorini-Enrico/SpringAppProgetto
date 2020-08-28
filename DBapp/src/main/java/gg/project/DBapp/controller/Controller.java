package gg.project.DBapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.project.DBapp.Storage.*;
import gg.project.DBapp.model.*;
import gg.project.DBapp.service.PrincipalService;

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
		//return Storage.download();
	}
	
	@RequestMapping(value="/files/deleted", method = RequestMethod.GET) 
	public ResponseEntity<Object> getDeletedFiles(){
		return new ResponseEntity<>(service.getDeletedFiles(), HttpStatus.OK);
		//return Storage.download();
	}
	
	
	/*@RequestMapping(value="/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>();
	}*/
	
	
	
}
