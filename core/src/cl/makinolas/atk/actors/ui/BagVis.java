package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.items.ItemBox;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;

public class BagVis extends Group {

    private Inventory inventory;
    private ArrayList<ItemBag> items;
    private int selected = -1;
    private BitmapFont font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);


    public BagVis(){
        inventory = Hero.getInstance().getInventory();
        buildBagBoxes();
    }

    private void buildBagBoxes(){
        int c = 0;
        items = new ArrayList<>(inventory.getItems().size());
        for(ItemBox ib : inventory.getItems()){
            ItemBag item = new ItemBag(ib.getItem().getImage(),ib.getItem().getName(),ib.getQuantity(),
                        20+140*(c%4),400 - 60*(c/4),false);
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
        items.get(i).setSelected(true);
        selected = i;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(selected!=-1)
            font.draw(batch,"Press [A] or [S] to asign the item",0,0);
    }
}
