package cl.makinolas.atk.actors.ui;

import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.stages.GameStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainBar extends Actor{

    private Hero hero;
    private HBar healthBar, magicBar;
    private Sprite base;
    private BitmapFont font;
    private ShapeRenderer renderer;

    public MainBar(Hero h){
        hero = h;
        healthBar = new HBar(100,100,150,10,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
        magicBar = new HBar(100,100,150,10,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_blue.png"))));;
        base = new Sprite(new Texture(Gdx.files.internal("Overlays/bar_base.png")));
        base.setRegionWidth(640); //Full width
        font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
        setZIndex(1000);
        renderer = new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //Camara
        float cx = getStage().getCamera().position.x - 320;
        float cy = getStage().getCamera().position.y - 240;
        //Bars
        healthBar.setCurrent(hero.getHealth());
        batch.draw(base,cx,cy);
        batch.draw(healthBar.getSprite(),cx+50,cy+26);
        batch.draw(magicBar.getSprite(),cx+50,cy+8);
        //Labels
        font.draw(batch,"HP",cx+18,cy+36);
        font.draw(batch,"Magic",cx+4,cy+20);
        font.draw(batch,GameStage.levelName,cx+220,cy+38);
        font.draw(batch, ""+((int) GameStage.elapsedTime),cx+230,cy+20);
        //Rectangles (should be always at final)
        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());
        renderer.setTransformMatrix(batch.getTransformMatrix());
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.BLACK);
        renderer.rect(cx+50, cy+26, 150, 10);
        renderer.rect(cx+50, cy+8, 150, 10);
        renderer.end();
        batch.begin();
    }
}
