package service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import control.FileUtils;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
//  private int clicks = 0;
    //private static JLabel label = new JLabel();
    private static JFrame frame = new JFrame();
    private static JScrollPane pannelloScroll = new JScrollPane();
    public static JTextArea logTextArea = new JTextArea();
    private static JLabel labelTitolo = new JLabel();
    private static JSeparator separatore = new JSeparator();
    private static JLabel labelIstruzioni = new JLabel();
    private static JLabel labelIstruzioni1 = new JLabel();
    private static JLabel labelIstruzioni2 = new JLabel();
    private static JLabel labelSuccess = new JLabel();
    
    public static boolean errore = false;

    public GUI() {
    	
    	
    	// pannello
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(0, 1));

    	
    	// bottone invia BP
        JButton button = new JButton("Manda Buste Paga presenti nella cartella");
        //button.addActionListener(this);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBackground(new Color(22,158,255));
        button.setForeground(new Color(255,255,255));
        button.setFont(new Font("Arial", 1, 18));
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bottoneActionPerformed(evt);
            }
        });
        panel.add(button);
        
        // bottone salva log
        JButton saveLog = new JButton("Salva Log");
        saveLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveLog.setBackground(new Color(22,158,255));
        saveLog.setForeground(new Color(255,255,255));
        saveLog.setFont(new Font("Arial", 1, 18));
        saveLog.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        saveLog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveLogActionPerformed(evt);
            }
        });
        panel.add(saveLog);
        panel.add(separatore);
        

        // text area log
        logTextArea.setEditable(true);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        pannelloScroll.setViewportView(logTextArea);
        panel.add(pannelloScroll);
        
        
        // titolo
        labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitolo.setText("Invia Buste Paga");
        labelTitolo.setFont(new Font("Leelawadee UI", 1, 25));
        labelTitolo.setForeground(new Color(22,158,255));
        panel.add(labelTitolo);
        
        
        // istruzioni
        labelIstruzioni.setHorizontalAlignment(SwingConstants.CENTER);
        labelIstruzioni.setFont(new Font("Leelawadee UI", 1, 20));
        labelIstruzioni.setText("Inserire il file nella cartella contenente tutti i pdf e cliccare il pulsante \"Invia le buste paga nella cartella\"");
        panel.add(labelIstruzioni);

        labelIstruzioni1.setHorizontalAlignment(SwingConstants.CENTER);
        labelIstruzioni1.setFont(new Font("Leelawadee UI", 1, 20));
        labelIstruzioni1.setText("In automatico verranno inviate tramite mail, in caso di errori verranno mostrati nel riquadro in basso");
        panel.add(labelIstruzioni1);
        
        labelIstruzioni2.setHorizontalAlignment(SwingConstants.CENTER);
        labelIstruzioni2.setFont(new Font("Leelawadee UI", 1, 20));
        labelIstruzioni2.setText("Il pulsante \"Salva log\" e' utile in caso di errori, in modo che gli sviluppatori possano risolvere.");
        panel.add(labelIstruzioni2);
        
        
        // successo
        labelSuccess.setHorizontalAlignment(SwingConstants.CENTER);
        labelSuccess.setFont(new Font("Leelawadee UI", 1, 18));
        labelSuccess.setText("Buste Paga inviate correttamente!");
        labelSuccess.setForeground(new Color(0,255,0));
        labelSuccess.setVisible(false);
        panel.add(labelSuccess);
        
        // layout
        GroupLayout layout = new GroupLayout(panel);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(labelIstruzioni, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separatore)
                    .addComponent(button, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pannelloScroll, GroupLayout.Alignment.TRAILING)
                    .addComponent(labelTitolo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelIstruzioni1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelIstruzioni2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelSuccess, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(saveLog))
                 )
                .addContainerGap())
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelTitolo, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelIstruzioni, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelIstruzioni1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(labelIstruzioni2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addComponent(labelSuccess, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separatore, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button)
                .addGap(24, 24, 24)
                .addComponent(pannelloScroll, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                .addComponent(saveLog)
                .addContainerGap())
        );
        panel.setLayout(layout);
        
        // set icona
        ImageIcon img = new ImageIcon();
        try {
        	img = new ImageIcon(getClass().getResource("/resources/logo.png")); 
            
        }catch(NullPointerException e) {
        	img = new ImageIcon(".\\src\\main\\resources\\logo.png"); 
        }
        frame.setIconImage(img.getImage());
           
        
        // set up frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Buste Paga");
        frame.setMinimumSize(new Dimension(1250,730));
        frame.pack();
        frame.setVisible(true);
        
        
  
        
    }
    
    
    // invia BP premuto
    private void bottoneActionPerformed(ActionEvent e) {                                          
    	logTextArea.append("\n\n ============ [INIZIO] ============\n\n");
	        
	  
	    // get nomi da file
	    List<String> files = FileUtils.getFileName(new File("."));  // ricerca file
	    logTextArea.append("\n [INFO] -> Nessun altro file trovato\n\n");
	        
	    for (String file : files) {	
	    	
	        String oggetto = FileUtils.getOggettoMail(file);
	        String nominativo = FileUtils.getNominativo(file);
	        String email = FileUtils.getEmail(nominativo);
	        	
	        SendMail.send(email, oggetto);
	        logTextArea.append("\n [INFO] -> Invio email a " + email + "\n\n");
	    }
	    
	    

	    logTextArea.append("\n\n ============ [FINE] ============\n");
	    
	    
	    // controllo errori
	    if(errore == false) {
	    	labelSuccess.setText("Buste Paga inviate correttamente!");
	        labelSuccess.setForeground(new Color(0,255,0));
	    	labelSuccess.setVisible(true);
	    }else {
	    	labelSuccess.setText("Si sono verificati degli errori, controlla il log");
	    	labelSuccess.setForeground(new Color(255,0,0));
	    	labelSuccess.setVisible(true);
	    }
	    errore = false;
	    
    }
    
    
    
    // salva log premuto
    public void saveLogActionPerformed(ActionEvent e) {
    	String log = logTextArea.getText();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");  
    	LocalDateTime oggi = LocalDateTime.now();  

    	FileWriter logFile;
		try {
			logFile = new FileWriter("./log-" + formatter.format(oggi) + ".txt");
			logFile.write(log);
	    	logFile.close();
	    	JOptionPane.showMessageDialog(null, "Log Salvato");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Non e' stato possibile salvare il log...");
			logTextArea.append("\n\n[ERRORE] ==> " + e1.getMessage() + "\n\n");
			e1.printStackTrace();
			errore = true;
		}
	
    }

    
    
    
    // azione generale effettuata
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}


    
}
