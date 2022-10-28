package control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GestioreFile {
	
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
	


}
