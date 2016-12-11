package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.EvStates.EvState;
import cl.makinolas.atk.types.IType;

public abstract class AbstractClimate implements IClimate {
	/**TODO:
	 * Hero entrega una lista de friends
	 * Para cada friend se le alteran sus stats */
	
	public ArrayList<IType> getTypeFriend(Friend friend){
		return friend.getType();
	}
	
	public void modifyStates(Friend friend){
		friend.weatherEffect(
				newAttackState(friend), 
				newDefenseState(friend),
				newHpState(friend),
				newSpAttackState(friend),
				newSpDefenseState(friend),
				newSpeedState(friend)
				);
	}

	public boolean bugPokemon(IType type) { return false;}
	public boolean darkPokemon(IType type) { return false;}
	public boolean dragonPokemon(IType type) { return false;}
	public boolean electricPokemon(IType type) { return false;}
	public boolean fairyPokemon(IType type) { return false;}
	public boolean fightPokemon(IType type) { return false;}
	public boolean firePokemon(IType type) { return false;}
	public boolean flyingPokemon(IType type) { return false;}
	public boolean ghostPokemon(IType type) { return false;}
	public boolean grassPokemon(IType type) { return false;}
	public boolean groundPokemon(IType type) { return false;}
	public boolean icePokemon(IType type) { return false;}
	public boolean normalPokemon(IType type) { return false;}
	public boolean poisonPokemon(IType type) { return false;}
	public boolean psychicPokemon(IType type) { return false;}
	public boolean rockPokemon(IType type) { return false;}
	public boolean steelPokemon(IType type) { return false;}
	public boolean waterPokemon(IType type) { return false;}
	
	public int newAttackState(Friend friend) {return friend.getAttack();}
	public int newDefenseState(Friend friend) {return friend.getDefense();}
	public int newHpState(Friend friend) {return friend.getMaxHealth();}
	public int newSpAttackState(Friend friend) {return friend.getSpecialAttack();}
	public int newSpDefenseState(Friend friend) {return friend.getSpecialDefense();}
	public int newSpeedState(Friend friend) {return friend.getSpeed();}
}
