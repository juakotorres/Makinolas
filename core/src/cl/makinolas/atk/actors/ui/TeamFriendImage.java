package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.friend.Friend;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TeamFriendImage extends Actor{

    private Friend friend;
    private HBar hBar, mBar;
    private Sprite face;

    public TeamFriendImage(Friend f){
        setFriend(f);
    }

    private void setFriend(Friend f) {
        friend = f;
        hBar = new HBar(f.getMaxHealth(),f.getHealth(),30,4,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
        mBar = new HBar(f.getMaxMagic(),f.getMagic(),30,4,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_blue.png"))));
        face = new Sprite(friend.getFriendFaceSprite());
        face.setScale(0.75f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        hBar.setCurrent(friend.getHealth());
        mBar.setCurrent(friend.getMagic());
        face.setPosition(getX()-4,getY()+8);
        if(friend.getDead()) {
            face.setColor(1, 1, 1, 0.4f);
            mBar.setCurrent(0);
        }
        face.draw(batch,parentAlpha);
        batch.draw(hBar.getSprite(),getX(),getY()+8);
        batch.draw(mBar.getSprite(),getX(),getY()+4);
    }
}
