package cl.makinolas.atk.utils;

import java.util.ArrayList;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.types.IType;

public class Formulas {
  
  // Experience gain formula
  public static double gainExp(int trainerPokemonLevel, int wildPokemonLevel, Enemies enemyType){
    double L = wildPokemonLevel;
    double Lp = trainerPokemonLevel;
    double b = enemyType.baseExperience;
    return (b * (L / 5) * Math.pow(2*L + 10, 2.5) / Math.pow(L + Lp + 10, 2.5)) + 1;
  }
  
  // for medium slow pokemon exp gain.
  public static double nextExpLevel(int level){
    return 6/5*Math.pow(level, 3) - 15*Math.pow(level, 2) + 100*level - 140;
  }
  
  // damage formula
  public static int getDamage(Monsters monster, int attack1, int level1, int defense2, int attackBaseDamage, ArrayList<IType> typeFriendSource, ArrayList<IType> typeFriendMonster, IType type, int criticModificator){

    double randomMultiplier = Math.random()* 0.15 + 0.85;
    double criticalRandomizer = Math.random();
    double critical = 1;
    
    double extra = 1;
    for(IType auxType:  typeFriendSource){
    	extra = extra * type.attackFromType(auxType);
    }
    
    double efectivity = 1;
    for(IType auxType: typeFriendMonster){
    	efectivity = efectivity * type.attackToType(auxType);
    }
    
    if( criticalRandomizer < getCritical(criticModificator)){
      critical = 1.5;
      monster.CriticalDamage();
      System.out.println("Critical ! Formula");
    }

    return (int) (critical*extra*efectivity*randomMultiplier*(2+(0.2*(double)level1+1)*(double)attack1)*(double)attackBaseDamage*(1/(25*(double)defense2)));
  }
  
  private static double getCritical(int criticModificator) {
	  switch (criticModificator) {
		  case 1: return 0.0625;
		  case 2: return 0.125;
		  case 3: return 0.5;
		  default: return 1;
	  }
}

// stats formula
  public static int getOtherStat(int baseStat, int level){
    return (((2 * baseStat) * level) / 100) + 5;
  }
  
  // hp formula
  public static int getHpStat(int baseStat, int level){
    return (((2 * baseStat) * level) / 100) + level + 10;
  }

  // stats formula
  public static int getOtherStatWithIV(int baseStat, int level, int iv, int evStat){
    return (int) ((double)((2 * baseStat + iv + ((double)evStat)/4) * level) / 100) + 5;
  }

  // hp formula
  public static int getHpStatWithIV(int baseStat, int level, int iv, int evStat){

    return (int) ((double) ((2 * baseStat + iv + ((double)evStat)/4) * level) / 100) + level + 10;
  }


  public static boolean checkCatch(float ballFactor, float enemyCatchRatio, int currentHP, int maxHP) {
    float bernulli = (3*maxHP - 2*currentHP) * ballFactor * enemyCatchRatio / (3*maxHP);
    return Math.random()<=bernulli;
  }

  public static int getMoney(int level, int experience) {
    float r = (float) Math.random();
    return (int) ((0.3f + 0.7f*r)*level*experience/20f);
  }
}
