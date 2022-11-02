package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashDocAttributeSet;

import service.GUI;

public class FileUtils {
	
	private static String urlFile = "https://raw.githubusercontent.com/domenicolubrano/BP/test/src/main/resources/mail.csv";
	
	
	/**
	 * 
	 * @param Percorso cartella
	 * @return lista contenente i nomi dei file
	 */
	public static List<String> getFileName(final File folder) {
		List<String> nomiFile = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	//getFileName(fileEntry); //prende i file nelle sottocartelle
	        } else {
	        	if(fileEntry.getName().contains(".exe") || fileEntry.getName().contains(".jar")) {
	        		// non fare nulla
	        	}else {
	        		System.out.println(fileEntry.getName());
	        		GUI.logTextArea.append(" [INFO] -> File Trovato: " + fileEntry.getName() + "\n");
		            nomiFile.add(fileEntry.getName());
	        	}
	            
	        }
	    }
	    
	    return nomiFile;
	}
	
	/**
	 * 
	 * @param nome file
	 * @return oggetto mail formattato BP Mese Anno
	 */
	public static String getOggettoMail(String file){		
		String[] ogg = file.split("-");
		
		try {
			return "BP " + ogg[1];
		}catch(Exception e) {
			return "Busta Paga";
		}
		
	}
	
	
    /**
	* 
	* @param nome file
	* @return nominativo
	*/
	public static String getNominativo(String file){		
		String[] nome = file.split("-");
		try {
			return nome[2];
		}catch(Exception e) {
			return "NULL";
		}
	
		
	}
	
	/**
	 * 
	 * @param nome
	 * @return email dell nome
	 */
	public static String getEmail(String nome) {
		URL url  = null;
		BufferedReader in = null;
		Map<String, String> data = new HashMap<String, String>();
		
		
		try {
			url = new URL(urlFile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				 String[] arr = inputLine.split(";"); 
				 data.put(arr[0], arr[1]);
			}
			
			in.close();
		} catch (MalformedURLException e) {
			GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
			e.printStackTrace();
			GUI.errore = true;
		} catch (IOException e) {
			GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
			e.printStackTrace();
			GUI.errore = true;
		}
		
		
		return data.get(nome);
	}


}
