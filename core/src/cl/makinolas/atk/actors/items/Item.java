package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {

    public static TextureRegion[][] textures = new TextureRegion(new Texture(Gdx.files.internal("Overlays/items.png"))).split(32,32);

    public String getName(){
        return this.getClass().getName();
    }

    public abstract String getDescr();

    public abstract void use(Hero hero);

    public abstract TextureRegion getImage();
}
