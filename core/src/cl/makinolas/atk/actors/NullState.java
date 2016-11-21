package cl.makinolas.atk.actors;

import cl.makinolas.atk.minigames.ICharacter;
import cl.makinolas.atk.minigames.MinigameCharacter;

public class NullState extends JumpState {
	public NullState(GameActor actor) {
		super(actor);
		hero.myBody.setGravityScale(1);
	}
	
	@Override
	 public void setAnimation(MinigameCharacter minigameCharacter, float delta) {
	   minigameCharacter.onAirAnimation(delta);
	}
}
