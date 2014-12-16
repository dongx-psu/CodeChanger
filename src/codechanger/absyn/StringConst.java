package codechanger.absyn;

import codechanger.Util;

public class StringConst extends Expr {
	public String label = "StringConst";
	public String value;
	
	public StringConst(String s) {
		value = s;
		has_para_cond = false;
		has_func_call = false;
	}
	
	public String toString() {
		return Util.escape(value);
	}
}
