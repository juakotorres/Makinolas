package cl.makinolas.atk.actors.attacks;

import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;

public class RayAttack extends Attacks {

	public RayAttack(World myWorld, float x, float y, boolean facingRight, Monsters source, boolean rotated) {
		super(myWorld, x, y, facingRight, source, rotated);
	}

	@Override
	public int getAttackDamage() {
	    return mySpriteState.getAttackDamage();
	}

	@Override
	public Monsters getSource() {
		return mySource;
	}

	@Override
	public void setDead() {
		dead = true;
	}

	@Override
	protected void setAnimation() {}

	@Override
	public void setSource(Monsters monster) {
		mySource = monster;
	}

}
