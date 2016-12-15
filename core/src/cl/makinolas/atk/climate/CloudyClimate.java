package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class CloudyClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles 
	 * 
	 * +50% dark(shadow) atk
	 * **/
	@Override
	public double newAttackState(IType type) {
		if(darkPokemon(type)){
			return 1.5;
		} else {
			return 1.0;
		}
	}
}

