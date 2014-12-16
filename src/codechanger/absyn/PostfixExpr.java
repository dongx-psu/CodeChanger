package codechanger.absyn;

import codechanger.ParserSymbol;

public class PostfixExpr extends Expr {
	public String label = "PostfixExpr";
	public Expr pfe;
	public ParserSymbol symbol;
	public PfType pt;
	
	public PostfixExpr(Expr e, ParserSymbol s, PfType t) {
		pfe = e;
		symbol = s;
		pt = t;
		has_para_cond = false;
		has_func_call = e.has_func_call;
		func_names.addAll(e.func_names);
	}
	
	public static enum PfType { DOT, PTR }
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(pfe.toString());
		if (pt == PfType.DOT) buf.append(".");
		else if (pt == PfType.PTR) buf.append("->");
		buf.append(symbol.toString());
		return buf.toString();
	}
}
