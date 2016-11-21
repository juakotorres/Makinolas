package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.IHero;
import cl.makinolas.atk.minigames.ICharacter;
import cl.makinolas.atk.minigames.MinigameCharacter;

public class NullState extends JumpState {
	public NullState(IHero actor) {
		super(actor);
		hero.getBody().setGravityScale(1);
	}
	
	@Override
	 public void setAnimation(MinigameCharacter minigameCharacter, float delta) {
	   minigameCharacter.onAirAnimation(delta);
	}
}
