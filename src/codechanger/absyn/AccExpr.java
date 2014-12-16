package codechanger.absyn;

public class AccExpr extends Expr {
	public String label = "AccExpr";
	public AccType atype;
	public Expr expr;
	
	public AccExpr(AccType ty, Expr e) {
		atype = ty;
		expr = e;
		has_para_cond = expr.has_para_cond;
		has_func_call = expr.has_func_call;
		func_names.addAll(expr.func_names);
	}
	
	public static enum AccType {INC, DEC}
	
	public String toString() {
		String operator;
		if (atype == AccType.INC) operator = "++";
		else operator = "--";
		return expr.toString() + operator;
	}
}
