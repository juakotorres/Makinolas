package cl.makinolas.atk.actors;

public class JumpState {
	protected Hero hero;
	private int frames;
	
	protected JumpState() {
		hero = Hero.getInstance();
	}
	public void setHero(Hero hero) {
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

}
