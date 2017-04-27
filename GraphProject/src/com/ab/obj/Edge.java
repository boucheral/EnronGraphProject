package com.ab.obj;

public class Edge implements Comparable<Edge>{
	
	private Person from;
	private Person to;
	private int weight;
	
	public Edge(){
		
	}
	public Edge(Person from, Person to){
		this.to = to;
		this.from = from;
		weight = 1;
	}
	public Edge(Person from, Person to, int weight){
		this.to = to;
		this.from = from;
		this.weight = weight;
	}
	public Person getFrom() {
		return from;
	}
	public void setFrom(Person from) {
		this.from = from;
	}
	public Person getTo() {
		return to;
	}
	public void setTo(Person to) {
		this.to = to;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void addOne(){
		weight++;
	}
	public boolean equals(Edge other) {
		if (other == null){
			return to == null && from == null;
		}
		return this.getTo().equals(other.getTo()) && this.getFrom().equals(other.getFrom());
	}
	public String toString(){
		return "[" + from.toString() + "," + to.toString() + "," + weight + "]";
	}
	public int compareTo(Edge e) {
		if (e.equals(null))
			return 0;
		
		return (e.getWeight() < this.getWeight() ? -1 : (e.getWeight() == this.getWeight() ? 0 : 1));
	}
}
