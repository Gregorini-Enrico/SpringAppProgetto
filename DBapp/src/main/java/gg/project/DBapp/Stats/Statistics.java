package gg.project.DBapp.Stats;

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
	public static float media(List<RecordFile> records) {
		float s = 0;
		for(RecordFile f:records) 
			s += f.getSize();
		return s/records.size(); //ritorna la media 
	}
	
	/**
	 * Metodo per calcolare la media delle dimensioni dei file eliminati
	 * @param records lista dei Record da analizzare
	 * return float media della dimensione dei file
	 */
	public static float mediaDeletedFile(List<RecordDeleted> Drecords) {
		float s = 0;
		for(RecordDeleted f:Drecords){
			int KB = f.getSize()/1024;
			s += KB;
		}
		return s/Drecords.size(); //ritorna la media 
	}
	
	public static int maxDimFile(List<RecordFile> records) {
		int max = 0;
		for(RecordFile r:records) 
			if(r.getSize()>max)
				max = r.getSize()/1024; //calcolo la dimensione in KB
		return max; //ritorno il file con dimensione massima in KB
	}
	
	public static int maxDimFileDeleted(List<RecordDeleted> Drecords) {
		int max = 0;
		for(RecordDeleted r:Drecords) 
			if(r.getSize()>max)
				max = r.getSize()/1024; //calcolo la dimensione in KB
		return max; //ritorno il file con dimensione massima in KB
	}
}
