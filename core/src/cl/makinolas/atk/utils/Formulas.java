package cl.makinolas.atk.utils;

public class Formulas {
  
  // Experience gain formula
  public static double gainExp(int trainerPokemonLevel, int wildPokemonLevel){
    double L = wildPokemonLevel;
    double Lp = trainerPokemonLevel;
    return ((L / 5) * Math.pow(2*L + 10, 2.5) / Math.pow(L + Lp + 10, 2.5)) + 1;
  }
  
  // for medium slow pokemon exp gain.
  public static double nextExpLevel(int level){
    return 6.0/5*level*level*level - 15*level*level + 100*level - 140;
  }


  public static boolean checkCatch(float ballCatchability, float enemyCatchRatio, int currentHP, int maxHP) {
    float bernulli = (3*maxHP - 2*currentHP) * ballCatchability * enemyCatchRatio / (3*maxHP);
    return Math.random()<=bernulli;
  }

}
