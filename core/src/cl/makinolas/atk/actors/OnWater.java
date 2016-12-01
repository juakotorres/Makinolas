package cl.makinolas.atk.actors;

import cl.makinolas.atk.minigames.MinigameCharacter;

public class OnWater extends JumpState {
	
	public OnWater() {
		super();
	}
	
	public void firstJump() {
				
		/*Setea cuanto sube el hero al nadar*/
		hero.setSpeed(hero.getBody().getLinearVelocity().x, 10f);
	}
	
	@Override
  public void setAnimation(MinigameCharacter minigameCharacter, float delta) {
    minigameCharacter.onGroundAnimation(delta);
  }
}
