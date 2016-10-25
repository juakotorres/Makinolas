package cl.makinolas.atk.actors.friend.EvStates;

import cl.makinolas.atk.actors.Monsters;

public class EvHpState extends EvState {

  private int evQuantity;

  public EvHpState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addHpEv(evQuantity);
  }
}
