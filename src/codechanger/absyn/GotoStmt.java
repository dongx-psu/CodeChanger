package codechanger.absyn;

import codechanger.ParserSymbol;
import codechanger.Util;

public class GotoStmt extends JmpStmt {
	public String label = "GotoStmt";	
	public ParserSymbol symbol;
	
	public GotoStmt(ParserSymbol s) {
		symbol = s;
	}
	
	public String toString() {
		return Util.generateIndention() + "goto " + symbol.toString() + ";";
	}
}
