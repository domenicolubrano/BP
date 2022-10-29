package control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	        	getFileName(fileEntry);
	        } else {
	        	if(!fileEntry.getName().contains(".exe") || !fileEntry.getName().contains(".jar")) {
	        		System.out.println(fileEntry.getName());
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
		
	
		return "BP " + ogg[1];
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
	


}
