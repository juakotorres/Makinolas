package cl.makinolas.atk.actors.heroState;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;
import cl.makinolas.atk.stateEfects.CriticalHit;

public class ParalizedState extends AbstractFriendState {
		
	public ParalizedState(){
		super();
	}
	
	public ParalizedState(AbstractFriendState state){
		super(state);
	}
	
	@Override
	public void attackSecondary() {
			if(Math.random()<.8){
				super.attackSecondary();
			}
			else{
				hero.CriticalDamage();
			}
	}

	@Override
	public void attackPrimary(AbstractStage abstractStage, Friend actualFriend, World myWorld, Vector2 position,
			boolean isFacingRight, Hero hero2) {
		if(Math.random()<.8){
			super.attackPrimary(abstractStage, actualFriend, myWorld, position, isFacingRight, hero2);
		}
		else{
			hero.CriticalDamage();
		}
	}

	@Override
	public void jump(JumpState state, Hero hero2) {
		if(Math.random()<.8){
			super.jump(state, hero2);
		}
		else{
			hero.CriticalDamage();
		}
	}
	
	
}
