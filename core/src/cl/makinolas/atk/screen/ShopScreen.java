package cl.makinolas.atk.screen;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.items.Ball;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.items.Item;
import cl.makinolas.atk.actors.items.ItemFinder;
import cl.makinolas.atk.actors.ui.ShopItem;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ShopScreen extends SimpleScreen{

    private String[] items = {"Potion","Max Potion","PokeBall","GreatBall","UltraBall"};
    private int[] prices = {200,1000,200,400,800};
    private TextureRegion[] sps = {Item.textures[1][0],Item.textures[1][3], Ball.BallType.POKEBALL.texture,
            Ball.BallType.GREATBALL.texture, Ball.BallType.ULTRABALL.texture};
    private int itemSel = -1;

    public ShopScreen(Game g) {
        super(g, new Stage(new FitViewport(640,480)));
        createShopItems();
        TextButton exitButton = new TextButton("Exit Shop",  new Skin(Gdx.files.internal("Data/uiskin.json")));
        exitButton.setPosition(20, 20);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitShop();
            }
        });
        stage.addActor(exitButton);
        TextButton buyButton = new TextButton("Buy Item",  new Skin(Gdx.files.internal("Data/uiskin.json")));
        buyButton.setPosition(420, 20);
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                purchaseItem(1);
            }
        });
        stage.addActor(buyButton);
        TextButton buy10Button = new TextButton("Buy 10 Items",  new Skin(Gdx.files.internal("Data/uiskin.json")));
        buy10Button.setPosition(520, 20);
        buy10Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                purchaseItem(10);
            }
        });
        stage.addActor(buy10Button);
    }

    private void exitShop() {
        myGame.setScreen(new MenuScreen(myGame));
    }

    private void createShopItems() {
        for(int i = 0; i < items.length; i++) {
            ShopItem item = new ShopItem(sps[i],prices[i]);
            item.setPosition(120*(i%3)+20,320-60*(i/3));
            final int finalI = i;
            item.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    setItemSel(finalI);
                    return true;
                }
            });
            stage.addActor(item);
        }
    }

    public void setItemSel(int i){
        itemSel = i;
    }

    public void purchaseItem(int quantity){
        Inventory inventory = Hero.getInstance().getInventory();
        if(itemSel==-1 && inventory.payMoney(prices[itemSel]*quantity)){
            inventory.addItem(ItemFinder.getInstance().itemForName(items[itemSel]),quantity);
            SaveManager.getInstance().saveState();
        }
    }

}