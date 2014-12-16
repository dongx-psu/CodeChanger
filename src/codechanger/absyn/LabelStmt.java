package codechanger.absyn;

import codechanger.ParserSymbol;

public class LabelStmt extends Stmt {
	public String label = "LabelStmt";
	public ParserSymbol symbol;
	
	public LabelStmt(ParserSymbol s) {
		symbol = s;
	}
	
	public String toString() {
		return symbol.toString() + ":";
	}
}
