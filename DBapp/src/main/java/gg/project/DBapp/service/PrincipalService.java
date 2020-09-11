package gg.project.DBapp.service;

import java.util.*;

import gg.project.DBapp.Storage.*;
import gg.project.DBapp.model.Metadata;
import gg.project.DBapp.model.*;
import gg.project.DBapp.Restore.*;

@org.springframework.stereotype.Service
public class PrincipalService {

	public ArrayList<Record> getRecords(){
		return Storage.download();
	}
	
	public ArrayList<RecordDeleted> getDeletedFiles(){
		return DeletedFiles.downloadDeletedFiles();
	}
	
	public HashMap<String,String> getMetadata(){
		return Metadata.getFileMetadata();
	}
	
	public boolean RestoreFile(String body) {
		if(Restore.restore(FilterService.filtring(DeletedFiles.downloadDeletedFiles(), body))) return true;
		else return false;
	}
	
	public List<RecordDeleted> getFilteredFileDeleted(String filter){
		return FilterService.filtring(DeletedFiles.downloadDeletedFiles(), filter);
	}
	
	public List<RecordFile> getFilteredFile(String filter){
		return FilterService.filtringFile(Storage.downloadFile(), filter);
	}
	
	
}
 