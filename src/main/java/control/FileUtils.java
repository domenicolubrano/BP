package control;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashDocAttributeSet;

import service.GUI;

public class FileUtils {
	
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
			return "BP";
		}
		
	}
	
	
    /**
	* 
	* @param nome file
	* @return nominativo
	*/
	public static String getNominativo(String file){		
		String[] nome = file.split("-");
	
	
		return nome[2];
	}
	
	/**
	 * 
	 * @param nome
	 * @return email dell nome
	 */
	public static String getEmail(String nome) {
		Map<String, String> data = new HashMap<String, String>();
		
		return data.get(nome);
	}


}
