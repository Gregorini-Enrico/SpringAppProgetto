package gg.project.DBapp.service;

import java.util.*;

import gg.project.DBapp.Storage.*;
import gg.project.DBapp.model.Metadata;
import gg.project.DBapp.model.Record;
import gg.project.DBapp.model.RecordDeleted;

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
}
 