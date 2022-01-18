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

public class JobPool {
	public Queue<Process> JobQueue,T,ReadQUeue,tempReadQUeue; // Job Queue to hold all job
	PaintReadyQueue object;
	public JobPool(){
		JobQueue=new LinkedList<Process>();
		 T=new LinkedList<Process>();
		 ReadQUeue=new LinkedList<Process>();
		 tempReadQUeue=new LinkedList<Process>();
	}
	public void InsertJob(int id,int atime,int cputime,Color c){
		JobQueue.add(new Process(id,atime,cputime,c));
		T.add(new Process(id,atime,cputime,c));
		
	}
	public void insertReady(Process p){
		ReadQUeue.add(p);
		tempReadQUeue.add(p);
	}

}
