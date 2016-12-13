package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.friend.Friend;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PCFriend extends TeamFriendImage {
	
    private final TextureRegion bg = new TextureRegion(new Texture("Overlays/boxgray.png"));
    private final TextureRegion sbg = new TextureRegion(new Texture("Overlays/boxblue.png"));
    private boolean selected;

    public PCFriend(Friend f){
    	super(f,true);
    	this.bg.setRegionWidth(50);//FUNCIONA pero mas o menos, ya que solo corta la imagen,
    	// sol: hacer nueva imagen mas bonita, modificar la anterior, ajustar para que se corte bien.
        selected = false;
        setBounds(0,0,120,120);//48);esto es la region que reacciona al "click" del mouse
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float cx = getX();
        float cy = getY();
        if(selected)
            batch.draw(sbg,cx,cy);
        else
            batch.draw(bg,cx,cy);
        super.draw(batch, parentAlpha);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
