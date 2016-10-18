package cl.makinolas.atk.start;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import cl.makinolas.atk.actors.friend.Friend;
import cl.makinolas.atk.utils.Formulas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class PokemonStarter extends Actor {
  
  private BitmapFont font;
  private NinePatch base;
  private ShapeRenderer renderer;
  private Friend friend;
  private int xPosition;
  private int yPosition;
  private int width;
  private int height;
  private TextureRegion backgroundImage;
  private TextureRegion typeImage;
  private String firstText;
  private String secondText;
  private String thirdText;
  private String fourthText;
  private boolean drawStats;

  public PokemonStarter(String imagePath, final Friend friend, int xPosition, int yPosition,
                        String typeImagePath, String description, final int pos) {
    
    drawStats = false;
    this.friend = friend;
    this.xPosition = xPosition;
    this.yPosition = yPosition;
    backgroundImage = new TextureRegion(new Texture(Gdx.files.internal(imagePath)));
    typeImage = new TextureRegion(new Texture(Gdx.files.internal(typeImagePath)));
    this.width = backgroundImage.getRegionWidth();
    this.height = backgroundImage.getRegionHeight();
    
    setTexts(description, 0);
    
    base = new NinePatch(new Texture("Overlays/superdupertextarea.png"),12,12,12,12);
    font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    renderer = new ShapeRenderer();
    setBounds(0,0,width,height);
    setPosition(xPosition-width/2,yPosition-height/2);
    addListener(new InputListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if(drawStats) ((ChooseStage) getStage()).setChosenInitial(friend);
        else ((ChooseStage) getStage()).changeArrow(pos);
        return true;
      }
    });
  }
  
  public void isSelected(){
    drawStats = true;
  }
  
  public void notSelected(){
    drawStats = false;
  }
  
  @Override
  public void act(float delta){
    //int mouseX = Gdx.input.getX();
    //int mouseY = Math.abs(Gdx.input.getY() - 480);

    //if(mouseX <= xPosition + width/2 && mouseX >= xPosition - width/2 && mouseY >= yPosition - height/2 && mouseY <= yPosition + height / 2){
     // drawStats = true;
    //} 
    
    if(Gdx.input.isKeyPressed(Keys.Z) && drawStats){
      ((ChooseStage) getStage()).setChosenInitial(friend);
    }
  }
  
  private void setTexts(String paragraph, int i) {
    int partOfText = i * 270;
    if(paragraph.length() < 30 + partOfText){
      firstText = paragraph.substring(partOfText);
    } else {
      firstText = paragraph.substring(partOfText, 30 + partOfText);
    }
    
    if(paragraph.length() < 60 + partOfText && paragraph.length() > 30 + partOfText){
      secondText = paragraph.substring(30 + partOfText);
    } else if (paragraph.length() > 60 + partOfText) {
      secondText =  paragraph.substring(30 + partOfText, 60 + partOfText);
    } else {
      secondText = "";
    }
    
    if(paragraph.length() < 90 + partOfText && paragraph.length() > 60 + partOfText){
      thirdText = paragraph.substring(60 + partOfText);
    } else if (paragraph.length() > 90 + partOfText) {
      thirdText =  paragraph.substring(60 + partOfText, 90 + partOfText);
    } else {
      thirdText = "";
    }
    
    if(paragraph.length() < 120 + partOfText && paragraph.length() > 90 + partOfText){
      fourthText = paragraph.substring(90 + partOfText);
    } else if (paragraph.length() > 120 + partOfText) {
      fourthText =  paragraph.substring(90 + partOfText, 120 + partOfText);
    } else {
      fourthText = "";
    } 
  }
  
  @Override
  public void draw(Batch batch, float alpha){
    batch.draw(backgroundImage, xPosition - backgroundImage.getRegionWidth() / 2
                                , yPosition - backgroundImage.getRegionHeight() / 2);
    
    if(drawStats){
      float cx = getStage().getCamera().position.x - 320;
      float cy = getStage().getCamera().position.y- 240;
      
      batch.end();
      renderer.begin(ShapeRenderer.ShapeType.Filled);
      renderer.setTransformMatrix(batch.getTransformMatrix());
      renderer.setProjectionMatrix(batch.getProjectionMatrix());
      renderer.setColor(Color.BLACK);
      renderer.rect(cx + 400, cy + 100, 220, 300);
      renderer.end();
      batch.begin();
      
      //batch.draw(base,cx + 400,cy + 400);
      base.draw(batch,cx+400,cy+100,220,300);
      
      font.draw(batch,friend.getName().substring(0,1) + friend.getName().substring(1).toLowerCase(),cx + 500,cy + 380);
      font.draw(batch,"Level 5",cx + 420,cy + 380);
      font.draw(batch,"HP",cx + 420,cy + 320);
      font.draw(batch,"Attack",cx + 420,cy + 300);
      font.draw(batch,"Defense",cx + 420,cy + 280);
      font.draw(batch,"Sp. Attack",cx + 420,cy + 260);
      font.draw(batch,"Sp. Defense",cx + 420,cy + 240);
      font.draw(batch,"Speed",cx + 420,cy + 220);
      font.draw(batch,"" + Formulas.getHpStatWithIV(friend.getMaxHealth(), 5, friend.getIVHp()),cx + 550,cy + 320);
      font.draw(batch,"" + Formulas.getOtherStatWithIV(friend.getAttack(), 5, friend.getIVAttack()),cx + 550,cy + 300);
      font.draw(batch,"" + Formulas.getOtherStatWithIV(friend.getDefense(), 5, friend.getIVDefense()),cx + 550,cy + 280);
      font.draw(batch,"" + Formulas.getOtherStatWithIV(friend.getSpecialAttack(), 5, friend.getIVSpecialAttack()),cx + 550,cy + 260);
      font.draw(batch,"" + Formulas.getOtherStatWithIV(friend.getSpecialDefense(), 5, friend.getIVSpecialDefense()),cx + 550,cy + 240);
      font.draw(batch,"" + Formulas.getOtherStatWithIV(friend.getSpeed(), 5, friend.getIVSpeed()),cx + 550,cy + 220);
      
      batch.draw(typeImage, cx + 420
          , cy + 340 );
      
      
      font.draw(batch,firstText,cx+420,cy+190);
      font.draw(batch,secondText,cx+420,cy+170);
      font.draw(batch,thirdText,cx+420,cy+150);
      font.draw(batch,fourthText,cx+420,cy+130);
    }
  }
}
