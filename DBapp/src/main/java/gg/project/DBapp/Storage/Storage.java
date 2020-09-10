package gg.project.DBapp.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;
/*import java.net.URI;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.simple.parser.ParseException;*/

import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.DBapp.model.*;
import gg.project.DBapp.model.Record;

public class Storage {

	public static ArrayList<Record> download() {

		String url = "https://api.dropboxapi.com/2/files/list_folder";  //url per fare richiesta all'API dropbox
		Parser p = null;   //Parser che servirà poi per ottenere la richiesta formato JSON dell'API dropbox 
		try {

			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			//setto tutti i paramentri e la body della richiesta 
			openConnection.setRequestMethod("POST"); 
			openConnection.setRequestProperty("Authorization",
					"Bearer TTT_mp4F8uIAAAAAAAAAAfg7FoYwmgEPuELIrV7zBJvObmJE_0MO9HTvN1uB2SB7");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"    \"path\": \"\",\r\n" + 
					"    \"recursive\": true,\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": true,\r\n" + 
					"    \"include_mounted_folders\": true\r\n" + 
					"}";
			

			try (OutputStream os = openConnection.getOutputStream()) {  //faccio richiesta all'API dropbox
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			InputStream in = openConnection.getInputStream();

			String data = ""; 
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line; 							//tutti i dati presi dall'API in stringa
				}
			} finally {
				in.close();
			}
			
			ObjectMapper obj = new ObjectMapper();		//inizializzo ObjectMapper
			p = obj.readValue(data, Parser.class);		//Effettuo il parsing dei dati attraverso obj (passando data)
			 											//e inserisco i dati dentro il parser (parser.class)
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Data.getRecords(p);
	}
	
	
	
	public static ArrayList<RecordFile> downloadFile() {

		String url = "https://api.dropboxapi.com/2/files/list_folder";
		Parser p = null;
		try {

			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization",
					"Bearer TTT_mp4F8uIAAAAAAAAAAfg7FoYwmgEPuELIrV7zBJvObmJE_0MO9HTvN1uB2SB7");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"    \"path\": \"\",\r\n" + 
					"    \"recursive\": true,\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": true,\r\n" + 
					"    \"include_mounted_folders\": true\r\n" + 
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

				while ((line = buf.readLine()) != null) {
					data += line; 							//tutti i dati presi dall'API in stringa
				}
			} finally {
				in.close();
			}
			
			ObjectMapper obj = new ObjectMapper();		//inizializzo ObjectMapper
			p = obj.readValue(data, Parser.class);		//Effettuo il parsing dei dati attraverso obj (passando data)
			 											//e inserisco i dati dentro il parser (parser.class)
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Data.getOnlyFile(p);
	}
}
