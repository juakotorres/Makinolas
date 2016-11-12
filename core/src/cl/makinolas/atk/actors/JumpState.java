package cl.makinolas.atk.actors;

import cl.makinolas.atk.audio.GDXMusicPlayer;
import cl.makinolas.atk.minigames.MinigameCharacter;

public class JumpState {
	protected GameActor hero;
	private int frames;
	
	protected JumpState() {
		hero = Hero.getInstance();
	}
	
	protected JumpState(GameActor actor) {
	  hero = actor;
	}
	
	public void setHero(GameActor hero) {
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
