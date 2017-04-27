package com.ab.obj;

import java.util.ArrayList;
import java.util.HashMap;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class Graph {
	public DirectedSparseGraph<Person, Edge> graph;
	private HashMap<String, Person> people = new HashMap<>();

	public Graph() {
		graph = new DirectedSparseGraph<>();
	}

	public void add(String email) {
		String[] addresses = email.split("\\|");
		Person from = getPerson(addresses[0]);
		if(!graph.containsVertex(from)){
			graph.addVertex(from);
		}
		String[] to = addresses[1].split(",");
		String[] cc;
		if (addresses.length == 3){
			cc = addresses[2].split(",");
		} else {
			cc = null;
		}

		for (String t : to) {
			Person x = getPerson(t);
			if (!graph.containsVertex(x)) {
				graph.addVertex(x);
			}
			if (graph.findEdge(from, x) == null) {
				Edge e = new Edge(from, x, 1);
				graph.addEdge(e, from, x);
			} else {
				graph.findEdge(from, x).addOne();
			}
		}

		if (cc != null) {
			for (String t : cc) {
				Person x = getPerson(t);
				if (!graph.containsVertex(x)) {
					graph.addVertex(x);
				}
				Edge e = graph.findEdge(from, x);
				if (e == null) {
					e = new Edge(from, x, 1);
					graph.addEdge(e, from, x);
				} else {
					e.addOne();
				}
			}
		}

	}
	
	Person getPerson(String email){
		if (people.get(email) == null){
			Person p = new Person(email);
			people.put(email, p);
			return p;
		} else {
			return people.get(email);
		}
	}
	
	public String toString(){
		return graph.toString();
	}
	

}