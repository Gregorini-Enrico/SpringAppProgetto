package gg.project.DBapp.Filter;

import java.util.*;

import gg.project.DBapp.model.RecordDeleted;

public class DateFilter {
	
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
			if(records.get(rd).before(date)) 
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
	public static List<RecordDeleted> betweenDate(List<RecordDeleted> files, String date){ //date = [01/08/2020, 01/09/2020]
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);    //converto tutte le stringhe del campo client_modified in una classe Date asscociandolo all'oggetto RecordDeleted corrispondente
		Date Datainizio = new Date(), Datafine = new Date();
		
		String[] SDate = date.split(","); //array di stringhe per splittare le due date
		String inizio = SDate[0].substring(1), fine = SDate[1].substring(1, SDate[1].length()-1); //variabili che splittano le due date passate dall'utente
		
		String[] dmy1 = inizio.split("/");  //splitto inizio per ottenere i valori per settare Datainizio (oggetto Date)
		Datainizio.setDate(Integer.parseInt(dmy1[0]));
		Datainizio.setMonth(Integer.parseInt(dmy1[1])-1);  //-1 perchè il mese viene sempre aumentato di 1
		Datainizio.setYear(Integer.parseInt(dmy1[2])-1900);   //-1900 perchè l'anno viene sempre aumentato di 1900 nella classe Date
		String[] dmy2 = fine.split("/");    //splitto fine per ottenere i valori per settare Datafine (oggetto Date)   
		Datafine.setDate(Integer.parseInt(dmy2[0]));
		Datafine.setMonth(Integer.parseInt(dmy2[1])-1);    //-1 perchè il mese viene sempre aumentato di 1
		Datafine.setYear(Integer.parseInt(dmy2[2])-1900);   //-1900 perchè l'anno viene sempre aumentato di 1900 nella classe Date
		
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();  //inizializzo la lista che filtrata che poi ritornerò 
		
		for(RecordDeleted rd: files)
			if(records.get(rd).after(Datainizio) && records.get(rd).before(Datafine))   //controllo quali file sono stati modificati nell'intervallo scelto dall'utente
				filtered.add(rd);   //aggiungo alla lista filtrata quei file che sono all'interno dell'intervallo
		return filtered;   //ritorno la lista filtrata di file
	}
}
