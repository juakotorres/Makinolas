package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by sergio on 16-06-16.
 */
public class Gastly extends Enemy {

    public Gastly(World myWorld, int heroPosition) {
        super(myWorld, new TextureRegion(new Texture(Gdx.files.internal("Actors/Gastly.png"))), new int[]{30,30}, 3,
                new int[][]{new int[]{0,1},new int[]{0,2},new int[]{0,3}},
                1, new int[][]{new int[]{0,0}},  30, heroPosition);
    }
}
