package gg.project.DBapp.model;

/**
 * Classe che rappresenta i file presenti nella cartella dropbox
 * essa estende la classe Record
 * @author Enrico Gregorini
 * @author Daniele Gjeka 
 */
public class RecordFile extends Record{
	
	private String id;
	private String client_modified;
	private String rev;
	private double size;	
	
	/**
	 * Costruttore senza parametri
	 */
	public RecordFile() {}
	
	public RecordFile (String name, String tag, String path_lower, String id, String client_modified, String rev, double size) 
	{
		super(name, tag, path_lower);
		this.id = id;
		this.client_modified = client_modified;
		this.rev = rev;
		this.size = size;
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient_modified() {
		return client_modified;
	}

	public void setClient_modified(String client_modified) {
		this.client_modified = client_modified;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}
}
