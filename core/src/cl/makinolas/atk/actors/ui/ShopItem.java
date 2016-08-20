package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ShopItem extends Actor {

    private TextureRegion reg;
    private final TextureRegion bg = new TextureRegion(new Texture(Gdx.files.internal("")));
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    private int price;

    public ShopItem(TextureRegion im, int cost){
        reg = im;
        price = cost;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float cx = getX();
        float cy = getY();
        batch.draw(bg,cx,cy);
        batch.draw(reg,cx+4,cy+4);
        font.draw(batch,"$"+price,cx,cy);
    }
}
