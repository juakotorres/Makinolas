package cl.makinolas.atk.actors.friend.EvStates;

import cl.makinolas.atk.actors.Monsters;

public class EvSpDefenseState extends EvState {

  private int evQuantity;

  public EvSpDefenseState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addSpDefenseEv(evQuantity);
  }
}
