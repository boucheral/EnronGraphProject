package com.ab.obj;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class MinimumSpanningForest {
	
	PriorityQueue<Edge> weights;
	public DelegateForest<Person, Edge> f = new DelegateForest<>();
	
	
	public MinimumSpanningForest(Graph g){
		weights = new PriorityQueue<>(g.graph.getEdges());
		build();
	}
	
	void build(){
		ArrayList<Person> used = new ArrayList<>();
		while (!weights.isEmpty()){
			Edge current = weights.poll();
			if(used.contains(current.getFrom()) && used.contains(current.getTo()))
				continue;
			
			Person to = current.getTo();
			Person from = current.getFrom();
			
			f.addVertex(to);
			f.addVertex(from);
			if (!used.contains(to))
				used.add(to);
			if(!used.contains(from))
				used.add(from);
			f.addEdge(current, from, to);
			
		}
		
		
	}
	

}
