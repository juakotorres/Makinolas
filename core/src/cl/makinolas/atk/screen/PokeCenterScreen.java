package cl.makinolas.atk.screen;

import cl.makinolas.atk.actors.*;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.items.Inventory;
import cl.makinolas.atk.actors.ui.TeamFriendImage;
import cl.makinolas.atk.utils.SaveManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PokeCenterScreen extends SimpleScreen implements KeyHandable{

    private BitmapFont large = new BitmapFont(Gdx.files.internal("Fonts/large.fnt"),Gdx.files.internal("Fonts/large.png"),false);
    private Label currentItem, currentMoney;
    private Hero hero;

    public PokeCenterScreen(Game g) {
        super(g, new Stage(new FitViewport(640,480)));
        stage.addActor(new Background("Background/PokeCenter.jpg",stage.getCamera()));
        stage.addActor(new SimpleImageActor("Humans/LadyCenter.gif",440,60));
        hero = Hero.getInstance();
        showAllies();
        Skin uskin = new Skin(Gdx.files.internal("Data/uiskin.json"));
        TextButton exitButton = new TextButton("Exit PokeCenter", uskin);
        exitButton.setPosition(20, 20);
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                exitCenter();
            }
        });
        stage.addActor(exitButton);
        TextButton healButton = new TextButton("Heal Team", uskin);
        healButton.setPosition(520, 20);
        healButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                healTeam();
            }
        });
        stage.addActor(healButton);
        currentItem = new Label("",uskin);
        currentItem.setPosition(440,60);
        stage.addActor(currentItem);
        currentMoney = new Label("$"+Hero.getInstance().getInventory().getMoney(),uskin);
        currentMoney.setPosition(280,20);
        stage.addActor(currentMoney);
        stage.addListener(new SimpleInputController(this,null));
    }

    private void showAllies() {
        for (int i = 0; i < hero.getAllies().size; i++) {
            TeamFriendImage tfimg = new TeamFriendImage(hero.getAllies().get(i), true);
            tfimg.setPosition(150+60*i,300);
            tfimg.setScale(1.5f);
            stage.addActor(tfimg);
        }
    }

    private void exitCenter() {
        myGame.setScreen(new MapScreen(myGame));
    }

    public void healTeam(){
        Inventory inventory = Hero.getInstance().getInventory();
        if(inventory.payMoney(25*hero.getAllies().size)){
            for (int i = 0; i < hero.getAllies().size; i++) {
                Friend f = hero.getAllies().get(i);
                f.setDead(false);
                f.setHealth(f.getMaxHealth());
                f.setMagic(f.getMaxMagic());
            }
            SaveManager.getInstance().saveState();
            currentMoney.setText("$"+inventory.getMoney());
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Batch batch = stage.getBatch();
        batch.begin();
        large.draw(batch,"PokeCenter",228,460);
        batch.end();
    }

    @Override
    public void handleKey(int keycode) {
        switch (keycode){
            case Input.Keys.X:
                exitCenter();
                break;
            case Input.Keys.Z:
                healTeam();
                break;
        }
    }
}
