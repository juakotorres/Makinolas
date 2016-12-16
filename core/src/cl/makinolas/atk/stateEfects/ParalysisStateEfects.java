package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;

public class ParalysisStateEfects extends AbstractStateEfects {

	private float localTime = 0;

	public ParalysisStateEfects(Monsters monster){
		this.drawEfects = new DrawStateEfects("StateImages/Paralized.png", 64, 64, 0, 0, (float)(8*(Math.random()+1)), 2f, 4, this);
		this.monster = monster;
	}

	@Override
	public void affectMonsters() {
		monster.paraliza3();
	}
	
	@Override
	public void destroy() {
		monster.desparaliza3();
		super.destroy();
	}
	
	@Override
	public void act(float delta){
		super.act(delta);

		localTime+=delta;
		if(this.localTime>.5){
			this.localTime-=.5;
			if(Math.random()>.5){
				monster.stun();
			}
		}
		
	}

}
