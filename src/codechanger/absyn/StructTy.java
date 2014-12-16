package codechanger.absyn;

import codechanger.ParserSymbol;

public class StructTy extends Ty {
	public static String label = "StructTy";
	public StructDecls structdecls;
	private static int count = 0;
	
	public StructTy(StructDecls sds) {
		count++;
		symbol = ParserSymbol.getSymbol("struct !!"+count);
		structdecls = sds;
	}
	
	public StructTy(ParserSymbol sym, StructDecls sds) {
		symbol = ParserSymbol.getSymbol("struct " + sym.toString());
		structdecls = sds;
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer();
		if (!(symbol.toString().contains("!"))) buf.append(symbol.toString() + " ");
		else buf.append("struct ");
		if (structdecls != null) buf.append("{\n" + structdecls.toString() + "}");
		return buf.toString();
	}
}
