package gg.project.model;

public class RecordFile extends Record{
	
	private String tag;
	private String name;
	private String path_lower;
	private String id;
	private String client_modified;
	private String rev;
	private int size;	
	
	/**
	 * Costruttore senza parametri
	 */
	public RecordFile() {}
	
	public RecordFile (String name, String tag, String path_lower, String id, String client_modified, String rev, int size) 
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
