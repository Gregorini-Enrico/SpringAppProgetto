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
			 int anno = Integer.parseInt(rd.getClient_modified().substring(0, 4));
			 int mese = Integer.parseInt(rd.getClient_modified().substring(6, 7));
			 int giorno = Integer.parseInt(rd.getClient_modified().substring(8, 10));
			 int ora = Integer.parseInt(rd.getClient_modified().substring(12, 13));
			 int minuto = Integer.parseInt(rd.getClient_modified().substring(14, 16));
 			 data = new Date();
 			 data.setYear(anno-1900);
 			 data.setMonth(mese-1);
 			 data.setDate(giorno);
 			 data.setHours(ora);
 			 data.setMinutes(minuto);
 			 files.put(rd, data);
		}
		return files;
	}
	
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> beforeDate(List<RecordDeleted> files, String data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(date)) 
				filtered.add(rd);
		return filtered;
	}
	
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> afterDate(List<RecordDeleted> files, String data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(date)) 
				filtered.add(rd);
		return filtered;
	}
	
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> betweenDate(List<RecordDeleted> files, String inizio, String fine){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		Date Datainizio = new Date(), Datafine = new Date();
		String[] dmy1 = inizio.split("/");
		Datainizio.setDate(Integer.parseInt(dmy1[0]));
		Datainizio.setMonth(Integer.parseInt(dmy1[1])-1);
		Datainizio.setYear(Integer.parseInt(dmy1[2]));
		String[] dmy2 = fine.split("/");
		Datafine.setDate(Integer.parseInt(dmy2[0]));
		Datafine.setMonth(Integer.parseInt(dmy2[1])-1);
		Datafine.setYear(Integer.parseInt(dmy2[2]));
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();
		for(RecordDeleted rd: files)
			if(records.get(rd).after(Datainizio) && records.get(rd).before(Datafine)) 
				filtered.add(rd);
		return filtered;
	}
}
