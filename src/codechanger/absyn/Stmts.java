package codechanger.absyn;

import java.util.LinkedList;

public class Stmts {
	public String label = "Stmts";
	public LinkedList<Stmt>  list = new LinkedList<Stmt>();
	
	public Stmts(Stmt st) {
		list.addFirst(st);
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Stmt s: list)
			buf.append(s.toString() + "\n");
		return buf.toString();
	}
}
