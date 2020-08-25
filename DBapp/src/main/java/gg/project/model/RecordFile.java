package gg.project.model;

public class RecordFile {
	
	private String tag;
	private String name;
	private String path;
	private String id;
	private String client_modified;
	private String server_modified;
	private String rev;
	private int size;
	
	public RecordFile (String tag, String name, String path, String id,
			           String client_modified, String server_modifies, String rev, int size) 
	{
		this.tag = tag;
		this.name = name;
		this.path = path;
		this.id = id;
		this.client_modified = client_modified;
		this.server_modified = server_modifies;
		this.rev = rev;
		this.size = size;
		
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

	public String getClient_modified() {
		return client_modified;
	}

	public void setClient_modified(String client_modified) {
		this.client_modified = client_modified;
	}

	public String getServer_modified() {
		return server_modified;
	}

	public void setServer_modified(String server_modified) {
		this.server_modified = server_modified;
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
