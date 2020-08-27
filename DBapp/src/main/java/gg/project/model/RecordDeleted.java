package gg.project.model;

public class RecordDeleted extends Record{
	
	private String tag;
	private String name;
	private String path_lower;
	
	public RecordDeleted() {}

	public RecordDeleted(String name, String tag, String path_lower) {
		super(name, tag, path_lower);
	}
	
		
}
