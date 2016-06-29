package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.ThunderBolt;

public class Pichu extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Pichu() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Pichu_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Pichu.png"))));
    setAnimations(new int[]{26,28},
                  new int[][]{new int[]{0,4},new int[]{0,5}},
                  new int[][]{new int[]{0,3}});
    setMeleeAnimation(2,2);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setMaxHealth(30);
    setMaxMagic(100);
  }
  
  public Pichu(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 10, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Pikachu.png"))));
      setAnimations(new int[]{31,25},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(1,2);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setMaxHealth(50);
      setMaxMagic(100);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Raichu.png"))));
      setAnimations(new int[]{34,30},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,4);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setMaxHealth(80);
      setMaxMagic(100);
    }
  }
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new ThunderBolt(myWorld, x, y, facingRight, source);
  }
  
  
}
