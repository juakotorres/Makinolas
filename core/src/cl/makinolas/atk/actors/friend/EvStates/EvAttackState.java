package cl.makinolas.atk.actors.friend.EvStates;

import cl.makinolas.atk.actors.Monsters;

public class EvAttackState extends EvState{

  private int evQuantity;

  public EvAttackState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addAttackEv(evQuantity);
  }
}
