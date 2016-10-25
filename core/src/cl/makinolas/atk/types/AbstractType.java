package cl.makinolas.atk.types;

public abstract class AbstractType implements IType{
	
	public void attackFromType(IType type){
		type.attackWith(this);
	}
	
}
