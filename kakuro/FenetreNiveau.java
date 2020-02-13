package kakuro;
 
import javax.swing.*;
 
import kakuro.FenetreMenu.MenuListener;
 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
 
 
public class FenetreNiveau {
              
              private JPanel panel= new JPanel();
              private JPanel panelButoun1= new JPanel();
              private JPanel panelButoun2= new JPanel();
              private JPanel panelButoun3= new JPanel();
              private JPanel panelButoun4 = new JPanel();
              private JFrame frame = new JFrame();
              private JButton label = new JButton("Niveau du jeu");
              private JButton buttonFacile = new JButton("Facile");
              private JButton buttonMoyen = new JButton("Moyen");
              private JButton buttonDiff = new JButton("Difficile");
              private JButton buttonArr = new JButton("Back");
              private JPanel panelFinal = new JPanel();
              
              
              public FenetreNiveau()
              {
                           //panel.setSize(400,400);
                           
                           
                           buttonFacile.setPreferredSize(new Dimension(200,50));
                           buttonMoyen.setPreferredSize(new Dimension(200,50));
                           buttonDiff.setPreferredSize(new Dimension(200,50));
                           buttonArr.setPreferredSize(new Dimension(100,50));
                           
                           label.setPreferredSize(new Dimension(200,50));
                           label.setOpaque(false);
                           label.setBorderPainted(false);
                           label.setFocusPainted(false);
                           
                           label.setBackground(Color.WHITE);
                           buttonFacile.setBackground(Color.WHITE);
                           buttonMoyen.setBackground(Color.WHITE);
                           buttonDiff.setBackground(Color.WHITE);
                           buttonArr.setBackground(Color.WHITE);
                           
                           
                           label.setLayout(null);
                           panelButoun1.setLocation(140, 100);
                           panelButoun2.setLocation(140, 200);
                           panelButoun3.setLocation(140,300);
                           panelButoun4.setLocation(190,400);
                           
                           panelButoun1.setBackground(new Color(84,114,174));
                           panelButoun2.setBackground(new Color(84,114,174));
                           panelButoun3.setBackground(new Color(91,114,174));
                           panelButoun4.setBackground(new Color(91,114,174));
                           
                           
                           label.setFont(new Font("TimesRoman", Font.ITALIC, 30));
              
                           panelFinal.add(label);
                           panelButoun1.add(buttonFacile);
                           panelButoun2.add(buttonMoyen);
                           panelButoun3.add(buttonDiff);
                           panelButoun4.add(buttonArr);
                           panelButoun1.setSize(200,60);
                           panelButoun2.setSize(200,60);
                           panelButoun3.setSize(200,60);
                           panelButoun4.setSize(100,60);
                           
                           
                           
                           
                           
                           
                           frame.add(panelButoun1);
                           frame.add(panelButoun2);
                           frame.add(panelButoun3);
                           frame.add(panelButoun4);
                           frame.add(panelFinal);
                           frame.setSize(520, 520);
                           
                           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                           frame.setLocationRelativeTo(null);
                           frame.setVisible(true);
                           
                           
                           buttonArr.addActionListener(new NiveauListener());
                           
                           
                           
                           
              }
              
              public class NiveauListener implements ActionListener{
                           
                           public void actionPerformed(ActionEvent e) {
                                         String action = e.getActionCommand();
                                         if(action.equals("Back")) {
                                                       frame.dispose(); 
                                                       FenetreMenu menu = new FenetreMenu();
                                         
                                         }
                           }
              }
              
              public static void main(String[] args) {
                           
                           FenetreNiveau niveau = new FenetreNiveau();
              }
              
              
              
              
 
}
