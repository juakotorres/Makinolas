package cl.makinolas.atk.actors;

public abstract class Attacks extends GameActor {
  public abstract int getAttackDamage();
  public abstract Monsters getSource();
  public abstract void setDead();
}
