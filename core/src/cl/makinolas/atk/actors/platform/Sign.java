package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.GameConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

public class Sign extends Actor {

    private TextureRegion region = new TextureRegion(new Texture("Background/sign.png"));
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/mono.fnt"),Gdx.files.internal("Fonts/mono.png"),false);
    private String text;

    public Sign(String t, float x, float y){
        text = t;
        setPosition(x* GameConstants.WORLD_FACTOR,y*GameConstants.WORLD_FACTOR);
        font.setColor(1,1,1,0.9f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getX(),getY());
        font.draw(batch,text,getX()+8,getY()+44,112, Align.left, true);
    }
}
