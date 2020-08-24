package gg.project.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import gg.project.model.Data;
import gg.project.model.File;

public class Download {

	public List<File> Storing(String url){
		
	    Parser p = new Parser();
		try {
		URLConnection openConnection = new URL(url).openConnection();
		openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		InputStream in = openConnection.getInputStream();
		
		String data = "";
		String line = "";
		
		try {
			InputStreamReader inR = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( inR );
			  
			while ( ( line = buf.readLine() ) != null ) {
				data+= line;
			}
		} catch (IOException e) {
			System.out.println("Errore I/O...");	
		} finally {
			in.close();
		}
		
		ObjectMapper obj = new ObjectMapper();
		p = obj.readValue(data, Parser.class);	
        }
		catch(IOException e) {
			System.out.println("Errore I/O...");
		}
		return Data.getFile(p);
	}
}
