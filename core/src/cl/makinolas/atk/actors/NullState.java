package cl.makinolas.atk.actors;

public class NullState extends JumpState {
	public NullState() {
		super();
		Hero.getInstance().myBody.setGravityScale(1);
	}
}
