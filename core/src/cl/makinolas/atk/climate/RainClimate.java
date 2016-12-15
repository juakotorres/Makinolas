package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class RainClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 *  +50% water atk
	 *  -50% fire atk
	 *  **/
	
	@Override
	public double newAttackState(IType type) {
		if(waterPokemon(type)){
			return 1.5;
		} else if (firePokemon(type)){
			return 0.5;
		} else {
			return 1.0;
		}
	}
}
