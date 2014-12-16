package codechanger.absyn;

public class CallExpr extends Expr {
	public String label = "CallExpr";
	public Expr pfe;
	public Arguments args;
	
	public CallExpr(Expr e, Arguments ags) {
		pfe = e;
		args = ags;
		if (args != null) has_para_cond = pfe.has_para_cond || ags.has_para_cond;
		else has_para_cond = pfe.has_func_call;
		has_func_call = true;
		func_names.addLast(e.toString());
		func_names.addAll(pfe.func_names);
		if (args != null) func_names.addAll(args.func_names);
	}
	
	public String toString() {
		//TODO args may have func call
		if (args != null)
			return pfe.toString() + "(" + args.toString() + ")";
		else return pfe.toString() + "()";
	}
}
