package codechanger.absyn;

public class UnaryExpr extends Expr {
	public String label = "UnaryExpr";
	public Expr expr;
	public UnaryType utype;
	
	public UnaryExpr(UnaryType ut, Expr e) {
		expr = e;
		utype = ut;
		has_para_cond = expr.has_para_cond;
		has_func_call = expr.has_func_call;
		func_names.addAll(expr.func_names);
	}
	
	public static enum UnaryType {
		INC, DEC, AND, TIMES, PLUS, MINUS, TIDLE, NOT, SIZEOF
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (utype == UnaryType.INC) buf.append("++");
		else if (utype == UnaryType.DEC) buf.append("--");
		else if (utype == UnaryType.AND) buf.append("&");
		else if (utype == UnaryType.TIMES) buf.append("*");
		else if (utype == UnaryType.PLUS) buf.append("+");
		else if (utype == UnaryType.MINUS) buf.append("-");
		else if (utype == UnaryType.TIDLE) buf.append("~");
		else if (utype == UnaryType.NOT) buf.append("!");
		else if (utype == UnaryType.SIZEOF) buf.append("sizeof ");
		buf.append(expr.toString());
		return buf.toString();
	}
}
