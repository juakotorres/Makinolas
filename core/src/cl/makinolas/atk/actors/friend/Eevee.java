package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import cl.makinolas.atk.types.NormalType;
import cl.makinolas.atk.types.TypeFactory;

public class Eevee extends AbstractFriend {
  
  public Eevee() {
    friend = Enemies.EEVEE;
    TextureRegion[][] faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Eevee_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Eevee.png"))));
    setAnimations(new int[]{29,24},
                  new int[][]{new int[]{0,3},new int[]{0,4}, new int[]{0,5}},
                  new int[][]{new int[]{0,0}});
    setMeleeAnimation(3,5);
    setIdleAnimation(1,2);
    setSpecialAnimation(6,7);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    newMonster();
    setMaxMagic(1000);
    addType(TypeFactory.getType("Normal"));
  }

  public Eevee(int level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(int level) {
    this.level = new Level(level);  
    setStats();
    setHealth(getMaxHealth());
  }
}
