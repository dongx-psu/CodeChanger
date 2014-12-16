package codechanger.absyn;

public class SmpDecltor extends Decltor {
	public String label = "SmpDecltor";
	
	public SmpDecltor(PlDecltor pd) {
		pldecltor = pd;
	}
	
	public String toString() {
		return pldecltor.toString();
	}
}
