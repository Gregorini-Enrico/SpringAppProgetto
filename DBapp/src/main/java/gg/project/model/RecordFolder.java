package gg.project.model;

public class RecordFolder {
	
	private String tag;
	private String name;
	private String path;
	private String id;
	
	public RecordFolder() {
		
	}
	
	public RecordFolder (String tag, String name, String path, String id) {
		this.tag = tag;
		this.name = name;
		this.path = path;
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
