package cl.makinolas.atk.actors.friend.EvStates;

import cl.makinolas.atk.actors.Monsters;

public class EvSpAttackState extends EvState {

  private int evQuantity;

  public EvSpAttackState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addSpAttackEv(evQuantity);
  }
}
