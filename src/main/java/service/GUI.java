package service;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener {
  //  private int clicks = 0;
    private JLabel label = new JLabel();
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

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);
    }

    // process the button clicks
    public void actionPerformed(ActionEvent e) {

    	 label.setText("BP inviate" /*+ clicks*/);
    	
        frame.setVisible(true);
        
        try {
        	
        	//get nomi da file
        	
        	
			SendMail.send();
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
}
