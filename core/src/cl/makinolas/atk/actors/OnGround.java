package cl.makinolas.atk.actors;

import cl.makinolas.atk.minigames.ICharacter;
import cl.makinolas.atk.minigames.MinigameCharacter;

public class OnGround extends JumpState {
	
	public OnGround() {
		super();
	}
	
	public void firstJump() {
	   hero.myBody.setGravityScale(0.3f);
	  hero.setSpeed(hero.getBody().getLinearVelocity().x,15f);
	  hero.setState(new OnAir());
	}
	
	@Override
  public void setAnimation(ICharacter minigameCharacter, float delta) {
    minigameCharacter.onGroundAnimation(delta);
  }
}
