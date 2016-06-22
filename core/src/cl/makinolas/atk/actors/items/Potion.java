package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Potion extends Item {

    @Override
    public String getDescr() {
        return "Recovers 20HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
        //hero.recover(20);
    }

    @Override
    public TextureRegion getImage() {
        return (new TextureRegion(new Texture(Gdx.files.internal("Overlays/items")))).split(32,32)[1][0];
    }

}
