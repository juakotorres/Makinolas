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
		return;
	}
	
	public void secondJump() {
		return;
	}
	
	public void jumpOnWall() {
		return;
	}
	
	public void jump() {
		if (frames > 60)
			secondJump();
		firstJump();
	}
	
	public void countFrames() {
		if (frames > 10) {
			this.secondJump();
			Hero.getInstance().setState(new NullState());
		}
		frames = (frames+1);
	}
	
	public void restarCount() {
		frames = 0;
	}

}
