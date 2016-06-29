package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Potion extends Item {

    private TextureRegion reg = (new TextureRegion(new Texture(Gdx.files.internal("Overlays/items.png")))).split(32,32)[1][0];

    @Override
    public String getDescr() {
        return "Recovers 30HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setHealth(hero.getHealth()+30);
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
