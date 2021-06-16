import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ServerSide extends JFrame implements ActionListener{
	
	JPanel panel;  
	JTextField textField;
	static JTextArea textArea;
	JButton button;
	
	static DataOutputStream dataOutputStream;
	static DataInputStream dataInputStream;
	static ServerSocket serverSocket;
	static Socket socket;

	 ServerSide(){
		
		// Top Panel
		panel= new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 136, 204));
		panel.setBounds(0,0,450,70);
		add(panel);
		
		// ArrowImage Label
		ImageIcon arrowImage = new ImageIcon("arrow.png"); 
		Image sizearrowImage= arrowImage.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
		ImageIcon resizedArrowImage = new ImageIcon(sizearrowImage);
		JLabel arrowlabel = new JLabel();
		arrowlabel.setBounds(5, 20, 30, 30);
		panel.add(arrowlabel);
		arrowlabel.setIcon(resizedArrowImage);
		
		arrowlabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event) {
				System.exit(0);
			}
		});
		
		// PersonImage Label
		ImageIcon personImage = new ImageIcon("ms.png"); 
		Image sizePersonImage= personImage.getImage().getScaledInstance(60,60, Image.SCALE_DEFAULT);
		ImageIcon resizedPersonImage = new ImageIcon(sizePersonImage);
		JLabel labelPerson = new JLabel();
		labelPerson.setBounds(40, 5, 60, 60);
		panel.add(labelPerson);
		labelPerson.setIcon(resizedPersonImage);
		
		// Name Label
		JLabel name = new JLabel();
	    name.setFont(new Font("Monospace",Font.BOLD,20));
	    name.setForeground(Color.WHITE);
	    name.setBounds(110, 15, 250, 18);
	    panel.add(name);
	    name.setText("Dhoni");
	    
	    // ActiveNow Label
	    JLabel activeNow = new JLabel();
	    activeNow.setFont(new Font("Monospace",Font.PLAIN,14));
	    activeNow.setForeground(Color.WHITE);
	    activeNow.setBounds(110, 35, 100, 20);
 	    panel.add(activeNow);	
 	    activeNow.setText("Active Now");
 	    
 		// Icon Label
		ImageIcon icon = new ImageIcon("Icon.png"); 
		Image sizeIconImage= icon.getImage().getScaledInstance(15,25, Image.SCALE_DEFAULT);
		ImageIcon resizedIconImage = new ImageIcon(sizeIconImage);
		JLabel labelIcon = new JLabel();
		labelIcon.setBounds(410, 20, 15, 25);
		panel.add(labelIcon);
		labelIcon.setIcon(resizedIconImage);
		
		//Text Area
		textArea = new JTextArea();
		textArea.setBounds(1,70,450,537);
		textArea.setBackground(new Color(198, 201, 207));
		textArea.setFont(new Font("Monospace",Font.PLAIN,17));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea);	
		
		// Text Field
		textField= new JTextField();
		textField.setBounds(5,605,390,40);
		textField.setFont(new Font("Monospace",Font.PLAIN,17));
		add(textField);
		
		// Button	
		 button = new JButton("Send");
		 button.setBounds(400, 610, 45, 35);
		 button.setBackground(new Color(7, 94, 84));
		 button.setFont(new Font("Monospace", Font.PLAIN, 17 ));
		 button.addActionListener(this);
		 add(button);
	
		this.setResizable(false);
		this.setLocation(400,250);
		this.setSize(450,650);
		this.setUndecorated(true);
		this.setLayout(null);
		this.setVisible(true);
		getContentPane().setBackground(Color.white);	 
	 }
	public void actionPerformed(ActionEvent event) {
		
		try {
			String text = textField.getText();
			textArea.setText(textArea.getText()+"\n"+ text);
			//dataOutputStream.writeUTF(text);
			textField.setText("");
			
		} 
		catch (Exception e) {
		}
		
	}
			
		public static void main(String[] args) throws IOException {
			
			new ServerSide();
		
	     String messageString ="";
	     
		try {
			serverSocket = new ServerSocket(7777);
			socket = serverSocket.accept();
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			messageString = dataInputStream.readUTF();
			textArea.setText(textArea.getText()+"\n"+ messageString);
			
			serverSocket.close();
			socket.close();
		}
		catch (Exception e) {
		}
		
		}		
		
	}


