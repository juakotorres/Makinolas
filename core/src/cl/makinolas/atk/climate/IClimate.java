package cl.makinolas.atk.climate;

import java.util.ArrayList;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public interface IClimate {
	
	public ArrayList<IType> getTypeFriend(Friend friend);
	public void modifyStates(Friend friend);
	public void applyClimateEffect(Hero h);
	
	public boolean bugPokemon(IType type);
	public boolean darkPokemon(IType type);
	public boolean dragonPokemon(IType type) ;
	public boolean electricPokemon(IType type) ;
	public boolean fairyPokemon(IType type) ;
	public boolean fightPokemon(IType type) ;
	public boolean firePokemon(IType type);
	public boolean flyingPokemon(IType type);
	public boolean ghostPokemon(IType type);
	public boolean grassPokemon(IType type) ;
	public boolean groundPokemon(IType type) ;
	public boolean icePokemon(IType type) ;
	public boolean normalPokemon(IType type);
	public boolean poisonPokemon(IType type);
	public boolean psychicPokemon(IType type) ;
	public boolean rockPokemon(IType type) ;
	public boolean steelPokemon(IType type) ;
	public boolean waterPokemon(IType type);
	
	public int newAttackState(Friend friend);
	public int newDefenseState(Friend friend);
	public int newHpState(Friend friend);
	public int newSpAttackState(Friend friend);
	public int newSpDefenseState(Friend friend);
	public int newSpeedState(Friend friend);
}
