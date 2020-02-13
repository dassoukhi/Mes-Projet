package kakuro;

import javax.swing.*;

import kakuro.FenetreMenu.MenuListener;
import kakuro.FenetreMenu.NiveauListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.security.Policy;
import java.awt.event.*;
import java.util.*;

public class Interaction {
	
	private JFrame frame=new JFrame();
	private JFrame fr= new JFrame();
	private JLabel labelKakuro= new JLabel("   KAKURO");
	private JLabel label= new JLabel("Entrez la taille de votre Grille souhaiteé");
	private JLabel labelSkurt= new JLabel("SKUURRTTT");
	private JLabel labelX= new JLabel("X");
	private JLabel labelY= new JLabel("Y");
	private JPanel panel = new JPanel();
	private JPanel panelFinal= new JPanel();
	private JPanel insertion = new JPanel();
	private JPanel panelcompar= new JPanel();
	private JPanel panelSom =new JPanel(); 
	private JFloatField taille1= new JFloatField();
	private JFloatField taille2= new JFloatField();
	private JFloatField som1= new JFloatField();
	private JFloatField som2= new JFloatField();
	private JButton button = new JButton("ENVOYER");
	private JButton buttonValide= new JButton("VALIDER");
	private JButton buttonSolver = new JButton("SOLVEUR");
	private Dessine[][] tabButon ;
	private JFloatField[][] tabText;
	private JButton buttonCaseNoir = new JButton("Case Noir");
	private JButton buttonCaseSomme = new JButton("Case Somme");
	private JPanel panelValide=new JPanel();
	private int n1,n2;
	public int f; 
	private JPanel panelButtonArr = new JPanel();
	private JButton buttonArr = new JButton("Retour");
	
	
	
	
	private Dimension dim = new Dimension(35,20);
	private int cpt=0;
	private Dimension dimoi = new Dimension(50,40);
	
	private Dimension dimpanel = new Dimension(470,470);
	
	
	private JMenuBar menuBar = new JMenuBar();
    private JMenu menuScore = new JMenu("Score");
    private JMenu menuAide = new JMenu("Aide");
    private JMenu menuMusique = new JMenu("Musique");
    private JMenu menuDemo = new JMenu("Démo");
    private JRadioButtonMenuItem musiqueActive = new JRadioButtonMenuItem("Activée");
    private JRadioButtonMenuItem musiqueDesactive = new JRadioButtonMenuItem("Désactivée");
    


	 private JMenuItem joueur1 =new JMenuItem("1 -");
    private JMenuItem joueur2 =new JMenuItem("2 -");
    private JMenuItem joueur3 =new JMenuItem("3 -");
    private JMenuItem joueur4 =new JMenuItem("4 -");
    private JMenuItem joueur5 =new JMenuItem("5 -");
    
    @SuppressWarnings("unused")
	private Thread playWave=new AePlayWave("./src/kakuro/instru.wav");
	
	
	public class envoyerListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			setN1N2(Integer.valueOf(taille1.getText()), Integer.valueOf(taille2.getText()));
			
			if( ((n1 >= 7) && (n2 >= 7) ) && ((n1 <= 16) && (n2 <= 16)) ) {
				
				initButon();
			}
			else {
				
				JOptionPane.showMessageDialog(frame,"Attention, vous devez choisir une taille entre 7 et 16"); 
			}
		
			
		}
		
	}



