package cl.makinolas.atk.actors.attacks.states;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.RockType;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class TRockState extends SpriteState {
	
    public static int magicRequirement = 40;

    @Override
    public int getAttackDamage() {
        return 15;
    }

    @Override
    public TextureRegion getTexture() {
        return new TextureRegion(new Texture(Gdx.files.internal("Attacks/TRock.png")));
    }

    @Override
    public int getWidth() {
        return 31;
    }

    @Override
    public int getHeight() {
        return 31;
    }

    @Override
    public float getFrameTime() {
        return 0.2f;
    }

    @Override
    public Animation.PlayMode getModeAnimation() {
        return Animation.PlayMode.LOOP;
    }

    @Override
    public int getInitialSprite() {
        return 0;
    }

    @Override
    public int getFinalSprite() {
        return 0;
    }

    @Override
    public int getTypeAttack(Monsters monster) {
        return myAttack.getPhysicalAttackDamage(monster);
    }

	@Override
	public IType getType() {
		return RockType.getInstance();
	}

}
