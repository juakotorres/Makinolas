package cl.makinolas.atk.start;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class TextActor extends Actor {

  private BitmapFont font;
  private Sprite base;
  private Sprite basefliped;
  private Sprite basedown;
  private Sprite baseleft;
  private ShapeRenderer renderer;
  private String holeText;
  private String firstText;
  private String secondText;
  private String thirdText;
  private int count;
  private boolean isFinished;
  private final float textTime = 1/4f;
  private float accumulator;
  
  
  public TextActor(String paragraph) {
    // 60 caracteres cada linea
    
    this.holeText = paragraph;
    
    count = 0;
    isFinished = false;
    
    setTexts(paragraph, 0);
    
    base = new Sprite(new Texture(Gdx.files.internal("Overlays/superdupertextarea2.png")));
    basefliped = new Sprite(new Texture(Gdx.files.internal("Overlays/superdupertextarea2fliped.png")));
    basedown = new Sprite(new Texture(Gdx.files.internal("Overlays/superdupertextarea2down.png")));
    baseleft = new Sprite(new Texture(Gdx.files.internal("Overlays/superdupertextarea2left.png")));
    font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
    renderer = new ShapeRenderer();
  }
  
  private void setTexts(String paragraph, int i) {
    int partOfText = i * 270;
    if(paragraph.length() < 90 + partOfText){
      firstText = paragraph.substring(0 + partOfText);
    } else {
      firstText = paragraph.substring(0 + partOfText, 90 + partOfText);
    }
    
    if(paragraph.length() < 180 + partOfText && paragraph.length() > 90 + partOfText){
      secondText = paragraph.substring(90 + partOfText);
    } else if (paragraph.length() > 180 + partOfText) {
      secondText =  paragraph.substring(90 + partOfText, 180 + partOfText);
    } else {
      secondText = "";
    }
    
    if(paragraph.length() < 270 + partOfText && paragraph.length() > 180 + partOfText){
      thirdText = paragraph.substring(180 + partOfText);
    } else if (paragraph.length() > 270 + partOfText) {
      thirdText =  paragraph.substring(180 + partOfText, 270 + partOfText);
    } else {
      thirdText = "";
    } 
  }
  
  @Override
  public void act(float delta){
    accumulator += delta;
    
    if(accumulator <= textTime){
      return;
    }
    
    if(count >=  holeText.length() / 270){
      isFinished = true;
    }
    
    if(Gdx.input.isKeyPressed(Keys.Z)){
      count += 1;
      accumulator = 0;
      if(count <= holeText.length() / 270){
        setTexts(holeText, count);
      }
    }
  }
  
  public boolean hasFinished(){
    return isFinished;
  }

  @Override
  public void draw(Batch batch, float alpha) {
    float cx = getStage().getCamera().position.x - 320;
    float cy = getStage().getCamera().position.y- 240;
    
    batch.end();
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    renderer.setColor(Color.BLACK);
    renderer.rect(cx, cy, 640, 120);
    renderer.end();
    batch.begin();
    
    batch.draw(base,cx,cy + 120);
    batch.draw(base,cx + base.getWidth(),cy + 120);
    batch.draw(base,cx + 1.9f*base.getWidth(),cy + 120);
    batch.draw(basedown,cx,cy);
    batch.draw(basedown,cx + base.getWidth(),cy);
    batch.draw(basedown,cx + 1.9f*base.getWidth(),cy);
    batch.draw(basefliped,cx,cy);
    batch.draw(baseleft,cx + 635,cy);
    
    font.draw(batch,firstText,cx+18,cy+100);
    font.draw(batch,secondText,cx+18,cy+70);
    font.draw(batch,thirdText,cx+18,cy+40);

  }
}
