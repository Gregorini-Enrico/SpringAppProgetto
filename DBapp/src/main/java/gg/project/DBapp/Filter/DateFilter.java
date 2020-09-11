package gg.project.DBapp.Filter;

import java.util.*;

import gg.project.DBapp.model.*;
import gg.project.DBapp.Exception.*;

public class DateFilter {
	
	/**
	 * Metodo per convertire tutti i campi client_modified da String a Date associandoli al corrispondente file eliminato
	 * @param records lista di tutti i file eliminati
	 * @return files HashMap<RecordDeleted,Date> record associato a una variabile Date che corrisponde al campo client_modified
	 */
	@SuppressWarnings("deprecation")
	public static HashMap<RecordDeleted,Date> StringToDateDeleted(List<RecordDeleted> records){
		HashMap<RecordDeleted,Date> files = new HashMap<RecordDeleted,Date>();
		Date data = null; 
		for(RecordDeleted rd:records) {
			 int anno = Integer.parseInt(rd.getClient_modified().substring(0, 4));        //prendo le prime 4 lettere del campo client_modified che corrispondo all'anno e le converto in intero
			 int mese = Integer.parseInt(rd.getClient_modified().substring(6, 7));        //prendo le lettere del campo client_modified che corrispondo al mese e le converto in intero
			 int giorno = Integer.parseInt(rd.getClient_modified().substring(8, 10));     //prendo le lettere del campo client_modified che corrispondo al giorno e le converto in intero
			 int ora = Integer.parseInt(rd.getClient_modified().substring(12, 13));       //prendo le lettere del campo client_modified che corrispondo all'ora e le converto in intero
			 int minuto = Integer.parseInt(rd.getClient_modified().substring(14, 16));    //prendo le lettere del campo client_modified che corrispondo al minuto e le converto in intero
 			 data = new Date();
 			 data.setYear(anno-1900);           //setto l'anno della data con anno sottraendo 1900 perchè nella classe Date viene sempre sommato 1900
 			 data.setMonth(mese-1);             //setto il mese della data con mese sottraendo 1 perchè nella classe Date viene sempre sommato 1
 			 data.setDate(giorno);              //setto il giorno della data con giorno
 			 data.setHours(ora);                //setto l'ora della data con ora
 			 data.setMinutes(minuto);           //setto il minuto della data con minuto
 			 files.put(rd, data);            //inserisco nell'HashMap il record analizzato con la corrispondete data
		}
		return files;            //ritorno l'HashMap dei file eliminati con le loro relative date di modifica in versione Date
	}
	
	/**
	 * Metodo per convertire tutti i campi client_modified da String a Date associandoli al corrispondente file 
	 * @param records lista di tutti i file
	 * @return files HashMap<RecordFile,Date> record associato a una variabile Date che corrisponde al campo client_modified
	 */
	@SuppressWarnings("deprecation")
	public static HashMap<RecordFile,Date> StringToDateFile(List<RecordFile> records){  //client_modified = "2020-08-24T07:42:07Z"
		HashMap<RecordFile,Date> files = new HashMap<RecordFile,Date>();
		Date data = null; 
		for(RecordFile rd:records) { 
			 int anno = Integer.parseInt(rd.getClient_modified().substring(0, 4));      //prendo le prime 4 lettere del campo client_modified che corrispondo all'anno e le converto in intero
			 int mese = Integer.parseInt(rd.getClient_modified().substring(6, 7));      //prendo le lettere del campo client_modified che corrispondo al mese e le converto in intero
			 int giorno = Integer.parseInt(rd.getClient_modified().substring(8, 10));   //prendo le lettere del campo client_modified che corrispondo al giorno e le converto in intero
			 int ora = Integer.parseInt(rd.getClient_modified().substring(12, 13));     //prendo le lettere del campo client_modified che corrispondo all'ora e le converto in intero
			 int minuto = Integer.parseInt(rd.getClient_modified().substring(14, 16));  //prendo le lettere del campo client_modified che corrispondo al minuto e le converto in intero
 			 data = new Date();
			 data.setYear(anno-1900);           //setto l'anno della data con anno sottraendo 1900 perchè nella classe Date viene sempre sommato 1900
 			 data.setMonth(mese-1);             //setto il mese della data con mese sottraendo 1 perchè nella classe Date viene sempre sommato 1
 			 data.setDate(giorno);              //setto il giorno della data con giorno
 			 data.setHours(ora);                //setto l'ora della data con ora
 			 data.setMinutes(minuto);           //setto il minuto della data con minuto 
 			 files.put(rd, data);      //inserisco nell'HashMap il record analizzato con la corrispondete data
		}
		return files;   //ritorno l'HashMap dei file presenti con le loro relative date di modifica in versione Date 
	}
	
