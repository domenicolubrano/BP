package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			return "Busta Paga: " + ogg[1];
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
		String[] n = file.split("-");
		try {
			
			String nome = n[2].replace(".pdf", "");
			nome  = nome.trim();
			
			nome = Normalizer.normalize(nome, Normalizer.Form.NFD);
			nome = nome.replaceAll("[^\\p{ASCII}]", "");
			nome = nome.replaceAll("\\p{M}", "");
			
			GUI.logTextArea.append(" [INFO] ==> Il nome trovato e: " + nome + "\n\n");
			return nome;
		}catch(Exception e) {
			GUI.logTextArea.append(" [INFO] ==> Il nome trovato e: NULL\n\n");
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

	
	
	
	public static Map<String, String> getAllEmail() {
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return data;
	}
	
	
	
	
}
