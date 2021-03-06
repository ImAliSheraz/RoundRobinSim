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
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ali Sheraz
 */

public class GanttChart extends JPanel implements ActionListener {
	private Timer tm;
	int time;
	int n;
	int x;
	int c=0;
	public Process p;
	public Queue<Process> JobQ,temp;
	public GanttChart(int t){
		JobQ=new LinkedList<Process>();
		temp=new LinkedList<Process>();
		tm=new Timer(1000,this);
		n=0;
		x=5;
		time=t;
		tm.start();
	}

	public void actionPerformed(ActionEvent e) {
		temp.clear();
		temp.addAll(JobQ);
		n++;
		x=10;
	        repaint();
	}
	public void paint(Graphics g){
		super.paint(g);
		this.setBackground(Color.WHITE);
		if(n>=time){
			tm.stop();
		}
		for(int i=0;i<n;i++){
			
			if(temp.size()>0){
				g.setColor(temp.peek().getColor());
				g.fillRect(x, 10, 15,80);
				g.setColor(Color.WHITE);
				g.drawString(Integer.toString(1+temp.peek().getId()), x, 25);
				g.drawString(Integer.toString(1+i), x, 25+60);
				x+=20;
				temp.poll();	
			}
		}
		
		//g.fillRect(20, 300, 20, 40);
		
	}
	
	

}
