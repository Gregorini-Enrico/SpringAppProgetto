package gg.project.DBapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.project.DBapp.Stats.Statistics;
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
	
	
	@RequestMapping(value="/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(service.getMetadata(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/statistics/mediaDeleted", method = RequestMethod.GET)
	public double getDMedia(){
		return Statistics.mediaDeletedFile(DeletedFiles.downloadDeletedFiles());
	}
	
	@RequestMapping(value="/statistics/media", method = RequestMethod.GET)
	public double getMedia(){
		return Statistics.media(Storage.downloadFile());
	}
	
	@RequestMapping(value="/statistics/maxDeleted", method = RequestMethod.GET)
	public ResponseEntity<Object> getDMax(){
		return new ResponseEntity<>(Statistics.maxDimFileDeleted(DeletedFiles.downloadDeletedFiles()), HttpStatus.OK);	}
	
	@RequestMapping(value="/statistics/max", method = RequestMethod.GET)
	public ResponseEntity<Object> getMax(){
		return new ResponseEntity<>(Statistics.maxDimFile(Storage.downloadFile()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/statistics/type", method = RequestMethod.GET)
	public ResponseEntity<Object> getType(){
		return new ResponseEntity<>(Statistics.getFileType(Storage.downloadFile()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/restore", method = RequestMethod.POST)
	public ResponseEntity<String> FileRestore(@RequestParam (name ="name",defaultValue = "Ereditarieta.pdf") String name){
	    service.RestoreFile(name);
	    return new ResponseEntity<>("File restored", HttpStatus.OK);
	}
	
}
