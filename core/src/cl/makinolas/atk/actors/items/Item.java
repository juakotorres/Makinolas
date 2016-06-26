package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Item {

    public String getName(){
        return this.getClass().getName();
    }

    public abstract String getDescr();

    public abstract void use(Hero hero);

    public abstract TextureRegion getImage();
}
