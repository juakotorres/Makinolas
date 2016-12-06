package cl.makinolas.atk.types;

import java.util.HashMap;

public class TypeFactory {
	
  private static final HashMap<String, IType> types = new HashMap<String, IType>();

  public static IType getType(String str) {
    IType type = (IType) types.get(str);
    
	if (type == null) {
	  switch (str) {
        case "Bug":  type = new BugType();
                 break;
        case "Dark":  type = new DarkType();
                 break;
        case "Dragon":  type = new DragonType();
                 break;
        case "Electric":  type = new ElectricType();
                 break;
        case "Fairy":  type = new FairyType();
                 break;
        case "Fight":  type = new FightType();
                 break;
        case "Fire":  type = new FireType();
                 break;
        case "Flying":  type = new FlyingType();
                 break;
        case "Ghost":  type = new GhostType();
                 break;
        case "Grass": type = new GrassType();
                 break;
        case "Ground": type = new GroundType();
                 break;
        case "Ice": type = new IceType();
                 break;
        case "Normal": type = new NormalType();
        		 break;
        case "Poison": type = new PoisonType();
        		 break;
        case "Psychic": type = new PsychicType();
        		 break;
        case "Rock": type = new RockType();
                 break;
        case "Steel": type = new SteelType();
                 break;
        case "Water": type = new WaterType();
                 break;
      }
		
	  types.put(str, type);
	}
	return type;
  }
  
}
