package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.NullState;

public class OnWall extends JumpState {
	public void firstJump() {
		hero.getBody().setGravityScale(1);
		hero.setSpeed(hero.getBody().getLinearVelocity().x,12);
		hero.setState(new NullState(hero));
	}
	

}
