package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Application;
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
    private boolean drawBase;

    public ImageCuad(TextureRegion reg, String r, String l, BitmapFont f,boolean mobile){
        region = reg;
        rightLabel = r;
        leftLabel = l;
        font = f;
        drawBase = !mobile;
        base = new TextureRegion(new Texture(Gdx.files.internal("Overlays/items.png"))).split(32,32)[2][3];
    }

    public ImageCuad(TextureRegion reg, String t, BitmapFont f,boolean mobile){
        this(reg,"",t,f,mobile);
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
        //if(Gdx.app.getType()== Application.ApplicationType.Android)
        if(drawBase)
            batch.draw(base,getX(),getY());
        batch.draw(region,getX(),getY());
        font.draw(batch,leftLabel,getX(),getY()+12);
        font.draw(batch,rightLabel,getX()+24,getY()+12);
    }

}
