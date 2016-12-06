package cl.makinolas.atk.stateEfects;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.types.IType;

public class BurnedStateEffect extends AbstractStateEfects {

	private Friend friend;
	private int damage;
	private float localTime = 0;
	private boolean noEfect = false;
	private int attackValue;
	private Attacks attack;
	
	public BurnedStateEffect(Monsters monster, Attacks attack){
		this.monster = monster;
		this.attack = attack;
		this.friend = monster.getMyself();
		for(IType type: monster.getMyself().getType()){
			if(type.isFire()){
				noEfect = true;
				this.drawEfects = new DrawStateEfects("StateImages/fuego.png", 64, 64,10, -15, 0f, 0f , 8, this);
				return;
			}
		}
		double rand = 2 + Math.random()*2;
		this.drawEfects = new DrawStateEfects("StateImages/fuego.png", 64, 64,10, -15, (float)rand, 1f , 8, this);
		this.attackValue = this.friend.getAttackiv();;
	}
	
	@Override
	public void affectMonsters() {
		friend.setAttackiv(attackValue/2);
		damage = friend.getMaxHealth()/8;	
	}
	
	@Override
	public void destroy() {
		friend.setAttackiv(attackValue);
		super.destroy();
	}

	
	@Override
	public void act(float delta){
		super.act(delta);
		if(this.noEfect){
			return;
		}
		localTime+=delta;
		if(this.localTime>1){
			this.localTime--;
			monster.damage(damage, attack);
		}
		
	}

}
