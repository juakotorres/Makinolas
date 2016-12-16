package cl.makinolas.atk.actors;

import cl.makinolas.atk.minigames.MinigameCharacter;

public class LeavingWaterState extends JumpState {
	
	public LeavingWaterState() {
		super();
		
	}
	
	public void firstJump() {
	  hero.getBody().setGravityScale(0.3f);
	  hero.setSpeed(hero.getBody().getLinearVelocity().x,15f);
	  hero.setState(new OnAir());
	}
	
	@Override
  public void setAnimation(MinigameCharacter minigameCharacter, float delta) {
    minigameCharacter.onGroundAnimation(delta);
  }
}