package codechanger.absyn;

import codechanger.Util;

public class BreakStmt extends JmpStmt {
	public String label = "BreakStmt";
	
	public String toString() {
		return Util.generateIndention() + "break;";
	}
}
