package cl.makinolas.atk.actors;

import cl.makinolas.atk.stages.GameStage;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class AnimatedActor extends GameActor {

    private Array<Animation> anims;
    private TextureRegion[][] tiles;
    private int currentAnimation;
    protected boolean isFacingRight = false;

    protected void setMasterTexture(TextureRegion region, int tileWidth, int tileHeight){
        anims = new Array<Animation>();
        tiles = region.split(tileWidth,tileHeight);
        currentAnimation = 0;
    }

    protected int addAnimation(float duration, int[]... positions){
        Array<TextureRegion> regs = new Array<TextureRegion>(positions.length);
        for(int[] pos : positions){
            regs.add(tiles[pos[0]][pos[1]]);
        }
        anims.add(new Animation(duration,regs, Animation.PlayMode.LOOP));
        return anims.size -1;
    }

    protected void changeAnimation(int anim){
        currentAnimation = anim;
    }
    
    protected TextureRegion getActualSprite(){
      return anims.get(currentAnimation).getKeyFrame(GameStage.elapsedTime);
    }

    @Override
    public void draw(Batch batch, float alpha){
        Vector2 myPosition = myBody.getPosition();
        TextureRegion actualSprite = getActualSprite();
        batch.draw(actualSprite, myPosition.x * 20 - actualSprite.getRegionWidth() / 2 , myPosition.y * 20 - actualSprite.getRegionHeight() / 2,
                actualSprite.getRegionWidth() / 2, actualSprite.getRegionHeight() / 2, actualSprite.getRegionWidth(), actualSprite.getRegionHeight(),
                ((isFacingRight)?-1:1)*getScaleX(), getScaleY(), 0);
    }

}
