package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class RainClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles
	 * 
	 *  +50% water atk
	 *  -50% fire atk
	 *  **/
	
	public int newAttackState(Friend friend) {
		ArrayList<IType> tipos = getTypeFriend(friend);
		int atk=friend.getAttack();
		for (IType t : tipos){
			if(waterPokemon(t)){
				atk = (int) (friend.getAttack()*1.5);
			} else if (firePokemon(t)) {
				atk = (int) (friend.getAttack()*0.5);
			} 
		}
		return atk;
	}
}
