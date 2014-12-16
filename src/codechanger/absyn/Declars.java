package codechanger.absyn;

import java.util.LinkedList;

import codechanger.Util;

public class Declars {
	public String label = "Declars";
	public LinkedList<Declar> list = new LinkedList<Declar>();
	
	public Declars(Declar d) {
		list.addFirst(d);
	}
	
	public String toString() {
		StringBuffer tmp = new StringBuffer();
		for (Declar d : list) {
			tmp.append(Util.generateIndention() + d.toString());
			tmp.append("\n");
		}
		return tmp.toString();
	}
}
