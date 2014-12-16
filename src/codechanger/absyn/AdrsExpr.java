package codechanger.absyn;

public class AdrsExpr extends Expr {
	public String label = "AdrsExpr";
	public Expr ob;
	public Exprs sit;
	
	public AdrsExpr(Expr pfe, Exprs e) {
		ob = pfe;
		sit = e;
		has_para_cond = pfe.has_para_cond || e.has_para_cond;
		has_func_call = pfe.has_func_call || e.has_func_call;
		func_names.addAll(pfe.func_names);
		func_names.addAll(e.func_names);
	}
	
	public String toString() {
		StringBuffer sits = new StringBuffer();
		for (Expr e : sit.list)
			sits.append("[" + e.toString() + "]");
		return ob.toString() + sits.toString();
	}
}
