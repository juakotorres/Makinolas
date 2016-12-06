package cl.makinolas.atk.types;

import java.util.HashMap;

public class TypeFactory {
	
  private static final HashMap<String, IType> types = new HashMap<String, IType>();

  public static IType getType(String str) {
    IType type = (IType) types.get(str);

	if (type == null) {
	  if (str.equals("Bug")) {
		type = new BugType();
	  } else if (str.equals("Dark")) {
		type = new DarkType();
	  } else if (str.equals("Dragon")) {
		type = new DragonType();
	  } else if (str.equals("Electric")) {
		type = new ElectricType();
	  } else if (str.equals("Fairy")) {
		type = new FairyType();
	  } else if (str.equals("Fight")) {
		type = new FightType();
	  } else if (str.equals("Fire")) {
		type = new FireType();
	  } else if (str.equals("Flying")) {
		type = new FlyingType();
	  } else if (str.equals("Ghost")) {
		type = new GhostType();
	  } else if (str.equals("Grass")) {
		type = new GrassType();
	  } else if (str.equals("Ground")) {
		type = new GroundType();
	  } else if (str.equals("Ice")) {
		type = new IceType();
	  } else if (str.equals("Normal")) {
		type = new NormalType();
	  } else if (str.equals("Poison")) {
		type = new PoisonType();
	  } else if (str.equals("Psychic")) {
		type = new PsychicType();
	  } else if (str.equals("Rock")) {
		type = new RockType();
	  } else if (str.equals("Steel")) {
		type = new SteelType();
	  } else if (str.equals("Water")) {
		type = new WaterType();
	  }
	  
	  types.put(str, type);
	}
	return type;
  }
  
}
