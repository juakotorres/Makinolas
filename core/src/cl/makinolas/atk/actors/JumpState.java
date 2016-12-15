package cl.makinolas.atk.actors;

import cl.makinolas.atk.actors.ui.IHero;

import cl.makinolas.atk.minigames.MinigameCharacter;

public class JumpState {
	protected IHero hero;
	private int frames;
	
	protected JumpState() {
		hero = Hero.getInstance();
	}
	
	protected JumpState(IHero actor) {
	  hero = actor;
	}
	
	public void setHero(IHero hero) {
		this.hero= hero;
  }
	
	protected void changeState(JumpState state) {
		hero.setState(state);
	}
	
	public void firstJump() {
		
	}
	
	public void secondJump() {
		return;
	}
	
	public void jumpOnWall() {
		return;
	}
	
	public void jump() {
		
		this.firstJump();
	}
	
	public void countFrames() {
		frames++; 
		if (frames > 30)
			this.secondJump();
	}
	
	public void restarCount() {
		frames = 0;
	}
	public void release() {
	}


  public void setAnimation(MinigameCharacter minigameCharacter, float delta) {}
}
