package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.OnAir;

public class OnWall extends JumpState {
	public void firstJump() {
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,10);
		Hero.getInstance().setState(new OnAir());
	}

}
