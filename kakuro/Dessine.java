package kakuro;


import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Dessine extends JButton{


		private String x,y;
    


       public Dessine(String a, String b)
       {
            super();
            this.setSize(50,40);
			x=a;
			y=b;
            
            new mlop();

       }

       public String getSomX()
       {
        return x;

       }

        public String getSomY()
       {
        return y;

       }

       public void setXY(String a,String b)
       {

        x=a;
        y=b;
        new mlop();
       }
       
public void paintComponent(Graphics g)
        {

        super.paintComponent(g);
           
     
            
                setSize(50,40);

                //setEnabled(false);
          
            g.setColor(Color.black);
            

                if(!x.equals("") && !y.equals(""))
                {
                    g.drawLine(getHeight()+12,getHeight(),0,0);
                    g.drawString(x,25,15);
                    g.drawString(y,10,35);
                }
                else if(!y.equals(""))
                {
                    int m=12;
                    while(m<=1500)
                    {
                        g.drawLine(getHeight()+m,getHeight(),0,0);
                        m++;
                    }
                
                    g.drawString(y,10,35);
                }  
                else if(!x.equals(""))
                {
                    int n=12;
                    while(n>=-40)
                    {
                        g.drawLine(getHeight()+n,getHeight(),0,0);
                        n--;
                    }
                
               
                g.drawString(x,25,15);

                }
            
            else{

               // g.fillRect(0,0,50,40) ;    

               }
}

public class mlop{

	public void paint(Graphics g)
    {

        paintComponent(g);
    }




            
        
       
}
       


        
	
		

public static void main(String[] args) {
	
	 JFrame frame=new JFrame();
		Dessine d=new Dessine("","12");
        JButton b=new JButton();
		JPanel panel=new JPanel();
		
        b.setSize(50,40);
        //d.setEnable(false);
		 //d.setXY("2","");

         String num=d.getSomX();
         if(num.equals(""))
         {
            System.out.println(num);
         }
         else{
         System.out.println(num);
     }
		panel.add(d);
		panel.setSize(10,100);
		frame.setSize(200,200);
		frame.add(panel);
		frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        System.out.println(Integer.valueOf("00033"));
        

}



}