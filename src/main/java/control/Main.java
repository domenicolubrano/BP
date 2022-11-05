package control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import service.GUI;

public class Main{

	public static void main(String[] args) {
		
		// crea una cartella dove verranno spostate tutte le BP
		try {
			Files.createDirectories(Paths.get("./Inviate/"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		new GUI();
	}
	

}
