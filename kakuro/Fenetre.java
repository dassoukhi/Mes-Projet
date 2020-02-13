package kakuro;


import java.awt.Dimension;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
 
public class Fenetre extends JFrame {
     
    private static final long serialVersionUID = 1L;
     
    public Fenetre(){
        this.add(new ImagePanel(new ImageIcon("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\frameKaku.jpg").getImage()));
        this.setSize(new Dimension(600,400));
        this.setVisible(true);
    }
 
}