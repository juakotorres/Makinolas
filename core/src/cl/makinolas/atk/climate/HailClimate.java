package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class HailClimate extends AbstractClimate{
	/** TODO 
	 * [] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles 
	 * 
	 * +50% ice atk
	 * **/
		
	@Override
	public double newAttackState(IType type) {
		if(icePokemon(type)){
			return 1.5;
		} else {
			return 1.0;
		}
	}
}
