package cl.makinolas.atk.actors.enemies;

import java.util.HashMap;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Gastly;

public class EnemyFactory {
  
  public static EnemyFactory monsterFactory = new EnemyFactory();
  private HashMap<String, EnemyFunction> map;
  
  private EnemyFactory(){
    map = new HashMap<String, EnemyFunction>();
    map.put("gastly", EnemyFunction.Gastly);
    map.put("haunter", EnemyFunction.Haunter);
    map.put("gengar", EnemyFunction.Gengar);
  }
  
  public static EnemyFactory getInstance(){
    return monsterFactory;
  }
  
  public enum EnemyFunction{
    
    Gastly{      
      @Override
      public Enemy giveEnemy(int level, int position){
        return (new Gastly(level)).returnEnemy(Hero.getInstance().getMyWorld(), position);
      }
    },
    Haunter{      
      @Override
      public Enemy giveEnemy(int level, int position){
        Friend friend = (new Gastly(level));
        friend.forceEvolve(1);
        return friend.returnEnemy(Hero.getInstance().getMyWorld(), position);
      }
    },
    Gengar{      
      @Override
      public Enemy giveEnemy(int level, int position){
        Friend friend = (new Gastly(level));
        friend.forceEvolve(2);
        return friend.returnEnemy(Hero.getInstance().getMyWorld(), position);
      }
    };
    
    public abstract Enemy giveEnemy(int level, int position);
  }
  
  public Enemy giveMonster(String nameFriend, int level, int position){
    return map.get(nameFriend.toLowerCase()).giveEnemy(level, position); 
  }
  
}
