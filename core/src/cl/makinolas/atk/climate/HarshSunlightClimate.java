package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class HarshSunlightClimate extends AbstractClimate{
	/** TODO 
	 * [] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles
	 * 
	 *  +50% fire atk
	 *  -100% water atk
	 *  
	 *  **/

	@Override
	public double newAttackState(IType type) {
		if(firePokemon(type)){
			return 1.5;
		} else if (waterPokemon(type)) {
			return 0.0;
		} else {
			return 1.0;
		}
	}
}
