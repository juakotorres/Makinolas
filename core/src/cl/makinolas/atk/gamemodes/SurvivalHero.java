package cl.makinolas.atk.gamemodes;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.OnAir;
import cl.makinolas.atk.actors.OnGround;
import cl.makinolas.atk.actors.friend.Caterpie;
import cl.makinolas.atk.actors.friend.Charmander;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.OldMewtwo;
import cl.makinolas.atk.actors.items.Inventory;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Array;

/**
 * Created by belisariops on 11/8/16.
 */
public class SurvivalHero extends Hero {
    public SurvivalHero() {
        isJumping = false;
        isFacingRight = false;
        isDamaged = false;
        isAttacking = false;
        hasEvolved = false;
        dead = false;
        changing = false;
        isAccumulatingJump = false;
        changeIndex = 0;
        jumpAccumulator = 3;
        accumulator = 0;
        vx = 0;
        platformSpeed = new Vector2(0,0);
        inertia = false;
        actualFriend = new Caterpie();

        // Set team for player;
        allies = new Array<Friend>();
        backupAllies = new Array<Friend>();
        backupIndexFriend = 0;
        //loadFriends();

        //Inventory uses loaded data
        //inventory = new Inventory(this);

        // Set actual allie
        //actualFriend = allies.get(0);
        //indexFriend = 0;
        //parent = actualFriend;

        // Set correct collider.
        myBodyDefinition = new BodyDef();
        myBodyDefinition.type = BodyDef.BodyType.DynamicBody;

        // Guardar animaciones del jugador
        setAnimation();
        changeAnimation(walkAnimation);
        state = new OnAir();
        myBodyDefinition.fixedRotation = true;
    }
}
