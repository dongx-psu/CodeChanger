package codechanger;

import java.util.Dictionary;
import java.util.Hashtable;

public class ParserSymbol {

	private String name;
	
	public ParserSymbol(String s) {
		name = s;
	}
	
	public String toString() {
		return name;
	}
	
	private static Dictionary<String, ParserSymbol> dict = new Hashtable<String, ParserSymbol>();
	
	public static ParserSymbol getSymbol(String s) {
		String ss = s.intern();
		ParserSymbol sym = dict.get(ss);
		if (null == sym) {
			sym = new ParserSymbol(ss);
			dict.put(ss, sym);
		}
		return sym;
	}
}
