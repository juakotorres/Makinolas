package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ItemBag extends Actor {
    private TextureRegion normalReg = new TextureRegion(new Texture("Overlays/boxgray.png"));
    private TextureRegion selReg = new TextureRegion(new Texture("Overlays/boxblue.png"));
    private TextureRegion icon;
    private boolean selected;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    private int quantity;
    private String name;

    public ItemBag(TextureRegion reg, String nm, int quant, float x, float y, boolean sel){
        icon = reg;
        setBounds(0,0,120,48);
        setPosition(x,y);
        selected = sel;
        name = nm;
        quantity = quant;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setSelected(boolean sel){
        selected = sel;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float cx = getX();
        float cy = getY();
        if(selected)
            batch.draw(selReg,cx,cy);
        else
            batch.draw(normalReg,cx,cy);
        batch.draw(icon,cx+4,cy+8);
        font.draw(batch,name,cx+40,cy+34);
        font.draw(batch,"In Bag: "+quantity,cx+40,cy+20);
    }
}
