package cl.makinolas.atk.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SimpleImageActor extends Actor {

    private TextureRegion region;

    public SimpleImageActor(String path, float x, float y){
        region = new TextureRegion(new Texture(path));
        setPosition(x,y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getX(),getY());
    }


}
