package pack;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Color;
import java.awt.FlowLayout;


public class Graph {

	public static void main(String[] args) {
	
	    JFrame example = new JFrame("B-splines");
	    example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    example.setSize(800, 600);
	    example.setLocationRelativeTo(null);
		
		example.setLayout(new FlowLayout());
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();		
		
		JLabel jlab=new JLabel("Number of spline:");
		panel.add(jlab);
		JTextField jtf=new JTextField("7",2);
		panel.add(jtf); 
				
		JLabel jlab1=new JLabel("Order of spline:");
		panel.add(jlab1);		
		JTextField jtf1=new JTextField("6",2);
		panel.add(jtf1);
		
		JLabel jlab2=new JLabel("Derivative of spline:");
		panel.add(jlab2);		
		JTextField jtf2=new JTextField("0",2);
		panel.add(jtf2);		
		
		example.add(panel);
		
		JButton button1 = new JButton("Calculation");
		panel1.add(button1);
		example.add(panel1);
//----------------------------------------		
		XYSeries series = new XYSeries("y = B(x)");
        XYSeriesCollection dataset = new XYSeriesCollection();
    	var per= new Spline(0, 1, 10, Integer.parseInt(jtf1.getText()));
    	for (double x = 0; x <= 1; x += 0.001) {
	            double y = per.dspline(Integer.parseInt(jtf2.getText()), 
	            		Integer.parseInt(jtf.getText()), 
	            		Integer.parseInt(jtf1.getText()), x); 
	            series.add(x, y); // Add data point to the series
	        }
    	dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",      // Chart title
                "",                // X-Axis Label
                "",                // Y-Axis Label
                dataset,                   // Dataset containing the data
                PlotOrientation.VERTICAL,  // Plot orientation
                true,                      // Include legend
                true,                      // Use tooltips
                false                      // Configure URLs
        );
	    ChartPanel panel3 = new ChartPanel(chart);
	    example.add(panel3);
	    example.setVisible(true);
//----------------------------------------		       
		button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int der,num,order;
            try {	
            der=Integer.parseInt(jtf2.getText()); 
            num=Integer.parseInt(jtf.getText()); 
            order=Integer.parseInt(jtf1.getText());
            	        	
            	var per= new Spline(0, 1, 10, order);
            	series.clear();
           
       	        for (double x = 0; x <= 1; x += 0.001) {
       	            double y = per.dspline(der, num, order, x); 
       	            series.add(x, y); // Add data point to the series
       	        } 
            }
       	     catch(NumberFormatException ee){System.out.println("Wrong format!");}
       	       dataset.removeAllSeries();
    	       dataset.addSeries(series);
    	  
            }
         });	
//----------------------------------------			
	}
		

}

