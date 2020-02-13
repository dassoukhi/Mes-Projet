package kakuro;




import java.lang.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Chrono extends JPanel{
private static int heure=0,minute=0,seconde=0;

private int delais=1000;
private final JLabel Label1 = new JLabel(((heure/10)==0?"0"+heure:+heure)+":"+((minute/10)==0?"0"+minute:+minute)+":"+((seconde/10)==0?"0"+seconde:+seconde)); /* déclarer final car une classe interne va acceder à ce composant */
private  Timer timer;





public class chronoListener implements ActionListener{

	


	@Override
	public void actionPerformed(ActionEvent e) {
		
		
			seconde++;
			if(seconde==60)
			{
			seconde=0;
			minute++;
			}
			if(minute==60)
			{
			minute=0;
			heure++;
			}
			
			
	Label1.setText(((heure/10)==0?"0"+heure:+heure)+":"+((minute/10)==0?"0"+minute:+minute)+":"+((seconde/10)==0?"0"+seconde:+seconde));/* rafraichir le label */
			
	
		
	}
	
	
	
}

		public void  stopChrono() {
			
			timer.stop();
		}
		
		public void reprendreChrono() {
			
			timer.restart();
		}
		

		public int getHeure()
		{
			return heure;
		}
		
		public int getMinute()
		{
			return minute;
		}
		
		public int getSeconde()
		{
			return seconde;
		}
		
		public void arreter()
		{
			stopChrono();
			heure=0;
			seconde=0;
			minute = 0;
			
			
		}

	public Chrono() {
		
		timer= new Timer(delais, new chronoListener());
		
		
		timer.start();
		this.add(Label1);
		this.setPreferredSize(new Dimension(10, 10));
		
	}


public static void main(String[] args)
{

	
	new Chrono();
	
}




}