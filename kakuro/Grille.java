package kakuro;


import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JTextField;

import java.awt.event.*;
import java.io.InputStream;

import javax.swing.text.*;
import java.awt.FlowLayout;

public class Grille {
	private JFrame frame=new JFrame();
	private JPanel insertion=new JPanel();
	private static Dimension dim=new Dimension(50,40);
	public String[][] tab=new String[8][8];
	public int[][] tabInt = new int[8][8]; 
	public Dessine[][] TaBoutton=new Dessine[8][8];
	public JTextField[][] tabText=new JTextField[8][8];
	public Random rdm= new Random();
	
	private JButton bouton  = new JButton("ENVOYER");
	
	

	public void initTabNoir() {
		for (int i=0; i<8; i++) {
			TaBoutton[i][0].setBackground(Color.BLACK);
			TaBoutton[i][0].setText(null);
			TaBoutton[i][0].setVisible(true);
			tabText[i][0].setVisible(false);

			TaBoutton[0][i].setBackground(Color.BLACK);
			TaBoutton[0][i].setText(null);
			TaBoutton[0][i].setVisible(true);
			tabText[0][i].setVisible(false);
		}

		TaBoutton[1][3].setBackground(Color.BLACK);
		TaBoutton[1][3].setText(null);
		TaBoutton[1][3].setVisible(true);
		tabText[1][3].setVisible(false);

		TaBoutton[3][7].setBackground(Color.BLACK);
		TaBoutton[3][7].setText(null);
		TaBoutton[3][7].setVisible(true);
		tabText[3][7].setVisible(false);

		TaBoutton[7][4].setBackground(Color.BLACK);
		TaBoutton[7][4].setText(null);
		TaBoutton[7][4].setVisible(true);
		tabText[7][4].setVisible(false);
	}

	public void initSomme() {
		TaBoutton[2][3].setBackground(Color.pink);
		TaBoutton[2][3].setXY("29","15");
		TaBoutton[2][3].setVisible(true);                        
		tabText[2][3].setVisible(false);

		TaBoutton[1][4].setBackground(Color.pink);
		TaBoutton[1][4].setXY("24","17");
		TaBoutton[1][4].setVisible(true);
		tabText[1][4].setVisible(false);

		TaBoutton[4][4].setBackground(Color.pink);
		TaBoutton[4][4].setXY("8","7");
		TaBoutton[4][4].setVisible(true);
		tabText[4][4].setVisible(false);

		TaBoutton[5][2].setBackground(Color.pink);
		TaBoutton[5][2].setXY("16","10");
		TaBoutton[5][2].setVisible(true);
		tabText[5][2].setVisible(false);

		TaBoutton[6][5].setBackground(Color.pink);
		TaBoutton[6][5].setXY("5","");
		TaBoutton[6][5].setVisible(true);
		tabText[6][5].setVisible(false);

		TaBoutton[5][1].setBackground(Color.pink);
		TaBoutton[5][1].setXY("","11");
		TaBoutton[5][1].setVisible(true);
		tabText[5][1].setVisible(false);

		TaBoutton[3][6].setBackground(Color.pink);
		TaBoutton[3][6].setXY("","12");
		TaBoutton[3][6].setVisible(true);
		tabText[3][6].setVisible(false);

		TaBoutton[4][7].setBackground(Color.pink);
		TaBoutton[4][7].setXY("","7");
		TaBoutton[4][7].setVisible(true);
		tabText[4][7].setVisible(false);

		TaBoutton[4][1].setBackground(Color.pink);
		TaBoutton[4][1].setXY("7","");
		TaBoutton[4][1].setVisible(true);
		tabText[4][1].setVisible(false);

		TaBoutton[7][5].setBackground(Color.pink);
		TaBoutton[7][5].setXY("3","");
		TaBoutton[7][5].setVisible(true);
		tabText[7][5].setVisible(false);

		TaBoutton[0][1].setBackground(Color.pink);
		TaBoutton[0][1].setXY("","23");
		TaBoutton[0][1].setVisible(true);
		tabText[0][1].setVisible(false);

		TaBoutton[0][2].setBackground(Color.pink);
		TaBoutton[0][2].setXY("","30");
		TaBoutton[0][2].setVisible(true);
		tabText[0][2].setVisible(false);

		TaBoutton[0][5].setBackground(Color.pink);
		TaBoutton[0][5].setXY("","27");
		TaBoutton[0][5].setVisible(true);
		tabText[0][5].setVisible(false);

		TaBoutton[0][6].setBackground(Color.pink);
		TaBoutton[0][6].setXY("","12");
		TaBoutton[0][6].setVisible(true);
		tabText[0][6].setVisible(false);

		TaBoutton[0][7].setBackground(Color.pink);
		TaBoutton[0][7].setXY("","16");
		TaBoutton[0][7].setVisible(true);
		tabText[0][7].setVisible(false);

		TaBoutton[1][0].setBackground(Color.pink);
		TaBoutton[1][0].setXY("16","");
		TaBoutton[1][0].setVisible(true);
		tabText[1][0].setVisible(false);

		TaBoutton[2][0].setBackground(Color.pink);
		TaBoutton[2][0].setXY("17","");
		TaBoutton[2][0].setVisible(true);
		tabText[2][0].setVisible(false);

		TaBoutton[3][0].setBackground(Color.pink);
		TaBoutton[3][0].setXY("35","");
		TaBoutton[3][0].setVisible(true);
		tabText[3][0].setVisible(false);

		TaBoutton[6][0].setBackground(Color.pink);
		TaBoutton[6][0].setXY("21","");
		TaBoutton[6][0].setVisible(true);
		tabText[6][0].setVisible(false);

		TaBoutton[7][0].setBackground(Color.pink);
		TaBoutton[7][0].setXY("6","");
		TaBoutton[7][0].setVisible(true);


		tabText[7][0].setVisible(false);
	}

	public void initCaseVide() {
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				tabText[i][j] = new JTextField("");
				
				tabText[i][j].setPreferredSize(dim);

				
			}
		}
	


	}
		
	public void initButton()
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				TaBoutton[i][j]=new Dessine("","");
				
				TaBoutton[i][j].setPreferredSize(dim);
				TaBoutton[i][j].setVisible(false);
				
			}
		}	
		
	}
	
	

	public void addBouton() {
		insertion.setPreferredSize(new Dimension(450,400));
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				 insertion.add(TaBoutton[i][j]);
				 insertion.add(tabText[i][j]);
				
			}
		}
		
	}

	

	public void initTabInt(int[] tab, int taille) {
		for (int i=0; i<taille; i++) {
			tab[i] = -1;
		}
	}

	

	

	
	
	public Grille()
	{
		
	
		initButton();
		initCaseVide();
		addBouton();
		initTabNoir();
		initSomme();
		
		
	

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setContentPane(insertion);
		frame.add(new Chrono());
	       
		   AePlayWave playMusique=new AePlayWave("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\instru_dass.wav");
		   
		   	
		   	
		  
		       frame.pack();
		       frame.setLocationRelativeTo(null);
		       frame.setVisible(true);
		     
		      {
		    	  playMusique.start();
		      }
			   		
		    
	

	}
	
	
	public static void main(String[] args)
	{
		
		Grille grille=new Grille();
	}
	
	
	

}