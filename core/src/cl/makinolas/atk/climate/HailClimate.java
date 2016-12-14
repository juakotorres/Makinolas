package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class HailClimate extends AbstractClimate{
	/** TODO 
	 * [] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles 
	 * 
	 * +50% ice atk
	 * **/
	
	public int newAttackState(Friend friend) {
		ArrayList<IType> tipos = getTypeFriend(friend);
		int atk=friend.getAttack();
		for (IType t : tipos){
			if(icePokemon(t)){
				atk = (int) (friend.getAttack()*1.5);
			} 
		}
		return atk;
	}
}
