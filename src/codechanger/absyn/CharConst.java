package codechanger.absyn;

import codechanger.Util;

public class CharConst extends Expr {
	public String label = "CharConst";
	public char value;
	
	public CharConst(char ch) {
		value = ch;
		has_para_cond = false;
		has_func_call = false;
	}
	
	public String toString() {
		return Util.escape(value);
	}
}
