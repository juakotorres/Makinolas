package cl.makinolas.atk.actors.attacks;


import cl.makinolas.atk.actors.Monsters;
import cl.makinolas.atk.actors.attacks.states.SpriteState;
import cl.makinolas.atk.types.IType;

import com.badlogic.gdx.physics.box2d.World;

public class DirectionAttack extends ShootAttack {

    public DirectionAttack(SpriteState spriteState, World myWorld, float x, float y, float x2, float y2, float speed, Monsters source) {
        super(spriteState, myWorld, x, y, (x2-x) > 0, source);
        float mag = (x-x2)*(x-x2) + (y - y2)*(y - y2);
        xVelocity = speed*(x2 - x)/mag;
        yVelocity = speed*(y2 - y)/mag;
    }


}
