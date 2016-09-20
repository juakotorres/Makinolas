package cl.makinolas.atk.stages;

import cl.makinolas.atk.actors.*;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.ui.MapStageActor;
import cl.makinolas.atk.actors.ui.MobileGroup;
import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.ShopScreen;

public class MapStage extends Stage implements KeyHandable{

    private Traveler traveler;

    private Levels[] levels;
    private int current = 0;
    private int maxAllowed = 3;
    private Game myGame;

    public MapStage(Viewport v, Game game) {
        super(v);
        myGame = game;

        //Adding the actors to the stage (currently just the background, the traveler and the levels)
        addActor(new Background("Background/mapa.png", getCamera()));

        buildLevels();
        maxAllowed = Math.min(Hero.getInstance().getMaxLevelUnlocked(),levels.length);

        // Add floors
        for (int i = 0; i < levels.length; i++) {
            addActor(new MapStageActor(i, maxAllowed<=i, this, levels[i].mapx, levels[i].mapy));
        }

        traveler = new Traveler();
        addActor(traveler);

        //Input configurations
        addListener(new SimpleInputController(this, new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android)));
        Gdx.input.setInputProcessor(this);

        current = maxAllowed - 1;

        TextButton shopButton = new TextButton("Enter Shop",  new Skin(Gdx.files.internal("Data/uiskin.json")));
        shopButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                enterShop();
            }
        });
        shopButton.setPosition(20,12);
        addActor(shopButton);

        TextButton startButton = new TextButton("Start",  new Skin(Gdx.files.internal("Data/uiskin.json")));
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                startLevel();
            }
        });
        startButton.setPosition(640-80,12);
        startButton.setWidth(60);
        addActor(startButton);

        //The initial position of the traveler
        moveToLevel(current);
    }

    public void moveToLevel(int c) {
        current = c;
        traveler.setPosition(levels[c].mapx*20, levels[c].mapy*20);
    }

    private void buildLevels() {
        levels = Levels.values();
    }

    //public int getCurrentLevel(){return current;}

    public void nextLevel(){
        moveToLevel(current+1);
    }

    public void prevLevel(){
        moveToLevel(current-1);
    }

    public void handleKey(int keycode){
        if(keycode == Input.Keys.Z){
            startLevel();
            return;
        }
        else if(keycode == Input.Keys.X){
            enterShop();
            return;
        }
        int keynext = -1;
        int keyprev = -1;
        if(current > 0){
            if(levels[current].mapx > levels[current-1].mapx) keyprev = Input.Keys.LEFT;
            else if(levels[current].mapx < levels[current-1].mapx) keyprev = Input.Keys.RIGHT;
            else if(levels[current].mapy < levels[current-1].mapy) keyprev = Input.Keys.UP;
            else if(levels[current].mapy > levels[current-1].mapy) keyprev = Input.Keys.DOWN;
        }
        if(current < maxAllowed-1){
            if(levels[current].mapx > levels[current+1].mapx) keynext = Input.Keys.LEFT;
            else if(levels[current].mapx < levels[current+1].mapx) keynext = Input.Keys.RIGHT;
            else if(levels[current].mapy < levels[current+1].mapy) keynext = Input.Keys.UP;
            else if(levels[current].mapy > levels[current+1].mapy) keynext = Input.Keys.DOWN;
        }
        if(keycode == keyprev)
            prevLevel();
        else if(keycode == keynext)
            nextLevel();
    }

    private void enterShop() {
        myGame.setScreen(new ShopScreen(myGame));
    }

    public void startLevel(){
        GameScreen gameScreen = new GameScreen(myGame);
        if(!levels[current].bossLevel)
            gameScreen.setStage(new GameStage(new FitViewport(640,480), gameScreen, myGame, levels[current]));
        else
            gameScreen.setStage(new BossStage(new FitViewport(640,480), gameScreen, myGame, levels[current]));
        myGame.setScreen(gameScreen);
    }

}
