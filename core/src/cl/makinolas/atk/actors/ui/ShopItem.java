package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.items.Item;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShopItem extends Actor {

    private TextureRegion reg;
    private final TextureRegion bg = Item.textures[2][3];
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    private int price;

    public ShopItem(TextureRegion im, int cost){
        reg = im;
        price = cost;
        setBounds(0,0,100,48);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float cx = getX();
        float cy = getY();
        batch.draw(bg,cx,cy,100,48);
        batch.draw(reg,cx+8,cy+8);
        font.draw(batch,"$"+price,cx+42,cy+32);
    }
}
