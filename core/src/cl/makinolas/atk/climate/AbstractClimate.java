package cl.makinolas.atk.climate;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.BugType;
import cl.makinolas.atk.types.DarkType;
import cl.makinolas.atk.types.DragonType;
import cl.makinolas.atk.types.ElectricType;
import cl.makinolas.atk.types.FairyType;
import cl.makinolas.atk.types.FightType;
import cl.makinolas.atk.types.FireType;
import cl.makinolas.atk.types.FlyingType;
import cl.makinolas.atk.types.GhostType;
import cl.makinolas.atk.types.GrassType;
import cl.makinolas.atk.types.GroundType;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.IceType;
import cl.makinolas.atk.types.NormalType;
import cl.makinolas.atk.types.PoisonType;
import cl.makinolas.atk.types.PsychicType;
import cl.makinolas.atk.types.RockType;
import cl.makinolas.atk.types.SteelType;
import cl.makinolas.atk.types.WaterType;

public abstract class AbstractClimate implements IClimate {
	public ArrayList<IType> getTypeFriend(Friend friend){
		return friend.getType();
	}
	
	/** este metodo es llamado al principio de cada nivel **/
	public void applyClimateEffect(Hero h){
		Array<Friend> allies= h.getAllies();
		for(int i = 0; i < allies.size; i++){
		      modifyStates(allies.get(i));
      }
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
	
	public boolean bugPokemon(BugType type) { return true;}
	public boolean darkPokemon(DarkType type) { return true;}
	public boolean dragonPokemon(DragonType type) { return true;}
	public boolean electricPokemon(ElectricType type) { return true;}
	public boolean fairyPokemon(FairyType type) { return true;}
	public boolean fightPokemon(FightType type) { return true;}
	public boolean firePokemon(FireType type) { return false;}
	public boolean flyingPokemon(FlyingType type) { return false;}
	public boolean ghostPokemon(GhostType type) { return false;}
	public boolean grassPokemon(GrassType type) { return false;}
	public boolean groundPokemon(GroundType type) { return false;}
	public boolean icePokemon(IceType type) { return false;}
	public boolean normalPokemon(NormalType type) { return false;}
	public boolean poisonPokemon(PoisonType type) { return false;}
	public boolean psychicPokemon(PsychicType type) { return false;}
	public boolean rockPokemon(RockType type) { return false;}
	public boolean steelPokemon(SteelType type) { return false;}
	public boolean waterPokemon(WaterType type) { return false;}
	
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
