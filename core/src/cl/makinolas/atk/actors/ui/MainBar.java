package cl.makinolas.atk.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import cl.makinolas.atk.actors.HBar;
import cl.makinolas.atk.actors.Hero;

public class MainBar extends Group{

    public static MainBar barra = new MainBar();
    private Hero hero;
    private HBar healthBar, magicBar;
    private FriendImage friend;
    private Array<TeamFriendImage> team;
    private Sprite base;
    private BitmapFont font;
    private ShapeRenderer renderer;
    private ImageCuad itemA, itemS;

    private MainBar(){
        hero = Hero.getInstance();
        setBars();
        base = new Sprite(new Texture(Gdx.files.internal("Overlays/bar_base.png")));
        base.setRegionWidth(640); //Full width
        font = new BitmapFont(Gdx.files.internal("Fonts/normal.fnt"),Gdx.files.internal("Fonts/normal.png"),false);
        renderer = new ShapeRenderer();
        friend = new FriendImage();
        itemA = new ImageCuad(hero.getInventory().getSelItem1().getItem().getImage(),"A",font);
        itemS = new ImageCuad(hero.getInventory().getSelItem2().getItem().getImage(),"S",font);
        //updateTeam();
    }

    public void updateTeam() {
        team = new Array<TeamFriendImage>();
        for (int i = 0; i < hero.getAllies().size; i++) {
            team.add(new TeamFriendImage(hero.getAllies().get(i)));
        }
    }

    public static MainBar getInstance(){
      return barra;
    }
    
    public void reset(){
      barra = new MainBar();
    }

    public void setBars() {
      Hero h = Hero.getInstance();
      healthBar = new HBar(h.getFriend().getMaxHealth(),h.getHealth(),150,10,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_green.png"))));
      magicBar = new HBar(h.getFriend().getMaxMagic(),h.getMagic(),150,10,new TextureRegion(new Texture(Gdx.files.internal("Overlays/bar_blue.png"))));;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        //Camara
        float cx = getStage().getCamera().position.x - 320;
        float cy = getStage().getCamera().position.y- 240;
        //Bars
        healthBar.setCurrent(hero.getHealth());
        magicBar.setCurrent(hero.getMagic());
        batch.draw(base,cx,cy);
        batch.draw(healthBar.getSprite(),cx+50,cy+26);
        batch.draw(magicBar.getSprite(),cx+50,cy+8);
        //Labels
        font.draw(batch,"HP",cx+18,cy+36);
        font.draw(batch,"Magic",cx+4,cy+20);
        font.draw(batch,String.valueOf(Hero.getInstance().getHealth()),cx+100, cy+36); 
        font.draw(batch, "/", cx+120, cy+36);
        font.draw(batch,String.valueOf(Hero.getInstance().getFriend().getMaxHealth()),cx+130, cy+36);
        //font.draw(batch,AbstractStage.levelName ,cx+220,cy+38);
        //font.draw(batch, ""+((int) AbstractStage.elapsedTime),cx+230,cy+20);
        font.draw(batch, "$"+Hero.getInstance().getInventory().getMoney(),cx+220,cy+20);
        //Current Friend Sprite
        friend.setPosition(cx+270,cy+2);
        friend.setTexture(hero.getFriend().getFriendFaceSprite());
        friend.draw(batch, 1);
        //Team Friend Sprite
        if(team == null) updateTeam();
        for (int i = 1; i < team.size; i++) {
            TeamFriendImage friendImage =  team.get((i+hero.getIndexFriend())%team.size);
            friendImage.setPosition(cx+320+32*(i-1),cy);
            friendImage.draw(batch,1);
        }
        //ImageCuads
        itemA.setPosition(cx+540,cy+6);
        itemA.setRegion(hero.getInventory().getSelItem1().getItem().getImage());
        itemA.setRightLabel(""+hero.getInventory().getSelItem1().getQuantity());
        itemA.draw(batch,1);
        itemS.setPosition(cx+580,cy+6);
        itemS.setRegion(hero.getInventory().getSelItem2().getItem().getImage());
        itemS.setRightLabel(""+hero.getInventory().getSelItem2().getQuantity());
        itemS.draw(batch,1);
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
