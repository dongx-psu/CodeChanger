package codechanger.absyn;

import java.util.LinkedList;

public class Exprs extends Expr {
	public String label = "Exprs";
	public LinkedList<Expr> list = new LinkedList<Expr>();

	public Exprs(Expr e) {
		list.addFirst(e);
		has_para_cond = e.has_para_cond;
		has_func_call = e.has_func_call;
		func_names.addAll(e.func_names);
	}
	
	public void add(Expr e) {
		list.addFirst(e);
		has_para_cond = has_para_cond || e.has_para_cond;
		has_func_call = has_func_call || e.has_func_call;
		func_names.addAll(e.func_names);
	}
	
	public String toString() {
		if (list.size() == 0) return "";
		StringBuffer tmp = new StringBuffer();
		tmp.append(list.get(0).toString());
		for (int i = 1; i < list.size(); ++i)
			tmp.append(", " + list.get(i).toString());
		return tmp.toString();
	}
}
