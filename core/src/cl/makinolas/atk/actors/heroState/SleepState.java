package cl.makinolas.atk.actors.heroState;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.stages.AbstractStage;

public class SleepState extends AbstractFriendState {

	@Override
	public void attackSecondary() {
	}

	@Override
	public void attackPrimary(AbstractStage abstractStage, Friend actualFriend, World myWorld, Vector2 position,
			boolean isFacingRight, Hero hero2) {
	}

	@Override
	public void jump(JumpState state, Hero hero2) {
	}
	
	@Override
	public boolean isSinging() {
		return true;
	}
}