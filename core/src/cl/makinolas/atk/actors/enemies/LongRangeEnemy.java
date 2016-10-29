package cl.makinolas.atk.actors.enemies;

import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.friend.Friend;
import com.badlogic.gdx.physics.box2d.World;

public class LongRangeEnemy extends Enemy {

	public LongRangeEnemy(World myWorld, int health, int heroPosition, int level, Enemies friend, Friend parent) {
		super(myWorld,health,heroPosition,3,true, level,friend, parent);
	}

}
