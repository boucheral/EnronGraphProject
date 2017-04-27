package com.ab.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.ab.obj.Edge;
import com.ab.obj.Graph;
import com.ab.obj.MinimumSpanningForest;
import com.ab.obj.MinimumSpanningTree;
import com.ab.obj.Person;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.VertexLabelRenderer;

public class Reader {

	static String FILENAME = "C:\\Python\\Enron\\Emails";
	static Graph g = new Graph();

	public static void main(String[] args) {

		File folder = new File(FILENAME);
		File[] listOfFiles = folder.listFiles();
		for(File f : listOfFiles){
			read(f.getAbsolutePath());
			System.out.println(f.getAbsolutePath());
		}
		
		showGraph(g.graph,"graph");
		
		MinimumSpanningForest msf = new MinimumSpanningForest(g);
		
		showGraph(msf.f,"MSF");
		
		MinimumSpanningTree mst = new MinimumSpanningTree(g);
		
		showGraph(mst.mst, "MST");
	}

	public static void read(String filename) {

		BufferedReader br = null;
		FileReader fr = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				g.add(sCurrentLine.toString());
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}
	
	static public void showGraph(edu.uci.ics.jung.graph.Graph<Person, Edge> g,String name) {
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container content = frame.getContentPane();

		VisualizationViewer<Person, Edge> vv =  new VisualizationViewer<Person,Edge>(new FRLayout<Person, Edge>(g), new Dimension(1000,600));
		vv.setBackground(Color.white);
		VertexLabelRenderer vertexLabelRenderer = vv.getRenderContext().getVertexLabelRenderer();
		
		vv.setVertexToolTipTransformer(new ToStringLabeller());
		
		final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		content.add(panel);

		final DefaultModalGraphMouse<Integer,Number> graphMouse = new DefaultModalGraphMouse<Integer,Number>();
		vv.setGraphMouse(graphMouse);
		
		ScalingControl scaler = new CrossoverScalingControl();
		
		Box controls = Box.createHorizontalBox();
		
		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1.1f, vv.getCenter());
			}
		});
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1 / 1.1f, vv.getCenter());
			}
		});


		JPanel zoomPanel = new JPanel(new GridLayout(0, 1));
		zoomPanel.setBorder(BorderFactory.createTitledBorder("Scale"));
		zoomPanel.add(plus);
		zoomPanel.add(minus);

		JPanel labelPanel = new JPanel(new BorderLayout());
		JPanel sliderPanel = new JPanel(new GridLayout(3, 1));
		JPanel sliderLabelPanel = new JPanel(new GridLayout(3, 1));
		JPanel offsetPanel = new JPanel(new BorderLayout());
		offsetPanel.setBorder(BorderFactory.createTitledBorder("Offset"));
		
		JSlider directedSlider = new JSlider() {
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.width /= 2;
				return d;
			}
		};
		JSlider undirectedSlider = new JSlider() {
			public Dimension getPreferredSize() {
				Dimension d = super.getPreferredSize();
				d.width /= 2;
				return d;
			}
		};
		

		controls.add(zoomPanel);
		controls.add(labelPanel);
		content.add(controls, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
	}

}
