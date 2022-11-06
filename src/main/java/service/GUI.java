package service;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import control.FileUtils;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
    private static JFrame frame = new JFrame();
    private static JScrollPane pannelloScroll = new JScrollPane();
    public static JTextArea logTextArea = new JTextArea();
    private static JLabel labelTitolo = new JLabel();
    private static JSeparator separatore = new JSeparator();
    private static JProgressBar barraProgresso = new JProgressBar();
    private static JLabel labelIstruzioni = new JLabel();
    private static JLabel labelIstruzioni1 = new JLabel();
    private static JLabel labelIstruzioni2 = new JLabel();
    private static JLabel labelSuccess = new JLabel();
    private static JMenuItem accountSettings = new JMenuItem();
    private static JMenuItem scaricaGuida = new JMenuItem();
    private static JMenuBar jMenuBar = new JMenuBar();
    private static JMenu settingMenu = new JMenu();
    private static JMenu helpMenu = new JMenu();
    private static JButton button = new JButton("Invia Buste Paga presenti nella cartella");
    
    public static boolean errore = false;

    public GUI() {

    	//carica credenziali
    	SendMail.setCredenziali();
    	
    	// pannello
    	JPanel panel = new JPanel();
    	panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(0, 1));

    	
    	// bottone invia BP
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
        // icona titolo
        Image image = null;
        URL url;
		try {
			url = new URL("https://github.com/TreeEmmeDev/Gestione-Candidature/blob/main/src/main/webapp/img/logo.png?raw=true");
			image = ImageIO.read(url);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(image).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        labelTitolo.setIcon(imageIcon);
         
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
        
        panel.add(barraProgresso);
        
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
                    .addComponent(barraProgresso, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(separatore, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button)
                .addGap(24, 24, 24)
                .addComponent(pannelloScroll, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addComponent(barraProgresso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(saveLog)
                .addContainerGap())
        );
        panel.setLayout(layout);
        
        barraProgresso.setForeground(new Color(22,158,255));
        
        // set icona
        ImageIcon img = new ImageIcon();
        try {
        	img = new ImageIcon(getClass().getResource("/resources/logo.png")); 
            
        }catch(NullPointerException e) {
        	img = new ImageIcon(".\\src\\main\\resources\\logo.png"); 
        }
        frame.setIconImage(img.getImage());
        
        
        
        // menu bar
        settingMenu.setText("Impostazioni");
        settingMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingMenu.setFont(new Font("Arial", 1, 15));
        settingMenu.setBackground(new Color(22,158,255));
        settingMenu.setForeground(new Color(255,255,255));

        accountSettings.setText("Account");
        accountSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountSettings.setFont(new Font("Arial", 1, 15));
        accountSettings.setBackground(new Color(22,158,255));
        accountSettings.setForeground(new Color(255,255,255));
        accountSettings.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                accountSettingLogActionPerformed(evt);
            }
        });
        settingMenu.add(accountSettings);
        
        helpMenu.setText("Guida");
        helpMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpMenu.setFont(new Font("Arial", 1, 15));
        helpMenu.setBackground(new Color(22,158,255));
        helpMenu.setForeground(new Color(255,255,255));
        
        
        scaricaGuida.setText("Scarica Guida");
        scaricaGuida.setCursor(new Cursor(Cursor.HAND_CURSOR));
        scaricaGuida.setFont(new Font("Arial", 1, 15));
        scaricaGuida.setBackground(new Color(22,158,255));
        scaricaGuida.setForeground(new Color(255,255,255));
        scaricaGuida.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                scaricaGuidaActionPerformed(evt);
            }
        });
        helpMenu.add(scaricaGuida);
        
       
        jMenuBar.add(settingMenu);
        jMenuBar.add(helpMenu);

        jMenuBar.setBackground(new Color(22,158,255));
        frame.setJMenuBar(jMenuBar);
        
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
	    
    	button.setBorderPainted(false);
    	button.setFocusPainted(false);
    	
    	barraProgresso.setValue(0);
	  
	    // get nomi da file
	    List<String> files = FileUtils.getFileName(new File("."));  // ricerca file
	    barraProgresso.setMaximum(files.size());
	    int contaFileFatti = 0;
	    
	    logTextArea.append("\n [INFO] -> Nessun altro file trovato\n\n");
	        
	    for (String file : files) {	
	    	
	        String oggetto = FileUtils.getOggettoMail(file);
	        String nominativo = FileUtils.getNominativo(file);
	        String email = FileUtils.getEmail(nominativo);
	        
	        logTextArea.append("\n [INFO] -> Invio email a " + email + " in corso...");

	        SendMail.send(email, oggetto, file);
	        
	        contaFileFatti++;
	        barraProgresso.setValue(contaFileFatti);
	        button.setText("Invia Buste Paga presenti nella cartella (" + contaFileFatti + ")");
	        
	        if(errore == false) {
	        	try {
					Files.move(Paths.get(file), Paths.get("Inviate/" + file), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					e1.printStackTrace();
					logTextArea.append("\n [INFO] -> Non e stato possibile spostare il file inviato(" + file + ")\n [ERRORE] -> " + e1.getMessage());
				}
	        }     
	        
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
			JOptionPane.showMessageDialog(null, "Sono presenti degli errori, clicca su \"Salva Log\" e contatta Domenico Lubrano\n\n"
					+ "Email di riferimento: d.lubranolobianco@3em.it",
				      "Errore!", JOptionPane.ERROR_MESSAGE);

	    }
	    errore = false;
	    
	    button.setBorderPainted(true);
    	button.setFocusPainted(true);
    	button.setText("Invia Buste Paga presenti nella cartella");
    }
    
    
    
    // salva log premuto
    private void saveLogActionPerformed(ActionEvent e) {
    	String log = logTextArea.getText();
    	

    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH.mm.ss");  
    	LocalDateTime oggi = LocalDateTime.now();  

    	FileWriter logFile;
    	
    	if (log.length() > 0) {
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
    	}else {
			JOptionPane.showMessageDialog(null, "Il log e' vuoto, non e' stato salvato");

    	}
		
	
    }

    // impostazioni account premuto
    private void accountSettingLogActionPerformed(ActionEvent evt) {
		new ImpostazioniGUI();
		
	}

    
    
    // scarica guida premuto
    protected void scaricaGuidaActionPerformed(ActionEvent evt) {
		URI uri;
		try {
			uri = new URI(FileUtils.urlGuida);
			Desktop dt = Desktop.getDesktop();
        	dt.browse(uri);
			JOptionPane.showMessageDialog(null, "La guida è stata scaricata, controlla i download");

		} catch (URISyntaxException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato possibile aprire la guida...");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Non e' stato possibile aprire la guida...");
			e.printStackTrace();
		}		
	}


    
    // azione generale effettuata
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}


    
}
