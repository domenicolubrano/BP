package control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	public List<String> getFileName(final File folder) {
		List<String> nomiFile = new ArrayList<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	getFileName(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	            nomiFile.add(fileEntry.getName());
	        }
	    }
	    return nomiFile;
	}
	
	public String getOggettoMail(String file){		
		String[] ogg = file.split("-");
		
	
		return "BP " + ogg[1];
	}
	
	
//	public String getJarPath() {
//	String jarPath = "";
//	try {
//		jarPath = Main.class
//		          .getProtectionDomain()
//		          .getCodeSource()
//		          .getLocation()
//		          .toURI()
//		          .getPath();
//	} catch (URISyntaxException e) {
//		e.printStackTrace();
//	}
//	return jarPath;
//}



}
