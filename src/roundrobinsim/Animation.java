/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobinsim;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author Ali Sheraz
 */

public class Animation extends JPanel implements ActionListener{
	private Timer tm;
	Toolkit t; 
	Image image[];
	int c;
	public Animation(){
		tm=new Timer(10,this);
		t=Toolkit.getDefaultToolkit(); 
		image=new Image[8];
		c=0;
                URL url = getClass().getResource("Images//robot3.png");
		image[0]=t.getImage(url.getPath());
		image[1]=t.getImage(url.getPath());
		image[2]=t.getImage(url.getPath());
		image[3]=t.getImage(url.getPath());
		image[4]=t.getImage(url.getPath());
		image[5]=t.getImage(url.getPath());
		image[6]=t.getImage(url.getPath());
		image[7]=t.getImage(url.getPath());
		tm.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		 c++;
		 if(c>7){
	         c=0;
		 }
		
	    repaint();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(image[c],0 , 0, 120, 113, null);
	}
	

}
