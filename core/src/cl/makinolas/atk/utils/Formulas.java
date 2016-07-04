package cl.makinolas.atk.utils;

import cl.makinolas.atk.actors.friend.Enemies;

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
  
  
}
