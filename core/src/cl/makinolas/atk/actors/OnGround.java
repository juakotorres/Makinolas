package cl.makinolas.atk.actors;

public class OnGround extends JumpState {
	
	public OnGround() {
		super();
	}
	
	public void firstJump() {
		Hero.getInstance().myBody.setGravityScale(0.3f);
		Hero.getInstance().setSpeed(Hero.getInstance().getBody().getLinearVelocity().x,15);
		Hero.getInstance().setState(new OnAir());
	}
	
	
	
}
