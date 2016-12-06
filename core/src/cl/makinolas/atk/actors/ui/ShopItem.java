package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.audio.GDXSoundEffectsHero;
import cl.makinolas.atk.audio.GDXSoundEffectsPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ShopItem extends Actor {

    private TextureRegion reg;
    private final TextureRegion bg = new TextureRegion(new Texture("Overlays/boxgray.png"));
    private final TextureRegion sbg = new TextureRegion(new Texture("Overlays/boxblue.png"));
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    private GDXSoundEffectsPlayer mplayer = GDXSoundEffectsHero.getInstance();
    private int price;
    private String itemname;
    private boolean selected;

    public ShopItem(TextureRegion im, int cost, String q){
        reg = im;
        price = cost;
        itemname = q;
        selected = false;
        setBounds(0,0,120,48);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
    	
        float cx = getX();
        float cy = getY();
        int quantity = Hero.getInstance().getInventory().getItemQuantity(itemname);
        if(selected)
            batch.draw(sbg,cx,cy);
        else
            batch.draw(bg,cx,cy);
        batch.draw(reg,cx+8,cy+8);
        font.draw(batch,"$"+price,cx+44,cy+34);
        font.draw(batch,"In Bag: "+quantity,cx+40,cy+20);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        mplayer.PlayMoveMenu();
    }
}
