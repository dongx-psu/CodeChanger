package codechanger.absyn;

import java.util.LinkedList;

public class Arguments {
	public String label = "Arguments";
	public LinkedList<Expr> list = new LinkedList<Expr>();
	public boolean has_para_cond;
	public boolean has_func_call;
	public LinkedList<String> func_names = new LinkedList<String>();
	
	public Arguments(Expr ae) {
		list.addFirst(ae);
		has_para_cond = ae.has_para_cond;
		has_func_call = ae.has_func_call;
		func_names.addAll(ae.func_names);
	}
	
	public void add(Expr ae) {
		list.addFirst(ae);
		has_para_cond = has_para_cond || ae.has_para_cond;
		has_func_call = has_func_call || ae.has_func_call;
		func_names.addAll(ae.func_names);
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
