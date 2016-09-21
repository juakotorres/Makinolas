package cl.makinolas.atk.actors;

public class OnGround extends JumpState {
	
	public OnGround() {
		super();
	}
	
	public void firstJump() {
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,10);
		Hero.getInstance().setState(new OnAir());
	}
	
	
}
