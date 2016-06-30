package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ImageCuad extends Actor {

    private TextureRegion region, base;
    private String rightLabel, leftLabel;
    private BitmapFont font;

    public ImageCuad(TextureRegion reg, String r, String l, BitmapFont f){
        region = reg;
        rightLabel = r;
        leftLabel = l;
        font = f;
        base = new TextureRegion(new Texture(Gdx.files.internal("Overlays/items.png"))).split(32,32)[2][3];
    }

    public ImageCuad(TextureRegion reg, String t, BitmapFont f){
        this(reg,"",t,f);
    }


    public void setRegion(TextureRegion region) {
        this.region = region;
    }

    public void setRightLabel(String rightLabel) {
        this.rightLabel = rightLabel;
    }

    public void setLeftLabel(String leftLabel) {
        this.leftLabel = leftLabel;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(base,getX(),getY());
        batch.draw(region,getX(),getY());
        font.draw(batch,leftLabel,getX(),getY()+12);
        font.draw(batch,rightLabel,getX()+24,getY()+12);
    }

}
