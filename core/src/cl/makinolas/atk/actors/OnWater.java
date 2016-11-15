package cl.makinolas.atk.actors;

import cl.makinolas.atk.minigames.MinigameCharacter;

public class OnWater extends JumpState {
	
	public OnWater() {
		super();
	}
	
	public void firstJump() {
	  hero.myBody.setGravityScale(0.3f);
	  //hero.setSpeed(hero.getBody().getLinearVelocity().x,15f);
	  hero.setSpeed(hero.getBody().getLinearVelocity().x,7f);
	}
	
	@Override
  public void setAnimation(MinigameCharacter minigameCharacter, float delta) {
    minigameCharacter.onGroundAnimation(delta);
  }
}
