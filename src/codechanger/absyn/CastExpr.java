package codechanger.absyn;

public class CastExpr extends Expr {
	public String label = "CastExpr";
	public TypeName tn;
	public Expr expr;
	
	public CastExpr(TypeName t, Expr e) {
		tn = t;
		expr = e;
		has_para_cond = expr.has_para_cond;
		has_func_call = expr.has_func_call;
		func_names.addAll(expr.func_names);
	}
	
	public String toString() {
		return "(" + tn.toString() + ")" + expr.toString();
	}
}