public void setN1N2(int x, int y)
{
	
	this.n1=x;
	this.n2=y;
}
	
	public void initButon()
	{
	
	buttonCaseNoir.addActionListener(new casenoirListener());
	buttonCaseSomme.addActionListener(new sommeListener());
	buttonValide.addActionListener(new valideListener());
	buttonSolver.addActionListener(new solveurListener());
	
    	
    	 tabButon = new Dessine[n1][n2];
    	
    	 
    	initTabText(n1, n2);
    	
    
    	
    for (int i = 0; i < n1; i++) {
    	
    	for (int j = 0; j < n2; j++) {
    		
 	
    		
    		tabButon[i][j]=new Dessine("","");
    		tabButon[i][j].setPreferredSize(dimoi);
    		tabButon[i][j].addActionListener(new BouttonListener());
    		
    		
    		
    		insertion.add(tabButon[i][j]);	
    		insertion.add(tabText[i][j]);
		}	
	}

		
    panelFinal.setLayout(new BorderLayout());
    
    
  int mid[]= new TailleFrame().donneMoiLaBonneTaille(n1, n2);
    
	panelFinal.setPreferredSize(new Dimension(mid[0],mid[1]));
	

	
	
	

	
	insertion.setPreferredSize(new Dimension(40*n2+170,50*n1+10 ));
	panelFinal.add(insertion, BorderLayout.NORTH);
	buttonCaseNoir.setSize(60,60);
	buttonCaseNoir.setBackground(Color.LIGHT_GRAY);
	buttonValide.setSize(60,40);
	buttonValide.setBackground(Color.LIGHT_GRAY);
	buttonSolver.setSize(60,40);
	buttonSolver.setVisible(false);
	buttonSolver.setBackground(Color.LIGHT_GRAY);
	buttonCaseSomme.setSize(60,40);
	buttonCaseSomme.setBackground(Color.LIGHT_GRAY);
	
	
	som1.setPreferredSize(dim);
	som2.setPreferredSize(dim);
	panelSom.add(buttonCaseNoir);
	panelSom.add(buttonCaseSomme);
	panelSom.add(labelX);
	panelSom.add(som1);
	panelSom.add(labelY);
	panelSom.add(som2);
	panelValide.setSize(600,40);
	panelValide.add(buttonValide);
	panelValide.add(buttonSolver);
	labelSkurt.setBackground(Color.MAGENTA);
	labelSkurt.setVisible(false);
	panelValide.add(labelSkurt);
	
	

	
	
	panelcompar.add(panelSom,BorderLayout.SOUTH);
	
	
	panelFinal.add(panelValide, BorderLayout.CENTER);
	
	panelFinal.add(panelcompar,BorderLayout.SOUTH);
	
	fr.setContentPane(panelFinal);
	fr.pack();
	
	//fr.setJMenuBar(menuBar);
	
	fr.setVisible(true);
	frame.setVisible(false);
    
	
    
	}
	
	
	