	/**
	 * Metodo per filtrare tutti i file modificati prima della data passata dall'utente
	 * @param files HashMap di RecordDeleted con associato la data ad esso corrispondente
	 * @param data passata dall'utente
	 * @return filtered lista filtrata dei file eliminati
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> beforeDate(List<RecordDeleted> files, String data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();  //inizializzo la lista filtrata
		
		for(RecordDeleted rd: files)
			if(records.get(rd).before(date))       //controllo se la data del file è antecedente alla data inserita dall'utente
				filtered.add(rd);                  //se si, inserisco il file nella lista filtrata
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();     //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;
	}
	
	/**
	 * Metodo per filtrare tutti i file modificati prima della data passata dall'utente
	 * @param files HashMap di RecordFile con associato la data ad esso corrispondente
	 * @param data passata dall'utente
	 * @return filtered lista filtrata dei file presenti
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordFile> beforeDateFile(List<RecordFile> files, String data){
		HashMap<RecordFile,Date> records = DateFilter.StringToDateFile(files);
		
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		
		ArrayList<RecordFile> filtered = new ArrayList<RecordFile>();   //inizializzo la lista filtrata
		
		for(RecordFile rd: files)
			if(records.get(rd).before(date))       //controllo se la data del file è antecedente alla data inserita dall'utente
				filtered.add(rd);                  //se si, inserisco il file nella lista filtrata
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();     //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;
	}
	
	
	
	/**
	 * Metodo per filtrare tutti i file modificati dopo della data passata dall'utente
	 * @param files HashMap di RecordDeleted con associato la data ad esso corrispondente
	 * @param data passata dall'utente
	 * @return filtered lista filtrata dei file eliminati
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> afterDate(List<RecordDeleted> files, String data){
		HashMap<RecordDeleted,Date> records = DateFilter.StringToDateDeleted(files);
		
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();   //inizializzo la lista filtrata
		
		for(RecordDeleted rd: files)
			if(records.get(rd).after(date))    //controllo se la data del file è conseguente alla data inserita dall'utente
				filtered.add(rd);              //se si, inserisco il file nella lista filtrata
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();    //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;
	}
	
	/**
	 * Metodo per filtrare tutti i file modificati dopo della data passata dall'utente
	 * @param files HashMap di RecordFile con associato la data ad esso corrispondente
	 * @param data passata dall'utente
	 * @return filtered lista filtrata dei file presenti
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordFile> afterDateFile(List<RecordFile> files, String data){
		HashMap<RecordFile,Date> records = DateFilter.StringToDateFile(files);
		Date date = new Date();
		String[] dmy = data.split("/");
		date.setDate(Integer.parseInt(dmy[0]));
		date.setMonth(Integer.parseInt(dmy[1])-1);
		date.setYear(Integer.parseInt(dmy[2])-1900);
		
		ArrayList<RecordFile> filtered = new ArrayList<RecordFile>();   //inizializzo la lista filtrata
		
		for(RecordFile rd: files)
			if(records.get(rd).after(date)) //controllo se la data del file è conseguente alla data inserita dall'utente
				filtered.add(rd);           //se si, inserisco il file nella lista filtrata
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();    //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;
	}
	
	
	/**
	 * Metodo per filtrare tutti i file modificati nell'intervallo delle due date passate dall'utente
	 * @param files HashMap di RecordFile con associato la data ad esso corrispondente
	 * @param le due date passate dall'utente
	 * @return filtered lista filtrata dei file eliminati
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordDeleted> betweenDate(List<RecordDeleted> files, String date){ //es. date = [01/08/2020, 01/09/2020]
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
		
		if(Datafine.before(Datainizio)) throw new DateFilterIncorrectException();    //se Datafine è precedente a Datainizio lancio un'eccezione
		
		ArrayList<RecordDeleted> filtered = new ArrayList<RecordDeleted>();  //inizializzo la lista che filtrata che poi ritornerò 
		
		for(RecordDeleted rd: files)
			if(records.get(rd).after(Datainizio) && records.get(rd).before(Datafine))   //controllo quali file sono stati modificati nell'intervallo scelto dall'utente
				filtered.add(rd);   //aggiungo alla lista filtrata quei file che sono all'interno dell'intervallo
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();    //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;   //ritorno la lista filtrata di file
	}
	
	/**
	 * Metodo per filtrare tutti i file modificati nell'intervallo delle due date passate dall'utente
	 * @param files HashMap di RecordFile con associato la data ad esso corrispondente
	 * @param le due date passate dall'utente
	 * @return filtered lista filtrata dei file presenti
	 */
	@SuppressWarnings("deprecation")
	public static List<RecordFile> betweenDateFile(List<RecordFile> files, String date){//es. date = [01/08/2020, 01/09/2020]		
		HashMap<RecordFile,Date> records = DateFilter.StringToDateFile(files);    //converto tutte le stringhe del campo client_modified in una classe Date asscociandolo all'oggetto RecordDeleted corrispondente
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
		
		if(Datafine.before(Datainizio)) throw new DateFilterIncorrectException();    //se Datafine è precedente a Datainizio lancio un'eccezione
		
		ArrayList<RecordFile> filtered = new ArrayList<RecordFile>();  //inizializzo la lista che filtrata che poi ritornerò 
		
		for(RecordFile rd: files)
			if(records.get(rd).after(Datainizio) && records.get(rd).before(Datafine))   //controllo quali file sono stati modificati nell'intervallo scelto dall'utente
				filtered.add(rd);   //aggiungo alla lista filtrata quei file che sono all'interno dell'intervallo
		if(filtered.isEmpty())
			throw new FileInDateNotFoundException();  //se la lista filtrata è vuota,lancio un'eccezione
		return filtered;   //ritorno la lista filtrata di file
	}
}
