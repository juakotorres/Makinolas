package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by nbravo on 05-07-16.
 */
public class Traveler extends Actor {

    //47 x 48
    private TextureRegion myTexReg = new TextureRegion(new Texture(Gdx.files.internal("Actors/mina_principal.png")));
    private TextureRegion[][] myTexRegions;

    public Traveler(){
        myTexRegions = myTexReg.split(47,48);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(myTexRegions[0][0], getX(), getY());
    }
}
