package cl.makinolas.atk.actors;

public class OnAir extends JumpState {
	public void secondJump() {
		Hero.getInstance().myBody.setGravityScale(1);
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,8);
		Hero.getInstance().setState(new NullState());
	}
	
	public void release() {
		Hero.getInstance().setState(new NullState());
	}
}
