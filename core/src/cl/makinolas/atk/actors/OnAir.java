package cl.makinolas.atk.actors;

public class OnAir extends JumpState {
	public void secondJump() {
		hero.myBody.setGravityScale(1);
		hero.setSpeed(hero.getBody().getLinearVelocity().x,8);
		hero.setState(new NullState(hero));
	}
	
	public void release() {
		hero.setState(new NullState(hero));
	}
}
