package gg.project.model;

public class RecordDeleted {
	
	private String tag;
	private String name;
	private String path;
	
	public RecordDeleted (String tag, String name, String path) {
		this.tag = tag;
		this.name = name;
		this.path = path;

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

	
}
