package cl.makinolas.atk.climate;

import cl.makinolas.atk.types.IType;

public class SandstormClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles 
	 * 
	 * +50% rock,ground,steel atk
	 * **/

	@Override
	public double newAttackState(IType type) {
		if(rockPokemon(type) || groundPokemon(type) || steelPokemon(type)){
			return 1.5;
		} else {
			return 1.0;
		}
	}
}
