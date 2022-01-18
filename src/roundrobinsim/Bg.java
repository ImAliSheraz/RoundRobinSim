/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobinsim;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

/**
 *
 * @author Ali Sheraz
 */

public class Bg extends JComponent {
	Image i;
	public Bg(Image i) {
		this.i = i;
		 
		}
	
	public void paintComponent(Graphics g) {
		 
		g.drawImage(i, 0, 0, null);  // Drawing image using drawImage method
		 
		}
		
}
