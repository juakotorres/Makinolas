package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cl.makinolas.atk.actors.enemies.MonsterFactory;
import cl.makinolas.atk.actors.friend.FriendDescriptor;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.stages.LoadStage;
import cl.makinolas.atk.start.GameText;
import cl.makinolas.atk.utils.SaveDoesNotExistException;
import cl.makinolas.atk.utils.SaveManager;

public class LoadActor extends Actor {

  private int xPosition;
  private int yPosition;
  private String fileName;
  private String myName;
  private String[] myFriends;
  private BitmapFont font;
  private ShapeRenderer renderer;
  private TextureRegion typeImage;
  private boolean noFile;
  private LoadStage myStage;
  
  public LoadActor(String saveName, String fileName, int xPosition, int yPosition, LoadStage stage) {
    
    try {
      SaveManager.getInstance().loadData(fileName);
      noFile = false;
    } catch (SaveDoesNotExistException e) {
      noFile = true;
    }  
    
    this.myStage = stage;
    this.fileName = fileName;
    this.myName = saveName;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    
    if(!noFile){
      myFriends = new String[SaveManager.getInstance().getSaveInstance().friends.length];
      FriendDescriptor[] herosFriends = SaveManager.getInstance().getSaveInstance().friends;
      for(int i = 0;  i < myFriends.length ; i++){
          myFriends[i] = herosFriends[i].name;
      }
    }
    font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    typeImage = new TextureRegion(new Texture(Gdx.files.internal("CharacterImages/trainerSprite.png")));
    font.setColor(Color.RED);
    renderer = new ShapeRenderer();
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    float cx = xPosition;
    float cy = yPosition;
    float friendPosition = 100;
    
    batch.end();
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    renderer.setColor(Color.BROWN);
    renderer.rect(cx, cy, 500, 100);
    renderer.end();
    batch.begin();
    
    if(!noFile){
      font.draw(batch,myName,cx+20,cy+95);
      batch.draw(typeImage, cx + 10, cy);
      
      for(String face: myFriends){
        batch.draw(MonsterFactory.getInstance().getHeroFriend(face, 5).getFriendFaceSprite(), cx + friendPosition, cy + 30);
        friendPosition += 50;      
      }
    } else {
      font.draw(batch,"No save data",cx+200,cy+50);
    }

    
  
  }

  
  public void loadMap() {
    GameText.savePath = fileName;
    try {
      SaveManager.getInstance().loadData(fileName);
      Hero.getInstance().reset();
      MainBar.getInstance().reset();   
      myStage.loadMap();
    } catch (SaveDoesNotExistException e) {
      myStage.startJourney();
    }  
  }
  
}
