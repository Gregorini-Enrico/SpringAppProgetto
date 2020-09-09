package gg.project.DBapp.Filter;

import java.util.*;

import gg.project.DBapp.Storage.DeletedFiles;
import gg.project.DBapp.model.RecordDeleted;

public class DateFilter {
	
	private String client_modified = "2020-08-22T07:29:01Z";

	@SuppressWarnings("deprecation")
	public static HashMap<RecordDeleted,Date> StringToDateDeleted(List<RecordDeleted> records){
		HashMap<RecordDeleted,Date> files = new HashMap<RecordDeleted,Date>();
		Date data = null; 
		for(RecordDeleted rd:records) {
			 int anno = Integer.parseInt(rd.getClient_modified().substring(0, 3));
			 int mese = Integer.parseInt(rd.getClient_modified().substring(5, 6));
			 int giorno = Integer.parseInt(rd.getClient_modified().substring(8, 9));
			 int ora = Integer.parseInt(rd.getClient_modified().substring(11, 12));
			 int minuto = Integer.parseInt(rd.getClient_modified().substring(14, 15));
 			 data = new Date();
 			 data.setYear((int)anno);
 			 data.setMonth(mese);
 			 data.setDate(giorno);
 			 data.setHours(ora);
 			 data.setMinutes(minuto);
 			 files.put(rd, data);
		}
		return files;
	}
	
	public static List<RecordDeleted> beforeDate(List<RecordDeleted> files, Date data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(data)) 
				filtered.add(rd);
		return filtered;
	}
	
	public static List<RecordDeleted> afterDate(List<RecordDeleted> files, Date data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(data)) 
				filtered.add(rd);
		return filtered;
	}
	
	public static List<RecordDeleted> betweenDate(List<RecordDeleted> files, Date inizio, Date fine){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(inizio) && records.get(rd).before(fine)) 
				filtered.add(rd);
		return filtered;
	}
}