public void	initTabText(int n1, int n2)
{
	tabText = new JFloatField[n1][n2];
	
	for (int i = 0;  i < n1; i++) {
		
		for (int j = 0; j < n2; j++) {
			
		
	
			
			tabText[i][j]=new JFloatField();
			tabText[i][j].setPreferredSize(new Dimension(50,40));
			tabText[i][j].setVisible(false);
			
			
		}
		
	}
	
	
}
		
	
	
	public class BouttonListener implements ActionListener{
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object ob= e.getSource();
			for (int i = 0; i < n1; i++) {
				
				for (int j = 0; j < n2; j++) {
					
					if(ob==tabButon[i][j] && cpt==0)
					{
						
						tabButon[i][j].setBackground(Color.yellow);
						
						cpt++;
						
						System.out.println(i + "  " + j);
		
						
					}
					else if(tabButon[i][j].getBackground().equals((Color.yellow)) && cpt!=0) {
						
						tabButon[i][j].setBackground(null);
						tabButon[i][j].setXY("", "");
						cpt--;
					}
				}
				
			}     
			
			
		}
	}
	
	public class casenoirListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			Object ob1=buttonCaseNoir;
			
			if(ob1==e.getSource())
			{
				 int k=0;
				for (int i = 0; i < n1; i++) {
					
					for (int j = 0; j < n2; j++) {
						
						int p=0,l=0;
						
						if( i != 0 && tabButon[i][j].getBackground().equals(Color.yellow) && tabButon[i-1][j].getBackground().equals(Color.pink) && !tabButon[i-1][j].getSomY().equals(""))
						{
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas mettre une case noir ici"); 
							p++;
						}
						if(j != 0 && tabButon[i][j].getBackground().equals(Color.yellow) && tabButon[i][j-1].getBackground().equals(Color.pink) && !tabButon[i][j-1].getSomX().equals(""))
						{
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas mettre une case noir ici"); 
							l++;
						}
						
						
						
						if(tabButon[i][j].getBackground().equals(Color.yellow) && p==0 && l==0)
						{
							tabButon[i][j].setBackground(Color.black);
							
							k++;
						}
						
					}
					
				}
				
				if(k==1)
				{
					cpt--;
				}
				
				
				
			}
			
		}
	
			
		
	}
	
	
	public class sommeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			
			Object obj=e.getSource();
			
			if(obj == buttonCaseSomme)
			{
				int k=0;
				String x=som1.getText();
				String y=som2.getText();
				
				boolean oui=true;
				
				
				//System.out.println(x+"  "+y);
				
				if(y.equals("") && x.equals(""))
				{
					//cpt--;
					JOptionPane.showMessageDialog(frame,"Attention, vous ne pouvez pas choisir une case somme vide");  
					 
				}
				
				
				if(!y.equals("") && !x.equals("")){
					int x1 = Integer.parseInt(x);
					int y1 = Integer.parseInt(y);
					
					if(y1 < 3 || x1 < 3)
					{
						JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être inferieur à 3");  
						
						oui = false;
						
					}
					else if(y1 > 45 || x1 > 45)
					{
						JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être superieur à 45");  
						
						oui = false;
						
						
					}
					
				}
					
				else if(!x.equals("")){
				
						int x1 = Integer.parseInt(x);
						
						if(x1 < 3)
						{
							
							JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être inferieur à 3");  
							
							oui=false;
							
						}
						else if( x1 > 45)
						{
							JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être superieur à 45");  
							
							oui = false;
							
							
						}
						
				}
				else if(!y.equals(""))
				{
					int y1 = Integer.parseInt(y);
					
					if(y1 < 3)
					{
						JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être inferieur à 3");  
						
						oui = false;
						
					}
					else if(y1 > 45 )
					{
						JOptionPane.showMessageDialog(frame,"Attention, une somme ne peut pas être superieur à 45");  
						
						oui = false;
						
						
					}
					
				}
				 
					
					
				
				
				
				
				if((!y.equals("") || !x.equals("")) && oui)
				{
				
				for (int i = 0; i < n1; i++) {
					
					for (int j = 0; j < n2; j++) {
						
						int p=0,l=0;
						
						if( p==0 && (j+1) < n2 && tabButon[i][j].getBackground().equals(Color.yellow) && !som1.getText().equals("") && (tabButon[i][j+1].getBackground().equals(Color.black) || tabButon[i][j+1].getBackground().equals(Color.pink) ) )
						{
							
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme droite ici ");  
							p++;
							
						}
						
						if(p==0 && (j - 1 > 0) && tabButon[i][j].getBackground().equals(Color.yellow) && (!som2.getText().equals("") || !som1.getText().equals("") ) &&  !tabButon[i][j-1].getSomX().equals("")  ) 
						{
							
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme ici ");  
							p++;
							
						}
						if( (j+1)>=n2  && tabButon[i][j].getBackground().equals(Color.yellow) && !som1.getText().equals("") )
						{
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme droite ici ");  
							p++;
							
						}
						

						if( l==0 && (i+1) < n1 && tabButon[i][j].getBackground().equals(Color.yellow) && !som2.getText().equals("") && (tabButon[i+1][j].getBackground().equals(Color.black) || tabButon[i+1][j].getBackground().equals(Color.pink)) )
						{
							
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme bas ici");  
							l++;
							
						}
						
						if(l==0 && (i-1) > 0 && tabButon[i][j].getBackground().equals(Color.yellow) && (!som1.getText().equals("") || !som2.getText().equals("") ) && !tabButon[i-1][j].getSomY().equals("") )
						{
							
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme ici");  
							l++;
							
						}
						
						if( tabButon[i][j].getBackground().equals(Color.yellow) && !som2.getText().equals("") && (i+1) >= n1 )
						{
							JOptionPane.showMessageDialog(frame,"Vous ne pouvez pas placer une case somme bas ici I ");  
							p++;
							
							//System.out.println(i+"  " + j);
						}
						
						
						if(tabButon[i][j].getBackground().equals(Color.yellow) && p==0 && l==0)
						{
							tabButon[i][j].setBackground(Color.pink);
							tabButon[i][j].setXY(x, y);
							tabButon[i][j].setText(null);
							
							som1.setText(null);
							som2.setText(null);
							
							k++;
							
						}
						
					}
					
					
				}
				if(k==1)
				{
					cpt--;
				}
				
				}
				
				
			}
			
		}
		
	}
	
	
	public class valideListener implements ActionListener
	{

		
		public void actionPerformed(ActionEvent arg0) {
	
			
		
		if(valideGrille())
		{
			
			for (int i = 0; i < n1; i++) {
				
				for (int j = 0; j < n2; j++) {
					
					if(!tabButon[i][j].getBackground().equals(Color.black) && !tabButon[i][j].getBackground().equals(Color.pink) )
					{
						
						
						tabText[i][j].setVisible(true);
						tabButon[i][j].setVisible(false);
					}
					
					
					
				}
				
			}
			
			buttonValide.setVisible(false);
			buttonSolver.setVisible(true);
			button.setVisible(false);
			buttonCaseNoir.setVisible(false);
			buttonCaseSomme.setVisible(false);
			som1.setVisible(false);
			som2.setVisible(false);
			labelX.setVisible(false);
			labelY.setVisible(false);
		}
		else {
			
			
			JOptionPane.showMessageDialog(frame,"Cette grille n'est pas valide"); 
			
		}
		
		
		
	}
		
	}
	
	
	public class solveurListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			if(obj == buttonSolver)
			{
				ArrayList<Somme> listeSomme = new ArrayList<Somme>();
				Case[][] algo = convertTabForAlgo(listeSomme);		
				
				new Solveur().solve(0, n1, n2, algo, tabText, listeSomme);
				
				buttonSolver.setVisible(false);
				labelSkurt.setVisible(true);
				 labelSkurt.setForeground(Color.MAGENTA);
			        labelSkurt.setFont(new Font("TimesRoman", Font.ITALIC, 40));
				 AePlayWave playMusique=new AePlayWave("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\skurt.wav");
				 
				 playMusique.start();
				
			}
			
		}
		
		
		
	}
	
	   public  int sommeMin(int nbrCases) {
	        return (nbrCases * (nbrCases + 1)) / 2;
	    }

	    public  int sommeMax(int nbrCases) {
	        return (10 * nbrCases) - sommeMin(nbrCases);
	    }
	
	    
	    public int nbrCaseX(int i, int j)
	    {
	    	int k=j+1;
	    	int cpt=0;
	    	while( k < n2 && (!tabButon[i][k].getBackground().equals(Color.black) && !tabButon[i][k].getBackground().equals(Color.pink) ))
	    	{
	    		cpt++;
	    		k++;
	    	}
		
	    	return cpt;
	    	
	    }
	    
	    public int nbrCaseY(int i, int j)
	    {
	    	int k=i+1;
	    	int cpt=0;
	    	
	    	while( k < n1 && (!tabButon[k][j].getBackground().equals(Color.black) && !tabButon[k][j].getBackground().equals(Color.pink) ))
	    	{
	    		cpt++;
	    		k++;
	    	}
		
	    	return cpt;
	    	
	    }
	    
	    
	
	public boolean valideGrille()
	{
		
		for (int i = 0; i < tabButon.length; i++) {
			
			if(!tabButon[i][0].getBackground().equals(Color.black) && !tabButon[i][0].getBackground().equals(Color.pink) )
			{
				
				return false;
			}
			
		}
		
		for (int j = 0; j < tabButon.length; j++) {
			
			
			if(!tabButon[0][j].getBackground().equals(Color.black) && !tabButon[0][j].getBackground().equals(Color.pink) )
			{
				
				return false;
			}
			
			
		}
		
		for (int i = 0; i < tabButon.length; i++) {
			
			int tmp=0;
			
			for (int j = 0; j < tabButon.length; j++) {
				
				if(tabButon[i][j].getBackground().equals(Color.pink))
				{
					tmp++;
				}
				
			}
			
			if(tmp<=0)
			{
				return false;
			}
			
		}
		
		
		for (int i = 0; i < tabButon.length; i++) {
			
			int tmp2=0;
			
			for (int j = 0; j < tabButon.length; j++) {
				
				if(tabButon[j][i].getBackground().equals(Color.pink))
				{
					tmp2++;
				}
			}
			if(tmp2 <= 0)
			{
			
				return false;
			}
		}
		
		
		
		 f=0; 
		
		for (int i = 0; i < tabButon.length; i++) {
			
			for (int j = 0; j < tabButon.length; j++) {
				
				if(tabButon[i][j].getBackground().equals(Color.pink))
				{
					f++;
					
					if (!tabButon[i][j].getSomX().equals("")) {
						
						int n=Integer.valueOf(tabButon[i][j].getSomX());
						
						if( (n < sommeMin(nbrCaseX(i, j))) || (n > sommeMax(nbrCaseX(i, j))) )
						{
							System.out.println( tabButon[i][j].getSomX());
							return false;
						}
						
					}
					
					if (!tabButon[i][j].getSomY().equals(""))
					{
						int h=Integer.valueOf(tabButon[i][j].getSomY());
						
						if( ( h < sommeMin(nbrCaseY(i, j)) ) || ( h > sommeMax(nbrCaseY(i, j))))
						{
							System.out.println( tabButon[i][j].getSomY());
							System.out.println( sommeMin(nbrCaseY(i, j)));
							System.out.println( nbrCaseY(i, j));
							
							return false;
							
						}
						
						
					}
					
				}
			
			}
		}
		
		if(f==0)
		{
			return false;
		}
		
		
		return true;
		
	}

	
	
	
