package codechanger.absyn;

import java.util.LinkedList;

import codechanger.Util;

public class Decls {
	public String label = "Decls";
	public LinkedList<Decl> list = new LinkedList<Decl>();
	
	public Decls(Decl d) {
		list.addFirst(d);
	}
	
	public String toString() {
		StringBuffer tmp = new StringBuffer();
		for (Decl d: list) {
			tmp.append(Util.generateIndention());
			tmp.append(d.toString());
			tmp.append("\n");
		}
		return tmp.toString();
	}
}
