package cl.makinolas.atk.actors;

public class OnGround extends JumpState {
	
	public OnGround() {
		super();
	}
	
	public void firstJump() {
	  hero.myBody.setGravityScale(0.3f);
	  hero.setSpeed(hero.getBody().getLinearVelocity().x,15f);
	  hero.setState(new OnAir());
	}
	
	
	
}
