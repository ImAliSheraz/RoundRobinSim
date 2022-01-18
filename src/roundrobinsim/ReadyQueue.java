/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobinsim;
import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Ali Sheraz
 */

public class ReadyQueue {
	
	Queue<Process> readyqueue,temprq;
	public ReadyQueue(){
		readyqueue=new LinkedList<Process>();
		temprq=new LinkedList<Process>();	
	}
	public void InsertJob(Process p){
		readyqueue.add(p);
		temprq.add(p);
	}

}
