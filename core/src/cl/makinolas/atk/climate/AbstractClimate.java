package cl.makinolas.atk.climate;

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
	
	public double newAttackState(IType type) {return 1;}
	public double newDefenseState(IType type) {return 1;}
	public double newHpState(IType type) {return 1;}
	public double newSpAttackState(IType type) {return 1;}
	public double newSpDefenseState(IType type) {return 1;}
	public double newSpeedState(IType type) {return 1;}
}
