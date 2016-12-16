package cl.makinolas.atk.minigames;

import cl.makinolas.atk.actors.GameActor;
import cl.makinolas.atk.actors.JumpState;
import cl.makinolas.atk.actors.platform.Platform;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 * Created by belisariops on 11/12/16.
 */
public interface ICharacter {
    void setAnimation();
    void act(float delta);
    void checkPosition(float delta);
    void setSizeCollider(Vector2 position, boolean first);
    float getBodySize(int size);
    void draw(Batch batch, float alpha);
    void jump(int button);
    void isNotPressingSpace();
    void setState(JumpState state);
    void setSpeed(float x, float y);
    void setDead();
    boolean isDead();
    void interact(GameActor actor2, WorldManifold worldManifold);
    void interactWithPlatform(Platform platform, WorldManifold worldManifold);
    void onAirAnimation(float delta);
    void onGroundAnimation(float delta);

}
