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

  private String fileName;
  private String trainerName;
  private String[] myFriends;
  private BitmapFont font;
  private ShapeRenderer renderer;
  private TextureRegion typeImage;
  private TextureRegion[] friendImages;
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
    
    if(!noFile){
      myFriends = new String[SaveManager.getInstance().getSaveInstance().friends.length];
      friendImages= new TextureRegion[myFriends.length];
      FriendDescriptor[] herosFriends = SaveManager.getInstance().getSaveInstance().friends;
      for(int i = 0;  i < myFriends.length ; i++){
          myFriends[i] = herosFriends[i].name;
          friendImages[i]=MonsterFactory.getInstance().getHeroFriend(myFriends[i], 5).getFriendFaceSprite();
      }
      trainerName = SaveManager.getInstance().getSaveInstance().name;
      if(SaveManager.getInstance().getSaveInstance().sex){
        typeImage = new TextureRegion(new Texture(Gdx.files.internal("CharacterImages/hombre.png")));
      } else {
        typeImage = new TextureRegion(new Texture(Gdx.files.internal("CharacterImages/mujer.png")));
      }
    }
    font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    font.setColor(Color.RED);
    renderer = new ShapeRenderer();
    setBounds(0,0,500,100);
    setPosition(xPosition,yPosition);
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    float cx = getX();
    float cy = getY();
    float friendPosition = 100;
    
    batch.end();
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    renderer.setTransformMatrix(batch.getTransformMatrix());
    renderer.setProjectionMatrix(batch.getProjectionMatrix());
    renderer.setColor(Color.BROWN);
    renderer.rect(cx, cy, 500, 100);
    renderer.end();
    batch.begin();
    
    if(!noFile){
      font.draw(batch,trainerName,cx+20,cy+95);
      batch.draw(typeImage, cx + 10, cy, 50, 80);
      
      for(int i = 0;  i < friendImages.length  && i < 4 ; i++){
          batch.draw(friendImages[i], cx + friendPosition, cy + 30);
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
