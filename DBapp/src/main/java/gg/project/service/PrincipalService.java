package gg.project.service;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import gg.project.Storage.Storage;
import gg.project.model.Record;

@org.springframework.stereotype.Service
public class PrincipalService {

	public List<Record> getRecords(){
		return Storage.download();
	}
}
