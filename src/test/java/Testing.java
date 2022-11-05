import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import control.FileUtils;

public class Testing {

	public static void main(String[] args) {
		 
		Map<String, String> data = FileUtils.getAllEmail();
		
		String oggetto = "Test Domenico Lubrano";
		String percorso = "C:\\Users\\Fin\\Desktop\\buste\\";

		
		for (Entry<String, String> entry : data.entrySet()) {
		    String list = entry.getKey();
		    

		    File myObj = new File(percorso + "1- " + oggetto + " - " + list + ".pdf");
		    
		    try {
				if (myObj.createNewFile()) {
				    System.out.println("File created: " + myObj.getName());
				  }
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		

		
	}

}

