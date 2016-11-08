package cl.makinolas.atk.start;

import cl.makinolas.atk.stages.Levels;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import cl.makinolas.atk.actors.Background;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.Title;
import cl.makinolas.atk.actors.friend.Charmander;
import cl.makinolas.atk.actors.friend.Elekid;
import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.friend.Magby;
import cl.makinolas.atk.actors.friend.Snivy;
import cl.makinolas.atk.actors.friend.Totodile;
import cl.makinolas.atk.actors.ui.MainBar;

import cl.makinolas.atk.screen.GameScreen;
import cl.makinolas.atk.screen.MapScreen;
import cl.makinolas.atk.stages.MapStage;

import cl.makinolas.atk.utils.SaveManager;

public class ChooseStage extends Stage {

  private Game myGame;

  private PokemonStarter[] options;
  private int lastSelected;
  private Title arrow;
  private String myName;
  private boolean mySex;
  
  public ChooseStage(Viewport v, GameScreen actualScreen, Game myGame,String myName, boolean mySex) {
    super(v);
    
    lastSelected = 0;
    this.myName = myName;
    this.mySex = mySex;
    this.myGame = myGame;
    addActor(new Background("CharacterImages/background1.png", getCamera()));
    addActor(new Title("CharacterImages/choosecharacter.png",220 ,400));
    
    arrow = new Title("CharacterImages/arrow.png", 50, 300);
    addActor(arrow);
    PokemonStarter firstOption = new PokemonStarter("CharacterImages/charmander.png", new Magby(28),120,300
        ,"CharacterImages/firetype.png", GameText.charmanderDescription,0);
    addActor(firstOption);
    PokemonStarter secondOption = new PokemonStarter("CharacterImages/snivy.png", new Snivy(5), 320,300
        ,"CharacterImages/grasstype.png", GameText.snivyDescription,1 );
    addActor(secondOption);
    PokemonStarter thirdOption = new PokemonStarter("CharacterImages/totodile.png", new Totodile(5), 220,100,
        "CharacterImages/watertype.png", GameText.totodileDescription,2);
    addActor(thirdOption);
    
    options = new PokemonStarter[]{firstOption,secondOption, thirdOption};

    //MobileGroup group = new MobileGroup(Gdx.app.getType() == Application.ApplicationType.Android);
    Gdx.input.setInputProcessor(this);

    options[0].isSelected();
  }
  
  public void act(float delta){
    super.act(delta);
    
    if (Gdx.input.isKeyJustPressed(Keys.LEFT)){
      int last = lastSelected;
      lastSelected = (lastSelected == 0)? 2 : (lastSelected - 1);
      changeArrow(last, lastSelected);
    } if (Gdx.input.isKeyJustPressed(Keys.RIGHT)){
      int last = lastSelected;
      lastSelected = (lastSelected == 2)? 0 : (lastSelected + 1);
      changeArrow(last, lastSelected);
    }
    
  }
  
  private void changeArrow(int previous, int actual) {
    lastSelected = actual;
    options[previous].notSelected();
    options[actual].isSelected();
    
    if(actual == 0){
      arrow.changeCoordinates(50, 300);
    } else if( actual == 1){
      arrow.changeCoordinates(250, 300);
    } else {
      arrow.changeCoordinates(150, 100);
    }
    
  }

  public void changeArrow(int actual){
    changeArrow(lastSelected,actual);
  }

  public void setChosenInitial(Friend friend){
    SaveManager.getInstance().startGameSave(friend, myName, mySex);
    Hero.getInstance().reset();
    MainBar.getInstance().reset();

    MapScreen mapScreen = new MapScreen(myGame,new MapStage(new FitViewport(640, 480),myGame, Levels.LEVEL1.levelSpot));
    myGame.setScreen(mapScreen);
  }

}
