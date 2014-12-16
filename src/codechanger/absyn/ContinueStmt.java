package codechanger.absyn;

import codechanger.Util;

public class ContinueStmt extends JmpStmt {
	public String label = "ContinueStmt";
	
	public String toString() {
		return Util.generateIndention() + "continue;";
	}
}
