package gg.project.model;

public class RecordFolder extends Record{
	

	private String id;
	
	public RecordFolder() {	}
	
	public RecordFolder (String tag, String name, String path_lower, String id) {
		super(name, tag, path_lower);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
