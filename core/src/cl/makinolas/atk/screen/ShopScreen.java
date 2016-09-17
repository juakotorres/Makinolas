package cl.makinolas.atk.screen;

import cl.makinolas.atk.actors.*;
import cl.makinolas.atk.actors.items.Ball;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.items.Item;
import cl.makinolas.atk.actors.items.ItemFinder;
import cl.makinolas.atk.actors.ui.ShopItem;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class ShopScreen extends SimpleScreen implements KeyHandable{

    private String[] items = {"PokeBall","GreatBall","UltraBall","Potion","Max Potion","MasterBall"};
    private int[] prices = {200,400,800,200,1000,10};
    private TextureRegion[] sps = {Ball.BallType.POKEBALL.texture, Ball.BallType.GREATBALL.texture,
            Ball.BallType.ULTRABALL.texture, Item.textures[1][0],Item.textures[1][3], Ball.BallType.MASTERBALL.texture};
    private int itemSel = -1;
    private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
    private Label currentItem, currentMoney;
    private Array<ShopItem> shpItems;

    public ShopScreen(Game g) {
        super(g, new Stage(new FitViewport(640,480)));
        stage.addActor(new Background("Background/ShopBG.jpg",stage.getCamera()));
        stage.addActor(new SimpleImageActor("Humans/Salesman2.png",440,60));
        createShopItems();
        Skin uskin = new Skin(Gdx.files.internal("Data/uiskin.json"));
        TextButton exitButton = new TextButton("Exit Shop", uskin);
        exitButton.setPosition(20, 20);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitShop();
            }
        });
        stage.addActor(exitButton);
        TextButton buyButton = new TextButton("Buy Item", uskin);
        buyButton.setPosition(420, 20);
        buyButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                purchaseItem(1);
            }
        });
        stage.addActor(buyButton);
        TextButton buy10Button = new TextButton("Buy 10 Items", uskin);
        buy10Button.setPosition(520, 20);
        buy10Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                purchaseItem(10);
            }
        });
        stage.addActor(buy10Button);
        currentItem = new Label("",uskin);
        currentItem.setPosition(440,60);
        stage.addActor(currentItem);
        currentMoney = new Label("$"+Hero.getInstance().getInventory().getMoney(),uskin);
        currentMoney.setPosition(280,20);
        stage.addActor(currentMoney);
        stage.addListener(new SimpleInputController(this,null));
    }

    private void exitShop() {
        myGame.setScreen(new MapScreen(myGame));
    }

    private void createShopItems() {
        Inventory inventory = Hero.getInstance().getInventory();
        shpItems = new Array<>();
        for(int i = 0; i < items.length; i++) {
            ShopItem item = new ShopItem(sps[i],prices[i],items[i]);
            item.setPosition(132*(i%3)+16,320-60*(i/3));
            final int finalI = i;
            item.addListener(new InputListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    setItemSel(finalI);
                    return true;
                }
            });
            stage.addActor(item);
            shpItems.add(item);
        }
    }

    public void setItemSel(int i){
        itemSel = i;
        currentItem.setText("Selected: "+items[i]);
        for(ShopItem item : shpItems)
            item.setSelected(false);
        shpItems.get(i).setSelected(true);
    }

    public void purchaseItem(int quantity){
        Inventory inventory = Hero.getInstance().getInventory();
        if(itemSel!=-1 && inventory.payMoney(prices[itemSel]*quantity)){
            inventory.addItem(ItemFinder.getInstance().itemForName(items[itemSel]),quantity);
            SaveManager.getInstance().saveState();
            currentMoney.setText("$"+inventory.getMoney());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Batch batch = stage.getBatch();
        batch.begin();
        large.draw(batch,"SHOP",298,460);
        batch.end();
    }

    @Override
    public void handleKey(int keycode) {
        if(keycode == Input.Keys.X)
            exitShop();
    }
}