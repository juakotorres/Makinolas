package cl.makinolas.atk.actors.attacks.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.stateEfects.SleepStateEffect;
import cl.makinolas.atk.types.IType;
import cl.makinolas.atk.types.NormalType;
import cl.makinolas.atk.types.TypeFactory;

public class SingState extends SpriteState {


    private static int magicRequirement = 50;
	
    @Override
    public int getAttackDamage() {
        return 0;
    }

    @Override
    public void initializeBody(float x, float y) {
        myAttack.initializeBody(x, y);
    }

    @Override
    public TextureRegion getTexture() {
        return new TextureRegion(new Texture(Gdx.files.internal("Attacks/music.png")));
    }

    @Override
    public int getWidth() {
        return 40;
    }

    @Override
    public int getHeight() {
        return 40;
    }
    
    @Override
    public int getBodyWidth() {
        return 256;
      }

    @Override
      public int getBodyHeight() {
        return 180;
      }

    @Override
    public float getFrameTime() {
        return 0.4f;
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
        return 2;
    }

    @Override
    public float getAttackTime() {
        return 16 * getFrameTime();
    }

    @Override
    public int getTypeAttack(Monsters monster) {
        return myAttack.getSpecialAttackDamage(monster);
    }

	@Override
	public IType getType() {
		return TypeFactory.getType("Normal");
	}
	
	@Override
	public void secondaryEfectsToAfected(Monsters monster) {
		monster.addState(new SleepStateEffect(monster), 50);
	}
	
	public static int getMagicRequirement(){
		return magicRequirement;
	}

}
