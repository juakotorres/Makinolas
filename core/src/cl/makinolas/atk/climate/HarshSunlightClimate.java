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
	
	public int newAttackState(Friend friend) {
		ArrayList<IType> tipos = getTypeFriend(friend);
		int atk=friend.getAttack();
		for (IType t : tipos){
			if(firePokemon(t)){
				atk = (int) (friend.getAttack()*1.5);
			} else if (waterPokemon(t)) {
				atk = 0;
			} 
		}
		return atk;
	}
}
