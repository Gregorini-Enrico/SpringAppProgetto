package gg.project.DBapp.Restore;


import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import gg.project.DBapp.Storage.DeletedFiles;
import gg.project.DBapp.model.*;

public class Restore {
	
	public static void restore(String name) {
		
		ArrayList<RecordDeleted> dfiles = DeletedFiles.downloadDeletedFiles();

		String url = "https://api.dropboxapi.com/2/files/restore";
		RecordDeleted RecordtoRestore = null;
		for(RecordDeleted rd:dfiles) 
			if(rd.getName()==name)
				RecordtoRestore = rd;
	
		try {
            
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization",
					"Bearer TTT_mp4F8uIAAAAAAAAAAfg7FoYwmgEPuELIrV7zBJvObmJE_0MO9HTvN1uB2SB7");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"   \"path\": \"/"+RecordtoRestore.getPath_lower().substring(1)+"\",\r\n" +
					"	\"rev\": \""+RecordtoRestore.getRev()+"\"\r\n" + 
					"}";
			

			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		}

}
