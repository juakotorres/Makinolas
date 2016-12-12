package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;

public class ManaReduceState extends AbstractStateEfects {
	
	private float localTime = 0;
	private Attacks attack;
	private int manaDrain;

	
	public ManaReduceState(Monsters monster,int mana , Attacks attack){
		this.monster = monster;
		this.attack = attack;
		this.manaDrain = mana;
		this.drawEfects = new DrawStateEfects("StateImages/Poisoned.png", 64, 64,0, 0, (float)10000 , 2f, 8, this);
	}
	
	@Override
	public void act(float delta){
		super.act(delta);
		localTime+=delta;
		if(this.localTime>0.1){
			this.localTime= (float) (this.localTime - 0.1);
			if(monster.isHero()){
				((Hero)monster).drainMana(manaDrain, attack);
			}
		}
		
	}

	@Override
	public void affectMonsters() {}


}
