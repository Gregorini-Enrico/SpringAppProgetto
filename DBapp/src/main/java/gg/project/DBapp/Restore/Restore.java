package gg.project.DBapp.Restore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import gg.project.DBapp.model.*;

/**
 * Classe per effettuare il restore dei file scelti dall'utente 
 * @author Enrico Gregorini
 * @author Daniele Gjeka 
 */
public class Restore {
	
	/**
	 * Metodo che effettua la chiamata POST alla rotta https://api.dropboxapi.com/2/files/restore per il restore dei file
	 * a questo metodo viene passata la lista già filtrata dei file che si vogliono ripristinare
	 * @param FilteredList lista filtrata di file eliminati
	 * @return true se l'operazione è avvenuta con successo, false se non è avvenuta con successo
	 * @author Enrico Gregorini
     * @author Daniele Gjeka
	 */
	public static boolean restore(List<RecordDeleted> FilteredList) {
		
		String url = "https://api.dropboxapi.com/2/files/restore";
		String data = ""; 
		String line = "";
		try {
            for(RecordDeleted rd:FilteredList) {
			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization",
					"Bearer TTT_mp4F8uIAAAAAAAAAAfg7FoYwmgEPuELIrV7zBJvObmJE_0MO9HTvN1uB2SB7");
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			String jsonBody = "{\r\n" + 
					"   \"path\": \"/"+rd.getPath_lower().substring(1)+"\",\r\n" +
					"	\"rev\": \""+rd.getRev()+"\"\r\n" + 
					"}";
            

			try (OutputStream os = openConnection.getOutputStream()) {
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			
			InputStream in = openConnection.getInputStream();

			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;       //tutti i dati presi dall'API in stringa
				}
			} finally {
				in.close();
			}
        }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(data.isEmpty()) return false;
		else return true;
		}
	
}
