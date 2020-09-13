package gg.project.DBapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gg.project.DBapp.Exception.SubfolderNotFoundException;
import gg.project.DBapp.Stats.Statistics;
import gg.project.DBapp.Storage.*;
import gg.project.DBapp.service.PrincipalService;

/**
 * Spring controller per gestire le chiamate effettuate dall'utente
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
@RestController	
public class Controller {
	
	@Autowired
	PrincipalService service;
	
	/**
	 * Metodo per gestire la chiamata GET alla rotta /record/data
	 * per ottenere l'elenco di tutti i file/cartelle/file eliminati presenti nell'account dropbox
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	@RequestMapping(value="/files", method = RequestMethod.GET) 
	public ResponseEntity<Object> getFiles(){
		return new ResponseEntity<>(service.getRecords(), HttpStatus.OK);
	}
	
	/**
	 * Metodo che restituisce la lista dei file eliminati con tutti i campi che li descrivono
	 * @return lista di tutti i file eliminati 
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	@RequestMapping(value="/files/deleted", method = RequestMethod.GET) 
	public ResponseEntity<Object> getDeletedFiles(){
		return new ResponseEntity<>(service.getDeletedFiles(), HttpStatus.OK);
	}
	
	
	/**
	 * Metodo che restituisce tutti i metadata
	 * ossia i campi che vengono gestiti per i vari Record presenti nella cartella dropbox
	 * @return lista dei metadata come HashMap<String,String>
     * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	@RequestMapping(value="/metadata", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetadata(){
		return new ResponseEntity<>(service.getMetadata(), HttpStatus.OK);
	}
	
	
	/**
	 * Metodo che restituisce la media della dimensione dei file(eliminati o presenti) in KB
	 * @param file distingue se l'utente vuole la media dei file eliminati eliminati o no
	 * @param subfolder stringa che definisce se fare la media di una sottocartella in particolare o di quella principale
	 * @return media in formato double
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka
	 */
	@RequestMapping(value="/statistics/media", method = RequestMethod.GET)
	public ResponseEntity<Object> getMedia(@RequestParam (name = "file") String file,
                                           @RequestParam (name="subfolder", defaultValue = "")String subfolder) throws SubfolderNotFoundException{
		if(file.equals("file"))
		     return new ResponseEntity<>("La dimensione media dei file è: "+String.valueOf(Statistics.media(Storage.downloadFile(), subfolder))+" MB", HttpStatus.OK);
		else if(file.equals("deleted"))
			return new ResponseEntity<>("La dimensione media dei file è: "+String.valueOf(Statistics.mediaDeletedFile(DeletedFiles.downloadDeletedFiles(), subfolder))+" MB", HttpStatus.OK);
		else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	
	/**
	 * Metodo che restituisce il file più grande alla rotta "/statistics/max"
	 * @param file distingue se l'utente vuole la media dei file eliminati eliminati o no
	 * @param subfolder stringa che definisce se fare la media di una sottocartella in particolare o di quella principale
	 * @return il file più grande di dimensione
	 * @throws SubfolderNotFoundException
	 * @author Enrico gregorini
	 * @author Daniele Gjeka/
	 */
	@RequestMapping(value="/statistics/max", method = RequestMethod.GET)
	public ResponseEntity<Object> getMax(@RequestParam (name = "file") String file,
                                         @RequestParam (name="subfolder", defaultValue = "")String subfolder) throws SubfolderNotFoundException{
		if(file.equals("file"))
		     return new ResponseEntity<>(Statistics.maxDimFile(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(file.equals("deleted"))
			return new ResponseEntity<>(Statistics.maxDimFileDeleted(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);
		else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	
	/**
	 * Metodo che restituisce il file più piccolo alla rotta "/statistics/min"
	 * @param file distingue se l'utente vuole la media dei file eliminati eliminati o no
	 * @param subfolder stringa che definisce se fare la media di una sottocartella in particolare o di quella principale
	 * @return il file più piccolo di dimensione
	 * @throws SubfolderNotFoundException 
	 * @author Enrico gregorini
	 * @author Daniele Gjeka/
	 */
	@RequestMapping(value="/statistics/min", method = RequestMethod.GET)
	public ResponseEntity<Object> getMin(@RequestParam (name = "file") String file,
			                             @RequestParam (name="subfolder", defaultValue = "")String subfolder) throws SubfolderNotFoundException{
		if(file.equals("file"))
		      return new ResponseEntity<>(Statistics.minDimFile(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(file.equals("deleted"))
			return new ResponseEntity<>(Statistics.minDimFileDeleted(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);	
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	
	
	/**
	 * Metodo che restituisce il numero di file per ogni formato presente nella sottocartella scelta dall'utente
	 * @param file distingue se l'utente vuole la media dei file eliminati eliminati o no
	 * @param subfolder stringa che definisce se fare la media di una sottocartella in particolare o di quella principale
	 * @return HashMap<String,Integer> tabella in cui per ogni tipo di file è presente il numero di Record di quel formato 
	 * @throws SubfolderNotFoundException
	 */
	@RequestMapping(value="/statistics/type", method = RequestMethod.GET)
	public ResponseEntity<Object> getType(@RequestParam (name="file") String file,
			                              @RequestParam (name="subfolder", defaultValue = "")String subfolder) throws SubfolderNotFoundException{
		if(file.equals("file"))
		     return new ResponseEntity<>(Statistics.getFileType(Storage.downloadFile(), subfolder), HttpStatus.OK);
		else if(file.equals("deleted"))
			return new ResponseEntity<>(Statistics.getDeletedFileType(DeletedFiles.downloadDeletedFiles(), subfolder), HttpStatus.OK);	
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
	
	
	
	/**
	 * Metodo che effettua il restore dei file scelti dall'utente
	 * @param filter RequestBody della richiesta POST
	 * @return messaggio che conferma se l'operazione è avvenuta con successo oppure no
	 */
	@RequestMapping(value="/restore", method = RequestMethod.POST)
	public ResponseEntity<String> FileRestore(@RequestBody String filter){
	    if(service.RestoreFile(filter))
	         return new ResponseEntity<>("File restored", HttpStatus.OK);
	    else return new ResponseEntity<>("L'operazione non è avvenuta con successo!", HttpStatus.BAD_REQUEST);
	}
	
	
	/**
	 * Metodo che restituisce la lista di Record con lo specifico filtro
	 * @param filter RequestBody della richiesta POST
	 * @param file distingue se l'utente vuole la media dei file eliminati eliminati o no
	 * @return lista di file filtrata
	 */
	@RequestMapping(value="/filter", method = RequestMethod.POST)
	public ResponseEntity<Object> TypeRestore(@RequestBody String filter, @RequestParam (name = "file")String file){
		if(file.equals("file"))
			return new ResponseEntity<>(service.getFilteredFile(filter), HttpStatus.OK);	
		else if(file.equals("deleted"))
			return new ResponseEntity<>(service.getFilteredFileDeleted(filter), HttpStatus.OK);
	    else return new ResponseEntity<>("Param not valid", HttpStatus.OK);
	}
		
}
