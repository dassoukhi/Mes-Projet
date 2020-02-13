package kakuro;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Demo extends JPanel{
	
	private JLabel labelKakuro= new JLabel("   DEMO");
	//private JPanel panel = new JPanel();
	private JPanel insertion = new JPanel();
	private Dessine[][] tabButon ;
	private JFloatField[][] tabText;
	private static final int n1=4,n2=4;
	private Dimension dim = new Dimension(50,40);
	
	public Demo() {
		
		tabButon = new Dessine[n1][n2];
		tabText = new JFloatField[n1][n2];
		
		init();
		initTabBouton(n1,n2);
		initTabText(n1, n2);
		
		
		this.setPreferredSize(new Dimension(250, 250));
	}
	public void init()
	{
		for (int i = 0; i < tabButon.length; i++) {
			for (int j = 0; j < tabButon.length; j++) {
				
				tabText[i][j] = new JFloatField();
				tabButon[i][j]= new Dessine("", "");
				tabText[i][j].setPreferredSize(new Dimension(50,40));
				tabButon[i][j].setPreferredSize(dim);
				add(tabButon[i][j]);
				add(tabText[i][j]);
			}
		}
		
		
		
		
		
	}
	public void	initTabText(int n1, int n2)
	{

		
		tabButon[1][2].setVisible(false);
		tabButon[1][3].setVisible(false);
		
		tabButon[2][1].setVisible(false);
		tabButon[2][2].setVisible(false);
		tabButon[2][3].setVisible(false);
		
		tabButon[3][1].setVisible(false);
		tabButon[3][2].setVisible(false);
		tabButon[3][3].setVisible(false);
		
		
		
			
		
	}
	
	public void	initTabBouton(int n1, int n2)
	{
		tabButon[0][0].setXY("", "");
		tabButon[0][1].setXY("", "");
		tabButon[1][0].setXY("", "");
		
		tabButon[0][0].setBackground(Color.BLACK);
		tabButon[0][1].setBackground(Color.BLACK);
		tabButon[1][0].setBackground(Color.BLACK);
//		add(tabButon[0][0]);
//		add(tabButon[0][1]);
//		add(tabButon[1][0]);
		
		tabText[0][0].setVisible(false);
		tabText[0][1].setVisible(false);
		tabText[1][0].setVisible(false);
		
		
		
		tabText[0][2].setVisible(false);
		tabText[0][3].setVisible(false);
		
		
		
		tabButon[0][2].setXY("", "7");
		tabButon[0][3].setXY("", "6");
		
		tabButon[0][2].setBackground(Color.pink);
		tabButon[0][3].setBackground(Color.pink);
		
//		add(tabButon[0][2]);
//		add(tabButon[0][3]);
		
		tabButon[1][1].setXY("4", "4");
		tabButon[1][1].setBackground(Color.pink);
		
//		add(tabButon[1][1]);
		
		tabText[1][1].setVisible(false);
		
		tabButon[2][0].setXY("7", "");
		tabButon[3][0].setXY("6", "");
		
		tabButon[2][0].setBackground(Color.pink);
		tabButon[3][0].setBackground(Color.pink);
//		
//		this.add(tabButon[2][0]);
//		this.add(tabButon[3][0]);
		
		tabText[2][0].setVisible(false);
		tabText[3][0].setVisible(false);
		
	}
}