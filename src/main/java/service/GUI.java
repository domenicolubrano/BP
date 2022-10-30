package service;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
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
    

    public GUI() {
    	
    	
    	
    	JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        panel.setLayout(new GridLayout(0, 1));
    	
    	
    	
        JButton button = new JButton("Manda Buste Paga presenti nella cartella");
        //button.addActionListener(this);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bottoneActionPerformed(evt);
            }
        });
        panel.add(button);
        
        
        JButton saveLog = new JButton("Salva log");
        saveLog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveLogActionPerformed(evt);
            }
        });
        panel.add(saveLog);
        
        
        
        panel.add(separatore);
      //panel.add(label);
        

        // text area log
        logTextArea.setEditable(true);
        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        pannelloScroll.setViewportView(logTextArea);
        panel.add(pannelloScroll);
        //panel.add(logTextArea);
        
        
        // titolo
        labelTitolo.setFont(new Font("Dialog", 1, 24)); // NOI18N
        labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitolo.setText("Invia Buste Paga");
        panel.add(labelTitolo);
        
        
        // istruzioni
        labelIstruzioni.setFont(new Font("Dialog", 1, 14)); // NOI18N
        labelIstruzioni.setHorizontalAlignment(SwingConstants.CENTER);
        labelIstruzioni.setText("Inserire il file nella cartella contenente tutti i pdf e cliccare il pulsante \"Invia le buste paga nella cartella\"");
        panel.add(labelIstruzioni);

        labelIstruzioni1.setFont(new Font("Dialog", 1, 14)); // NOI18N
        labelIstruzioni1.setHorizontalAlignment(SwingConstants.CENTER);
        labelIstruzioni1.setText("e in automatico verranno inviate tramite mail, in caso di errori verranno mostrati nel riquadro in basso");
        panel.add(labelIstruzioni1);
        
        
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
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(separatore, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button)
                .addGap(24, 24, 24)
                .addComponent(pannelloScroll, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
                .addComponent(saveLog)
                .addContainerGap())
        );
        panel.setLayout(layout);
        
        
        
        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Buste Paga");
        frame.pack();
        frame.setVisible(true);
        
        
  
        
    }
    
    
    // invia BP premuto
    private void bottoneActionPerformed(ActionEvent e) {                                          
    	frame.setVisible(true);
    	logTextArea.append("\n\n ============ [INIZIO] ============\n\n");
	        
	    try {
	    	
	    	//get nomi da file
	        List<String> files = FileUtils.getFileName(new File("."));  // ricerca file
	        logTextArea.append("\n\n [INFO] -> Nessun altro file trovato\n\n");
	        
	        for (String file : files) {	
	        	String oggetto = FileUtils.getOggettoMail(file);
	        	
	        	SendMail.send("test", oggetto);
			}
	
	        
				
	    } catch (AddressException e1) {	
			e1.printStackTrace();
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
    	
	    
	    
	    logTextArea.append("\n\n ============ [FINE] ============\n");
    }
    
    // salva log premuto
    public void saveLogActionPerformed(ActionEvent e) {
    	String log = logTextArea.getText();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH.mm.ss");  
    	LocalDateTime oggi = LocalDateTime.now();  

    	FileWriter logFile;
		try {
			logFile = new FileWriter("./log-" + formatter.format(oggi) + ".txt");
			logFile.write(log);
	    	logFile.close();
	    	JOptionPane.showMessageDialog(null, "Log Salvato");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Non è stato possibile salvare il log...");
			logTextArea.append("\n\n[ERRORE] ==> " + e1.getMessage());
			e1.printStackTrace();
		}    	
    }

    // azione generale effettuata
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}


    
}
