package codechanger.absyn;

import codechanger.ParserSymbol;

public class SmpPlDecltor extends PlDecltor {
	public String label = "SmpPlDecltor";
	public ParserSymbol symbol;
	
	public SmpPlDecltor(ParserSymbol sym) {
		symbol = sym;
	}
	
	public String toString() {
		return symbol.toString();
	}
}
