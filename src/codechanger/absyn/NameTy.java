package codechanger.absyn;

import codechanger.ParserSymbol;

public class NameTy extends Ty {
	public String label = "NameTy";
	
	public NameTy(ParserSymbol sym) {
		symbol = sym;
	}
	
	public String toString() {
		return symbol.toString();
	}
}
