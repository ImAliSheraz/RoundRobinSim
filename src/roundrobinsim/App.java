/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roundrobinsim;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Ali Sheraz
 */


public class App extends JFrame {
	
	JFrame f1,f2,finput,f3;
	ButtonGroup group;
	JRadioButton r1,r2,r3;
	Font font1;
	JButton jb1,add,delete,start,back,insert,done,colorc,start2;
	JLabel avgTime;
	JTable	table;
	JScrollPane scrollPane,scrollPane2;
	String dataValues[][];
	String readyData[][];
	JTextField txt1,txt2,txt3;// 
	int c,readyc;//counter
	float AvgWaitTime=0;
	float displayResult=0;
	int totalTime=0;
	String columnNames[] = { "S.No", "Process ID", "Brust Time","Arrival Time" };
	Color color;
	JobPool jobpool; // object of JobPool Class  to store all process
	Animation animation;
	PaintReadyQueue prqObj;
	GanttChart gcObj;
	ProgessBar pbObj;
	//
	public Queue<Process> JobQueue,ReadyQueue;
	//
	public App(){
		c=0;// counter
		color=Color.WHITE; // Color variable for process by default white
		jobpool=new JobPool();
		animation=new Animation();
		f1=new JFrame();
		f1.setSize(600, 420);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setResizable(false);
		f1.setLocationRelativeTo(null);
		f1.setLayout(null);
		f1.setTitle("PROCESS SCHEDULING | DEVELOPED BY IBEX DEVELOPERS");
		//
		f2=new JFrame();
		f2.setSize(800, 600);
		f2.setVisible(false);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setResizable(false);
		f2.setLocationRelativeTo(null);
		f2.setLayout(null);
		f2.setTitle("PROCESS SCHEDULING | DEVELOPED BY IBEX DEVELOPERS");
		//
		f3=new JFrame();
		f3.setSize(1000, 670);
		f3.setVisible(false);
		f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f3.setResizable(false);
		f3.setLocationRelativeTo(null);
		f3.setLayout(null);
		f3.setTitle("PROCESS SCHEDULING | DEVELOPED BY IBEX DEVELOPERS");
		//
		finput=new JFrame();
		finput.setSize(400, 250);
		finput.setVisible(false);
		finput.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		finput.setResizable(false);
		finput.setLocationRelativeTo(null);
		finput.setLayout(null);
		finput.setTitle("ENTER NEW PROCESS");
		load1();
		load2();
		txt1=new JTextField();
		txt2=new JTextField();
		txt3=new JTextField();
		txt1.setBounds(10, 70, 50, 30);
		finput.add(txt1);
		txt1.setEditable(false);
		txt2.setBounds(130, 70, 80, 30);
		// Formatting TextField to invalid
		 txt2.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        getToolkit().beep();
			        JOptionPane.showMessageDialog(null,
			        	    "Character Not Allowed",
			        	    "Invalid Input",
			        	    JOptionPane.ERROR_MESSAGE);
			        e.consume();
			      }
			    }
			  });
		 txt3.addKeyListener(new KeyAdapter() {
			    public void keyTyped(KeyEvent e) {
			      char c = e.getKeyChar();
			      if (!((c >= '0') && (c <= '9') ||
			         (c == KeyEvent.VK_BACK_SPACE) ||
			         (c == KeyEvent.VK_DELETE))) {
			        getToolkit().beep();
			        JOptionPane.showMessageDialog(null,
			        	    "Character Not Allowed",
			        	    "Invalid Input",
			        	    JOptionPane.ERROR_MESSAGE);
			        e.consume();
			      }
			    }
			  });
		finput.add(txt2);
		txt3.setBounds(280, 70, 80, 30);
		finput.add(txt3);
		 insert=new JButton("INSERT");
		 insert.setBackground(new Color(44, 62, 80));
		 insert.setForeground(Color.WHITE);
		 insert.setFont(new Font("Garamond",  Font.BOLD , 15));
		 insert.setBounds(10, 150, 100, 30);
		 insert.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					    loadData();
                                            f2.add(scrollPane);
                                            txt1.setText(Integer.toString(c+1));
                                            txt2.setText("");
                                            txt3.setText("");
					
				}
			});
		 done=new JButton("DONE");
		 done.setBackground(new Color(44, 62, 80));//rgb(243, 156, 18)
		 done.setForeground(Color.WHITE);
		 done.setFont(new Font("Garamond",  Font.BOLD , 15));
		 done.setBounds(280, 150, 100, 30);
		 done.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					finput.setVisible(false);
				}
			});
		 colorc=new JButton("CHOOSE COLOR");
		 colorc.setBackground(new Color(44, 62, 80));
		 colorc.setForeground(Color.WHITE);
		 colorc.setFont(new Font("Garamond",  Font.BOLD , 12));
		 colorc.setBounds(120, 150, 150, 30);
		 colorc.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					color=JColorChooser.showDialog(null, "Assign Color to Process", color);
				}
			});
		finput.add(done);
		finput.add(insert);
		finput.add(colorc);
		f1.validate();
	}
	public void load1(){
		try{
                URL url = getClass().getResource("Images//Background.jpg");
                BufferedImage bf = ImageIO.read(new File(url.getPath()));  
                f1.setContentPane(new Bg(bf));
			
		}catch(Exception e){
			
		}
		group = new ButtonGroup();
		r3= new JRadioButton("ROUND ROBIN",true);
		group.add(r3);
		r3.setBounds(100, 200, 300, 30);
		r3.setBackground(Color.WHITE);
		jb1=new JButton("NEXT");
		jb1.setBackground(new Color(44, 62, 80));
		jb1.setBounds(70, 250, 150, 50);
		jb1.setForeground(Color.WHITE);
		jb1.setFont(new Font("Garamond",  Font.BOLD , 20));
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				f1.dispose();
				f2.setVisible(true);
			}
		});
	
		f1.add(r3);
		f1.add(jb1);
		f1.validate();
	
		
		
	}
	public void load2(){
		JobQueue=new LinkedList<Process>();
		ReadyQueue=new LinkedList<Process>();
		try{
                        URL url = getClass().getResource("Images//Background2.jpg");
                        BufferedImage bf = ImageIO.read(new File(url.getPath())); 
			f2.setContentPane(new Bg(bf));
			
		}catch(Exception e){
			
		}
		try{
		URL url = getClass().getResource("Images//Background4.jpg");
                BufferedImage bf = ImageIO.read(new File(url.getPath()));   
		finput.setContentPane(new Bg(bf));
			
		}catch(Exception e){
			
		}
		 dataValues=new String[25][4];
		 inData();// initialize  dataValues to =""
		 table = new JTable( dataValues, columnNames );
		 table.setFont(new Font("Garamond",  Font.BOLD , 20));
		 table.setEnabled(false);
		 table.setRowHeight(30);
		 scrollPane = new JScrollPane( table );
		 scrollPane.setBounds(20, 150, 550, 400);
		 add=new JButton("ADD");
		 add.setBackground(new Color(44, 62, 80));
		 add.setForeground(Color.WHITE);
		 add.setFont(new Font("Garamond",  Font.BOLD , 25));
		 add.setBounds(600, 150, 150, 50);
		 add.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					txt1.setText(Integer.toString(c+1));
					txt2.setText("");
					txt3.setText("");
					finput.setVisible(true);
				}
			});
		 
		 delete=new JButton("DELETE");
		 delete.setBackground(new Color(44, 62, 80));
		 delete.setForeground(Color.WHITE);
		 delete.setFont(new Font("Garamond",  Font.BOLD , 25));
		 delete.setBounds(600, 250, 150, 50);
		 delete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					deleteData();
					//table = new JTable( dataValues, columnNames );
					f2.add(scrollPane);
				}
			});
		 
		 start=new JButton("START");
		 start.setBackground(new Color(44, 62, 80));
		 start.setForeground(Color.WHITE);
		 start.setFont(new Font("Garamond",  Font.BOLD , 25));
		 start.setBounds(600, 350, 150, 50);
		 start.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 prqObj=new PaintReadyQueue(totalTime);
				// prqObj.temp=jobpool.JobQueue;
				 gcObj=new GanttChart(totalTime);
				 pbObj=new  ProgessBar(c,JobQueue);
				 load3();
				 f3.setVisible(true);
				 f2.setVisible(false);
				 if(r3.isSelected()){
					 RR();
				 }
			 }
		 });
		 
		 back=new JButton("BACK");
		 back.setBackground(new Color(44, 62, 80));
		 back.setForeground(Color.WHITE);
		 back.setFont(new Font("Garamond",  Font.BOLD , 25));
		 back.setBounds(600, 450, 150, 50);
                 back.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 f1.setVisible(true);
				 f2.setVisible(false);
			 }
		 });
		 
		 f2.add(scrollPane);
		 f2.add(add);
		 f2.add(delete);
		 f2.add(start);
		 f2.add(back);
		 f2.validate();
	}
	public void load3(){
		try{
                URL url = getClass().getResource("Images//Background3.jpg");
                BufferedImage bf = ImageIO.read(new File(url.getPath()));   
		f3.setContentPane(new Bg(bf));
			
		}catch(Exception e){
			
		}
		 avgTime=new JLabel();
                 JLabel avgTimeLabel = new JLabel();
                 avgTimeLabel.setBounds(425, 350, 200, 100);
		 avgTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
                 avgTimeLabel.setText("Average Time:");
		 avgTime.setBounds(425, 380, 200, 100);
		 avgTime.setFont(new Font("SansSerif", Font.BOLD, 50));
		 start2=new JButton("BEGIN");
		 start2.setBounds(10, 10, 150, 50);
		 scrollPane2 = new JScrollPane( table );
		 scrollPane2.setBounds(20, 90, 335, 380);
		 animation.setBounds(455, 188, 120, 110);
		 prqObj.setBounds(373, 87, 300, 80);
		 gcObj.setBounds(20, 510, 955, 110);
		 pbObj.setBounds(700, 120, 165, 350);
		 
		
		 f3.add(avgTime);
                 f3.add(avgTimeLabel);
		 f3.add(gcObj);
		 f3.add(animation);
		 f3.add(scrollPane2);
		 f3.add(prqObj);
		 f3.add(pbObj);
		 f3.validate();
		 f3.revalidate();
	}//--------------------------------------------------------------------------
	public void loadData(){
		
		String str1=txt1.getText();
		String str2=txt2.getText();
		String str3=txt3.getText();
		if(str2.isEmpty()||str3.isEmpty()||color==Color.WHITE){
			getToolkit().beep();
			if(color==Color.WHITE){
				JOptionPane.showMessageDialog(null,"** Choose Color Apart From White","ERROR",JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"** ALL Fields Are Required","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}else{
			// load the the process in JobPool
			dataValues[c][0]=Integer.toString(c+1);
			dataValues[c][1]="P"+str1;
			dataValues[c][2]=str2;
			dataValues[c][3]=str3;
			totalTime=totalTime+ Integer.parseInt(str2);
			//jobpool.InsertJob(c, Integer.parseInt(str3), Integer.parseInt(str2), color);
			InsertJob(c, Integer.parseInt(str3), Integer.parseInt(str2), color);
			c++;
			color=Color.WHITE;
		}
	}
	public void deleteData(){
		//
		if(c==0){
			JOptionPane.showMessageDialog(null, "Process Queue is Empty, No Item For Deletion ");
		}
		else
		{
			c--;
			dataValues[c][0]="";
			dataValues[c][1]="";
			dataValues[c][2]="";
			dataValues[c][3]="";
		}
	}
	//-----------------------------------------------
	public void inData(){
		for(int i=0;i<25;i++){
			dataValues[i][0]="";
			dataValues[i][1]="";
			dataValues[i][2]="";
			dataValues[i][3]="";
			
		}
	}
	//----------------INSERT JOB TO JOBPOOL------------------
	public void InsertJob(int id,int atime,int cputime,Color c){
		JobQueue.add(new Process(id,atime,cputime,c));
	}
	//--------------------------UPDATE READY QUEUE--------------------------
	public void updateReadyQueue(int t){
		Process temp;
		for(int i=0;i<JobQueue.size();i++){
			temp=JobQueue.remove();
			if(temp.getArrivalTime()==t){
				insertProcess(temp);// Insert The Process To Ready Queue
				JobQueue.add(temp);
			}else{
				JobQueue.add(temp);
			}
			
		}
	}
	//--------------------------INSERT PROCESS TO READY QUEUE-------------------
	public void insertProcess(Process p){
		ReadyQueue.add(p);

		
	}
	
	
	//---------------------------------ROUND ROBIN--------------------------------------
	public void RR(){
		int n=JobQueue.size();
		Process CpuProcess=null;
		int quatum=0;
		for(int i=0;i<totalTime;i++){
			updateReadyQueue(i);
			allQueue(i);
			if(quatum==2||CpuProcess==null){
				if(!ReadyQueue.isEmpty()){
					CpuProcess=loadJob(CpuProcess,quatum);	
					CpuProcess.setLastResponseTime(i);
				}
				quatum=0;
			}
			if(CpuProcess!=null){
				gcObj.temp.add(CpuProcess);
				pbObj.JobQ.add(CpuProcess);
				CpuProcess.Decrement();
				quatum++;
				if(CpuProcess.getRemainingTime()==0){
					if(CpuProcess.getLastCpuBurst()!=0){
						CpuProcess.setLastCpuBurst(quatum);
					}
					CpuProcess.setWaitTime(i);
					AvgWaitTime=AvgWaitTime+CpuProcess.getwaitingTime();
					CpuProcess=null;
				}
			}
			
		}
		gcObj.JobQ.addAll(gcObj.temp);
		if(n>0){	
			 displayResult=AvgWaitTime/n;
		}
		avgTime.setText(Float.toString(displayResult));
		f3.add(avgTime);
		f3.revalidate();
	}
	
	public Process loadJob(Process p ,int t){
		if(p==null){
			return ReadyQueue.remove();
		}else{
			ReadyQueue.add(p);
			p.setLastCpuBurst(t);
			return ReadyQueue.poll();
		}
	}
	
	//--------------All Queue For PAINTREADYQUEUE CLASS----------------------
	public void allQueue(int  t){
		if(!ReadyQueue.isEmpty()){
			prqObj.all.addAll(ReadyQueue);
			prqObj.array[t]=ReadyQueue.size();	
		}else{
			prqObj.array[t]=0;
		}
		
		
	}
	public static void main(String args[]){
		new App();
	}

}
