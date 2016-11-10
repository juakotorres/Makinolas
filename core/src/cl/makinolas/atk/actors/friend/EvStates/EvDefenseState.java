package cl.makinolas.atk.actors.friend.EvStates;

import cl.makinolas.atk.actors.Monsters;

public class EvDefenseState extends EvState {

  private int evQuantity;

  public EvDefenseState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addDefenseEv(evQuantity);
  }
}
