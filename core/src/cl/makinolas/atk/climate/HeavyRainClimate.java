package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class HeavyRainClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles
	 * 
	 *  +50% water atk
	 *  -100% fire atk
	 *  **/
	
	@Override
	public double newAttackState(IType type) {
		if(waterPokemon(type)){
			return 1.5;
		} else if (firePokemon(type)){
			return 0.0;
		} else {
			return 1.0;
		}
	}
}
