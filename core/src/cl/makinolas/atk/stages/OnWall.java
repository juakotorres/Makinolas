package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.NullState;

public class OnWall extends JumpState {
	public void firstJump() {
		Hero.getInstance().myBody.setGravityScale(1);
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,12);
		Hero.getInstance().setState(new NullState());
	}
	

}
