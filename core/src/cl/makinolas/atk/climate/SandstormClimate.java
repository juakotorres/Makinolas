package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class SandstormClimate extends AbstractClimate{
	/** TODO 
	 * [x] Especificar los cambios en cada clima
	 * [] Agregar animaciones en los niveles 
	 * 
	 * +50% rock,ground,steel atk
	 * **/
	
	public int newAttackState(Friend friend) {
		ArrayList<IType> tipos = getTypeFriend(friend);
		int atk=friend.getAttack();
		for (IType t : tipos){
			if(rockPokemon(t)){
				atk = (int) (friend.getAttack()*1.5);
			} else if (groundPokemon(t)) {
				atk = (int) (friend.getAttack()*1.5);
			} else if (steelPokemon(t)) {
				atk = (int) (friend.getAttack()*1.5);
			} 
		}
		return atk;
	}
}
