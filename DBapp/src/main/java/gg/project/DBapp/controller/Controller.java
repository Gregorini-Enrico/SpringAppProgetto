package gg.project.DBapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.project.DBapp.Stats.Statistics;
import gg.project.DBapp.Storage.*;
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
	}
	
	
	
	@RequestMapping(value="/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(service.getMetadata(), HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/statistics/media", method = RequestMethod.GET)
	public ResponseEntity<Object> getMedia(@RequestParam (name = "type", defaultValue = "file") String type,
                                           @RequestParam (name="subfolder", defaultValue = "")String subfolder){
		if(type.equals("file"))
		     return new ResponseEntity<>(Statistics.media(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(type.equals("deleted"))
			return new ResponseEntity<>(Statistics.mediaDeletedFile(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);
		else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	@RequestMapping(value="/statistics/max", method = RequestMethod.GET)
	public ResponseEntity<Object> getMax(@RequestParam (name = "type", defaultValue = "file") String type,
                                         @RequestParam (name="subfolder", defaultValue = "")String subfolder){
		if(type.equals("file"))
		     return new ResponseEntity<>(Statistics.maxDimFile(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(type.equals("deleted"))
			return new ResponseEntity<>(Statistics.maxDimFileDeleted(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);
		else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	@RequestMapping(value="/statistics/min", method = RequestMethod.GET)
	public ResponseEntity<Object> getMin(@RequestParam (name = "type", defaultValue = "file") String type,
			                             @RequestParam (name="subfolder", defaultValue = "")String subfolder){
		if(type.equals("file"))
		      return new ResponseEntity<>(Statistics.minDimFile(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(type.equals("deleted"))
			return new ResponseEntity<>(Statistics.minDimFileDeleted(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);	
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	@RequestMapping(value="/statistics/type", method = RequestMethod.GET)
	public ResponseEntity<Object> getType(@RequestParam (name="type", defaultValue = "file") String type,
			                              @RequestParam (name="subfolder", defaultValue = "")String subfolder){
		if(type.equals("file"))
		     return new ResponseEntity<>(Statistics.getFileType(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(type.equals("deleted"))
			return new ResponseEntity<>(Statistics.getDeletedFileType(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);	
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/restore", method = RequestMethod.POST)
	public ResponseEntity<String> FileRestore(@RequestBody String filter){
	    if(service.RestoreFile(filter))
	         return new ResponseEntity<>("File restored", HttpStatus.OK);
	    else return new ResponseEntity<>("L'operazione non è avvenuta con successo!", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/filter", method = RequestMethod.POST)
	public ResponseEntity<Object> TypeRestore(@RequestBody String filter, @RequestParam (name = "file")String file){
		if(file.equals("file"))
			return new ResponseEntity<>(service.getFilteredFile(filter), HttpStatus.OK);	
		else if(file.equals("deleted"))
			return new ResponseEntity<>(service.getFilteredFileDeleted(filter), HttpStatus.OK);
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
		
}
