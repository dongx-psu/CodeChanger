package codechanger.absyn;

public class CpdIniter extends Initer {
	public String label = "CpdIniter";
	public Initers initers;
	
	public CpdIniter(Initers inters) {
		initers = inters;
	}
	
	public String toString() {
		return "{" + initers.toString() +  "}";
	}
}
