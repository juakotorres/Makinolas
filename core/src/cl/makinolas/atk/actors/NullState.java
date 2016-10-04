package cl.makinolas.atk.actors;

public class NullState extends JumpState {
	public NullState(GameActor actor) {
		super(actor);
		hero.myBody.setGravityScale(1);
	}
}
