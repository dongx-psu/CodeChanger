package codechanger.absyn;

import java.util.LinkedList;

public class ArrParas {
	public String label = "ArrParas";
	public LinkedList<Expr> list = new LinkedList<Expr>(); 
	
	public ArrParas(Expr ce) {
		list.addFirst(ce);
	}
	
	public String toString() {
		StringBuffer tmp = new StringBuffer();
		for (Expr e: list)
			tmp.append("[" + e.toString() + "]");
		return tmp.toString();
	}
}
