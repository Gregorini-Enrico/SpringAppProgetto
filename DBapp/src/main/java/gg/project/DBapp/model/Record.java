package gg.project.DBapp.model;

/**
 * Superclasse Record che contiene gli attributi comuni a tutti i file/cartelle presenti nella cartella dropbox
 * le classi figlie poi erediteranno questi attributi e i metodi get e set associati
 * @author Enrico Gregorini
 * @author Daniele Gjeka
 */
public class Record {
        private String name;
        private String tag;
        private String path_lower;
        
        public Record() {}
        
		public Record(String name, String tag, String path_lower) {
			super();
			this.name = name;
			this.tag = tag;
			this.path_lower = path_lower;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public String getPath_lower() {
			return path_lower;
		}
		public void setPath_lower(String path_lower) {
			this.path_lower = path_lower;
		}        
        
}
