package server;

import java.io.Serializable;

public class SokobanSolution implements Serializable {

	private String name;
	private String solution;

	public SokobanSolution() {}

	public String getName() {
		return name;
	}
	public String getSolution() {
		return solution;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}

	public SokobanSolution(String name, String solution) {
		super();
		this.name = name;
		this.solution = solution;
	}







}
