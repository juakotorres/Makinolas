package cl.makinolas.atk.actors;

public class OnAir extends JumpState {
	public void secondJump() {
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,12);
	}
}
