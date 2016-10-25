package cl.makinolas.atk.actors.friend.EvStates;


import cl.makinolas.atk.actors.Monsters;

public class EvSpeedState extends EvState{

  private int evQuantity;

  public EvSpeedState(int quantityOfEv) {
    evQuantity = quantityOfEv;
  }

  @Override
  public void addEffortValue(Monsters monsters) {
    monsters.getMyself().addSpeedEv(evQuantity);
  }
}
