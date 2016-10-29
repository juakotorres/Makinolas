package cl.makinolas.atk.actors.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.WorldManifold;

import cl.makinolas.atk.GameConstants;
import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.HBarFliped;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.Attacks;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.stages.BossStage;

public abstract class Boss extends Monsters implements IBoss{
  
  protected int health;
  protected HBarFliped healthBar;
  protected boolean isDamaged;
  protected float vx;
  protected int width;
  protected int height;
  protected boolean dead;
  protected int walkAnimation;
  protected int hurtAnimation;
  private float hurtAcc;
  protected StateProcessor processor;

  public void setProcessor(StateProcessor sp){
    processor = sp;
  }

  public abstract void defineStates();

  public Boss(){
    defineStates();
  }

  @Override
  public void act(float delta){
    myBody.setLinearVelocity(vx, myBody.getLinearVelocity().y);
    if(processor!=null)
      processor.act(delta);
    hurtAcc -= delta;
    if(hurtAcc<=0)
      changeAnimation(walkAnimation);
  }
  
  
  @Override
  public void draw(Batch batch, float alpha){
    super.draw(batch,alpha);
    batch.draw(healthBar.getSprite(),28 * GameConstants.WORLD_FACTOR - getActualSprite().getRegionWidth() / 2 ,
        5.9f* GameConstants.WORLD_FACTOR + getActualSprite().getRegionHeight() / 2);
  }
  
  @Override
  public void damage(int damage, Attacks inflictor) {
    if(inflictor.getSource().isHero()){
      if(health - damage <= 0){
        health = 0;
      } else{
        health -= damage;
      }
      isDamaged = true;
      changeAnimation(hurtAnimation);
      hurtAcc = 0.25f;
      inflictor.setDead();
      healthBar.setCurrent(health);
      if(health <= 0){
        ((BossStage) getStage()).bossIsDead();
        dead = true;
      }
    }
  }
  
  protected void setAnimation(TextureRegion enemySprites, int width, int height){
    setMasterTexture(enemySprites, width, height);
  }
  
  @Override
  public boolean isDead(){
    return dead;
  }
  
  @Override
  public void interact(GameActor actor2, WorldManifold worldManifold) {
    actor2.interactWithBoss(this);
  }
  
  @Override
  public void interactWithAttack(Attacks attack, WorldManifold worldManifold){
    attack.manageInteractWithMonster(this);
  }
  
  @Override
  public void interactWithHero(Hero hero, WorldManifold worldManifold){
    interactWithHero2(hero);
    hero.interactWithMonster(this);
  }

  @Override
  public void endInteraction(GameActor actor2, WorldManifold worldManifold){}
  
  private float getBodySize(int size){
    return (0.5f*size)/22;
  }
  
  @Override
  public float getMonsterWidth() {
    return getBodySize(width);
  }
  
  @Override
  public float getMonsterHeight() {
    return getBodySize(height);
  }
  
  @Override
  public Monsters getBoss(){
    return this;
  }
  
  @Override
  protected void gainExp(int enemyLevel, Enemies type) {}
}
