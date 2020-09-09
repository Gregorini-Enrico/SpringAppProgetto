package gg.project.DBapp.service;

import java.util.*;

import gg.project.DBapp.Storage.*;
import gg.project.DBapp.model.Metadata;
import gg.project.DBapp.model.*;
import gg.project.DBapp.Filter.TypeFilter;
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
		
	public List<RecordDeleted> getTypeFileDeleted(String type){
		return TypeFilter.typeDeleted(DeletedFiles.downloadDeletedFiles(), type);
	}
	
	public List<RecordFile> getTypeFile(String type){
		return TypeFilter.type(Storage.downloadFile(), type);
	}
	
	
}
 