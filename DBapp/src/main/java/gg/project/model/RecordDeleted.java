package gg.project.model;

public class RecordDeleted extends Record{
	
	private String id;
	private String client_modified;
	private String rev;
	private int size;
	
	public RecordDeleted() {}

	public RecordDeleted(String name, String tag, String path_lower) {
		super(name, tag, path_lower);
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
