package gg.project.DBapp.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.DBapp.model.*;
import gg.project.DBapp.model.Record;

public class DeletedFiles {
	
	/**
	 * Metodo che restituisce tutti i file eliminati con tutti i vari attributi dati dalla chiamata list_revisions
	 * 
	 * @return ArrayList<RecordDeleted> lista di tutti i file eliminati
	 */
	public static ArrayList<RecordDeleted> downloadDeletedFiles() {
		
		ArrayList<DeletedParser> dp = new ArrayList<DeletedParser>();
		ArrayList<Record> records = Storage.download();
		ArrayList<RecordDeleted> dfiles = new ArrayList<RecordDeleted>();
		for(Record r:records) {
			if(r.getTag().equals("deleted")) {
				//dfiles.add(((RecordDeleted)r));
				RecordDeleted rd = new RecordDeleted();
				rd.setName(r.getName());
				rd.setPath_lower(r.getPath_lower());
				rd.setTag(r.getTag());
				dfiles.add(rd);
			}
		}

		String url = "https://api.dropboxapi.com/2/files/list_revisions";
		for(RecordDeleted rd:dfiles) {
		try {
			
			if(rd.getPath_lower().contains(".")) {  //serve per distinguere i file(che hanno sempre un formato quindi con il .) dalle cartelle che non hanno il punto
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization",
					"Bearer TTT_mp4F8uIAAAAAAAAAAfg7FoYwmgEPuELIrV7zBJvObmJE_0MO9HTvN1uB2SB7");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"    \"path\": \"/"+rd.getPath_lower().substring(1)+"\",\r\n" + 
					"    \"mode\": \"path\",\r\n" + 
					"    \"limit\": 10\r\n" + 
					"}";
			
            
			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
            
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) 
					data += line;
			} finally {
				in.close();
			}
            
			ObjectMapper obj = new ObjectMapper();
			dp.add(obj.readValue(data, DeletedParser.class));
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
		return Data.getRecordsDeleted(dp);	
  }
}
