package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.items.ItemBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;

public class BagVis extends Group {

    private static BagVis instance = new BagVis();
    private Inventory inventory;
    private ArrayList<ItemBag> items;
    private int selected = -1;
    private boolean showing;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
    private TextureRegion fxblack = new TextureRegion(new Texture("Overlays/fxblack.png"));

    private BagVis(){
        hide();
    }

    public static BagVis getInstance() {
        return instance;
    }

    public void show(){
        showing =true;
        inventory = Hero.getInstance().getInventory();
        buildBagBoxes();
    }

    public void hide(){
        showing = false;
        clearChildren();
    }

    public void handleKey(int keycode){
        switch (keycode){
            case Input.Keys.A:
                if(selected==-1) break;
                inventory.selectItem1(items.get(selected).getName());
                selectItem(-1);
                break;
            case Input.Keys.S:
                if(selected==-1) break;
                inventory.selectItem2(items.get(selected).getName());
                selectItem(-1);
                break;
            case Input.Keys.LEFT:
                selectItem(Math.max(selected-1,0));
                break;
            case Input.Keys.RIGHT:
                selectItem(Math.min(selected+1,items.size()-1));
                break;
            case Input.Keys.UP:
                selectItem(Math.max(selected-3,0));
                break;
            case Input.Keys.DOWN:
                selectItem(Math.min(selected+3,items.size()-1));
                break;
        }
    }

    private void buildBagBoxes(){
        int c = 0;
        items = new ArrayList<>(inventory.getItems().size());
        float cx = getStage().getCamera().position.x;
        float cy = getStage().getCamera().position.y;
        for(ItemBox ib : inventory.getItems()){
            ItemBag item = new ItemBag(ib.getItem().getImage(),ib.getItem().getName(),ib.getQuantity(),
                        cx - 280+140*(c%4),cy + 100 - 60*(c/4),false);
            final int cf = c;
            item.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    selectItem(cf);
                    return true;
                }
            });
            items.add(item);
            addActor(item);
            c++;
        }
    }

    private void selectItem(int i){
        for(ItemBag item : items){
            item.setSelected(false);
        }
        if(i!=-1)
            items.get(i).setSelected(true);
        selected = i;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!showing) return;
        batch.draw(fxblack,getStage().getCamera().position.x-320,getStage().getCamera().position.y-198,640,438);
        large.draw(batch,"PAUSE",getStage().getCamera().position.x-48,getStage().getCamera().position.y+220);
        super.draw(batch, parentAlpha);
        if(selected!=-1)
            font.draw(batch, "Press [A] or [S] to asign the item",
                    getStage().getCamera().position.x-108, getStage().getCamera().position.y+180);
    }
}
