package kakuro;
import javax.swing.*;
import sun.audio.*;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Musique {
	
	private JFrame frame = new JFrame();
	JButton buton = new JButton("clique");
	
	public Musique()
	{
		frame.setPreferredSize(new Dimension(222,222));
		frame.add(buton);
		//buton.addActionListener(new musicListener());
		frame.show(true);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jouerMusique();
	}
	
	public class musicListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			jouerMusique();
		}
		
	}
	

	public static void jouerMusique()
	{
		AudioPlayer MGP = AudioPlayer.player;
		AudioStream kl ;
		AudioData mol;
		ContinuousAudioDataStream loop = null;
		
		try {
			kl = new AudioStream(new FileInputStream("C:\\Users\\Dass\\eclipse-workspace\\kakuro\\src\\kakuro\\instru_dass.wav"));
			mol = kl.getData();
			loop = new ContinuousAudioDataStream(mol);
		
		} catch (IOException error) {
			// TODO: handle exception
		}
		
		MGP.start(loop);
	}
	
	public static void main(String[] args) {
		
		new Musique();
	}

}
