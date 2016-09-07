package cl.makinolas.atk.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.actors.ui.MainBar;
import cl.makinolas.atk.utils.SaveManager;

public class LoadActor extends Actor {

  private int xPosition;
  private int yPosition;
  private String fileName;
  private String myName;
  private Array<Friend> myFriends;
  private BitmapFont font;
  private ShapeRenderer renderer;
  private TextureRegion typeImage;
  
  public LoadActor(String saveName, String fileName, int xPosition, int yPosition) {
    
    SaveManager.getInstance().loadData(fileName);  
    Hero.getInstance().reset();
    MainBar.getInstance().reset();

    
    this.fileName = fileName;
    this.myName = saveName;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    myFriends = Hero.getInstance().getAllies();
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
    
    font.draw(batch,myName,cx+20,cy+95);
    batch.draw(typeImage, cx + 10, cy);
    
    for(Friend friend : myFriends){
      batch.draw(friend.getFriendFaceSprite(), cx + friendPosition, cy + 30);
      friendPosition += 50;      
    }

    
  
  }

  public void loadMap() {
    SaveManager.getInstance().loadData(fileName);  
    Hero.getInstance().reset();
    MainBar.getInstance().reset();    
  }
  
}
