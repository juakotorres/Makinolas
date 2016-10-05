package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MinigameFriend extends AbstractFriend {
  
  private int[][] jumpAnimation;
  
  public MinigameFriend() {
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/pikachuRun.png"))));
    setCutSprites(33,29);
    setWalkAnimation(0,1,2);
    setHurtAnimation(1);
    initDead();
  }
  
  @Override
  public int[][] getSpecialAnimation() {
    return jumpAnimation;
  }
  
  protected void setJumpAnimation(int... positions){
    this.jumpAnimation = new int[positions.length][];
    for (int i = 0; i < positions.length; i++ ){
      this.jumpAnimation[i] = new int[]{0,positions[i]};
    }
  }
  
  @Override
  protected void initLevel(int level) {
    
  }
  
}
