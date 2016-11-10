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
    private boolean big;

    public TeamFriendImage(Friend f, boolean big){
        this.big = big;
        setFriend(f);
    }

    public TeamFriendImage(Friend f){
        this(f,false);
    }

    private void setFriend(Friend f) {
        friend = f;
        int factor = (big)?2:1;
        hBar = new HBar(f.getMaxHealth(),f.getHealth(),15+15*factor,2+2*factor,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
        mBar = new HBar(f.getMaxMagic(),f.getMagic(),15+15*factor,2+2*factor,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_blue.png"))));
        face = new Sprite(friend.getFriendFaceSprite());
        face.setScale(0.5f+0.25f*factor);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        hBar.setCurrent(friend.getHealth());
        mBar.setCurrent(friend.getMagic());
        face.setPosition(getX()-4+(big?6:0),getY()+8);
        if(friend.getDead()) {
            face.setColor(1, 1, 1, 0.4f);
            mBar.setCurrent(0);
        }
        else{
            face.setColor(1,1,1,1);
        }
        face.draw(batch,parentAlpha);
        batch.draw(hBar.getSprite(),getX(),getY()+8);
        batch.draw(mBar.getSprite(),getX(),getY()+4-(big?2:0));
    }
}
