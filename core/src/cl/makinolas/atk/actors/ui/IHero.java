package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.bosses.Boss;
import cl.makinolas.atk.actors.enemies.Enemy;
import cl.makinolas.atk.actors.friend.Enemies;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.platform.Platform;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by belisariops on 11/9/16.
 */
public interface IHero {
    public Stage getStage();

    public void setAnimation();

    public void act(float delta);

    public void moveHorizontal(int i, boolean b);

    public void jump(int i);

    public Inventory getInventory();

    public void attackPrimary();

    public void attackSecondary();

    public void prevAllie();

    public void nextAllie();

    public void foo();

    public void isNotPressingSpace();

    public void setWorld(World myWorld, Vector2 initialPosition);

    public void setWorld(World myWorld);

    void gainExp(int i, Enemies eevee);

    void setState(JumpState state);

    Body getBody();

    void setSpeed(float x, float v);

    void interactWithMonster(Enemy enemy);

    void landedPlatform(WorldManifold worldManifold, Platform platform);

    void interactWithMonster(Boss boss);
    
    void stopMovement();

    boolean isDead();

    void pressingLeft();


    void pressingRight();

    void notPressingLeft();

    void notPressingRight();

    void isNotPressingPrimaryAttack();

	public void setInsideWater(int i);
}
