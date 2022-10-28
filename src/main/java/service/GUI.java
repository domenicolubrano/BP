package service;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import java.util.List;

import control.FileUtils;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI implements ActionListener {
  //  private int clicks = 0;
    private JLabel label = new JLabel();
    private JLabel errorLabel = new JLabel();
    private JFrame frame = new JFrame();

    public GUI() {

        // the clickable button
        JButton button = new JButton("Manda Buste Paga presenti nella cartella");
        button.addActionListener(this);

        // the panel with the button and text

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);
        panel.add(errorLabel);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Buste Paga");
        frame.pack();
        frame.setVisible(true);
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e) {

    	
    	
        frame.setVisible(true);
        
        try {
        	
        	List<String> files = FileUtils.getFileName(new File("D:\\eclipse-workspace-ee\\3em\\buste"));
        	
        	for (String file : files) {
        		
        		String oggetto = FileUtils.getOggettoMail(file);
        		SendMail.send("d.lubranolobianco@3em.it", oggetto);
			}
        	
        	
        	
        	
        	//get nomi da file
        	
        	SendMail.send("d.lubranolobianco@3em.it", "Test");
			
		} catch (AddressException e1) {
			
			e1.printStackTrace();
			errorLabel.setText("Errore nell'inviare le buste paga");
		} catch (MessagingException e1) {
			errorLabel.setText("Errore nell'inviare le buste paga");
			e1.printStackTrace();
		}
        label.setText("BP inviate" /*+ clicks*/);
    }
}
