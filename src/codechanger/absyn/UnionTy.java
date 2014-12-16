package codechanger.absyn;

import codechanger.ParserSymbol;

public class UnionTy extends Ty {
	public static String label = "UnionTy";
	public StructDecls structdecls;
	private static int count = 0;
	
	public UnionTy(StructDecls sds) {
		count++;
		symbol = ParserSymbol.getSymbol("union !!"+count);
		structdecls = sds;
	}
	
	public UnionTy(ParserSymbol sym, StructDecls sds) {
		symbol = ParserSymbol.getSymbol("union " + sym.toString());
		structdecls = sds;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (!(symbol.toString().contains("!"))) buf.append(symbol.toString() + " ");
		else buf.append("union ");
		if (structdecls != null) buf.append("{\n" + structdecls.toString() + "}");
		return buf.toString();
	}
}
