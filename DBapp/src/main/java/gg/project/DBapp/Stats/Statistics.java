package gg.project.DBapp.Stats;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.*;

import gg.project.DBapp.model.*;

public class Statistics {

	private static List<String> allStats = new ArrayList<String>();
	
	public Statistics() {
		allStats.add("media dimensioni");
		allStats.add("tipo di file");
	}
	
	public static List<String> getAllStats(){
		return allStats;
	}
	
	/**
	 * Metodo per calcolare la media delle dimensioni dei file presenti
	 * @param records lista dei Record da analizzare
	 * return float media della dimensione dei file
	 */
	public static double media(List<RecordFile> records) {
		double s = 0;
		for(RecordFile f:records) {
			double KB = f.getSize()/1024;
			s += KB;
		}
		return s/records.size(); //ritorna la media 
	}
	
	/**
	 * Metodo per calcolare la media delle dimensioni dei file eliminati
	 * @param records lista dei Record da analizzare
	 * return float media della dimensione dei file
	 */
	public static double mediaDeletedFile(List<RecordDeleted> Drecords) {
		double s = 0;
		for(RecordDeleted f:Drecords){
			double KB = f.getSize()/1024; //calcolo la dimensione in KB
			s += KB;
		}
		return s/Drecords.size(); //ritorna la media in KB
	}
	
	public static RecordFile maxDimFile(List<RecordFile> records) {
		RecordFile max = new RecordFile();
		for(RecordFile r:records) 
			if(r.getSize()>max.getSize())
				max = r; 
		return max; //ritorno il file con dimensione massima
	}
	
	public static RecordDeleted maxDimFileDeleted(List<RecordDeleted> Drecords) {
		RecordDeleted max = new RecordDeleted();
		for(RecordDeleted r:Drecords) 
			if(r.getSize()>max.getSize())
				max = r; //calcolo la dimensione in B
		return max; //ritorno il file con dimensione massima in B
	}
	
	public static HashMap<String, Integer> getFileType(List<RecordFile> records){
		HashMap<String, Integer> type = new HashMap<String, Integer>();
		String[] format = new String[2];
		for(Record r:records) {
			int i=0;
			format = r.getName().split(".");
			if(type.containsKey(format[format.length]))
				i = type.get(format[format.length])+1;
			else type.put(format[format.length], i++);
		}
		return type;
	}
}
 