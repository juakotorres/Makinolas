package cl.makinolas.atk.actors.friend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.attacks.Ember;

public class Charmander extends AbstractFriend {
  
  private TextureRegion[][] faces;
  
  public Charmander() {
    faces = new TextureRegion(new Texture(Gdx.files.internal("Actors/Charmander_faces.png"))).split(40,40);
    setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmander.png"))));
    setAnimations(new int[]{22,22},
                  new int[][]{new int[]{0,0},new int[]{0,1},new int[]{0,2},new int[]{0,1}},
                  new int[][]{new int[]{0,7}});
    setMeleeAnimation(3,5);
    setFaceSprite(faces[0][0]);
    initLevel(5);
    initDead();
    setActualEvolution(0);
    setVariables(30);
  }
  
  public Charmander(float level){
    this();
    initLevel(level);
  }
  
  @Override
  protected void initLevel(float level){
   this.level = new Level(level);
   new Evolution(this.level, 16, 1);
  }
  
  @Override
  protected void evolve(int numberOfLevel){
    if (numberOfLevel == 1 && getActualEvolution() < 1){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/charmeleon.png"))));
      setAnimations(new int[]{34,31},
          new int[][]{new int[]{0,3},new int[]{0,4},new int[]{0,5},new int[]{0,4}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(6,8);
      setFaceSprite(faces[0][1]);
      setActualEvolution(1);
      setVariables(60);
    } else if (numberOfLevel == 2 && getActualEvolution() < 2){
      setTexture(new TextureRegion(new Texture(Gdx.files.internal("Actors/Charizard.png"))));
      setAnimations(new int[]{32,32},
          new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3},new int[]{0,2}},
          new int[][]{new int[]{0,0}});
      setMeleeAnimation(4,7);
      setFaceSprite(faces[0][2]);
      setActualEvolution(2);
      setVariables(120);
    }
  }
  
  
  @Override
  public Attacks getFriendAttack(World myWorld, float x , float y, boolean facingRight, Monsters source){
    return new Ember(myWorld, x, y, facingRight, source);
  }
  
}
