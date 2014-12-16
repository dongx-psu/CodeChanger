package codechanger.absyn;

import codechanger.ParserSymbol;

public class Var extends Expr {
	public String label = "Var";
	public ParserSymbol symbol;
	
	public Var(ParserSymbol sym) {
		symbol = sym;
		has_para_cond = false;
		has_func_call = false;
	}
	
	public String toString() {
		return symbol.toString();
	}
}
