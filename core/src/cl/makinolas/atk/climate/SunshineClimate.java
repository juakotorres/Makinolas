package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class SunshineClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles
	 * 
	 * +50% fire atk
	 * -50% water atk
	 *  **/
	
	@Override
	public double newAttackState(IType type) {
		if(firePokemon(type)){
			return 1.5;
		} else if (waterPokemon(type)){
			return 0.5;
		} else {
			return 1.0;
		}
	}
}
