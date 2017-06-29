package model.commands;

import java.util.LinkedList;

import server.Model;


public abstract class Command implements general.Command {

	//data members
	private LinkedList<String> params;

	public LinkedList<String> getParams() {
		return params;
	}
	public void setParams(LinkedList<String> params) {
		this.params = params;
	}
	protected Model model;

	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}




}