public class EnArriereListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if(action.equals("Retour")) {
                frame.dispose(); 
                FenetreMenu menu = new FenetreMenu();
                   }
    }
}



Case[][] convertTabForAlgo(ArrayList<Somme> listeSomme)
{
	Case[][] grille= new Case[n1][n2];
	
	Somme somNull = new Somme();
	
	
	for (int i = 0; i < tabButon.length; i++) {
		
		for (int j = 0; j < tabButon.length; j++) {
			
			if(tabButon[i][j].isVisible() && tabButon[i][j].getBackground().equals(Color.black))
			{
				
				grille[i][j]= new CaseNoire(i,j,somNull,somNull);
			}
			else if(tabButon[i][j].isVisible())
			{
				Somme sommeY = null;
				Somme sommeX = null;
				
				if(!tabButon[i][j].getSomY().equals(""))
				{
					
					ArrayList<Pair<Integer, Integer>> listePairY = new Solveur().getListePair(i,j,nbrCaseY(i, j),true);
					int s=Integer.valueOf(tabButon[i][j].getSomY());
					sommeY= new Somme(s,true, listePairY, new Combinaison().combinaisonsPossibles(s, listePairY.size()));
					
					listeSomme.add(sommeY);
					
				}
				
				if(!tabButon[i][j].getSomX().equals(""))
				{
					ArrayList<Pair<Integer, Integer>> listePairX = new Solveur().getListePair(i, j, nbrCaseX(i, j), false);
					
					int s2 = Integer.valueOf(tabButon[i][j].getSomX());
					
					sommeX = new Somme(s2, false,listePairX, new Combinaison().combinaisonsPossibles(s2, listePairX.size()));
					listeSomme.add(sommeX);
				}
				
				if(!tabButon[i][j].getSomX().equals("") && !tabButon[i][j].getSomY().equals(""))
				{
					grille[i][j] = new CaseNoire(i, j, sommeX, sommeY);
				}
				else if(!tabButon[i][j].getSomX().equals(""))
				{
					grille[i][j] = new CaseNoire(i,j,sommeX,somNull);
				}
				else if(!tabButon[i][j].getSomY().equals(""))
				{
					
					grille[i][j] = new CaseNoire(i,j,somNull,sommeY);
				}
				
				
			}
		
		}
		
	}
	
	for (int k = 0; k < tabText.length; k++) {
		
		for (int k2 = 0; k2 < tabText.length; k2++) {
			
			if(tabText[k][k2].isVisible())
			{
				grille[k][k2] = new CaseBlanche(k, k2, 0, new Solveur().listePossibilite(k, k2, listeSomme)); 
			}
		
	}
		
	}
	

	return grille;
}
    
    public Interaction()
    {

        taille1.setPreferredSize(dim);
        taille1.getText();
        taille2.setPreferredSize(dim);
        
        label.setLocation(10, 100);
        
        fr.setSize(582,581);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//    	musiqueActive.addActionListener(new MenuListener());
//    	musiqueDesactive.addActionListener(new MenuListener());
    	musiqueActive.setSelected(true);
    	
    	menuMusique.add(musiqueActive);
    	menuMusique.add(musiqueDesactive);

    	menuAide.add(new PanelDescription());
    	menuDemo.add(new Demo());

    	
    	  try {
     			new MeilleurScore().MajLesItem(joueur1, joueur2, joueur3, joueur4, joueur5);
     		} catch (FileNotFoundException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     		
     		menuScore.add(joueur1);
     		menuScore.add(joueur2);
     		menuScore.add(joueur3);
     		menuScore.add(joueur4);
     		menuScore.add(joueur5);
     		
    	
    	
    	menuBar.add(menuScore);
    	menuBar.add(menuDemo);
    	menuBar.add(menuAide);
    	menuBar.add(menuMusique);
    	
    	
    	
    	
    	//playWave.start();
        
        
    
        button.setSize(50,40);
        button.addActionListener(new envoyerListener());
    
        buttonArr.setPreferredSize(new Dimension(100,50));
        buttonArr.setBackground(Color.WHITE);
        buttonArr.addActionListener(new EnArriereListener());

        panel.add(label);
        panel.add(taille1);
        panel.add(taille2);
        panel.add(button);
        panel.setSize(582,50);
        //panel.setSize(855,855); ALEX
        //panel.setLayout(new BorderLayout()); ALEX
        frame.setLayout(new BorderLayout());
    
        labelKakuro.setForeground(Color.LIGHT_GRAY);
        labelKakuro.setFont(new Font("TimesRoman", Font.ITALIC, 100));
        labelKakuro.setSize(22, 135);
    
        panelButtonArr.setSize(100,60);
        panelButtonArr.setLocation(230,400);
        panelButtonArr.setBackground(new Color(91,114,174));
        panelButtonArr.add(buttonArr);
        
        frame.setSize(581,581);
        //frame.add(panel,BorderLayout.NORTH); ALEX
        frame.add(panelButtonArr);
        //frame.setContentPane(panel);
        frame.add(labelKakuro,BorderLayout.NORTH);    
        //frame.add(panelButtonArr);
        frame.add(panel);
        	
        frame.setJMenuBar(menuBar);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    
        
        
        
    }
    

	
	public static void main(String[] args) {
		
		Interaction inter=new Interaction();
		
	}

}
