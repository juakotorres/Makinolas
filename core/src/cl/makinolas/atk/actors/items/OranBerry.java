package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class OranBerry extends Item {

    @Override
    public String getDescr() {
        return "Recovers 10HP";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setVariables(hero.getHealth()+10,hero.getFriend().getDead());
    }

    @Override
    public TextureRegion getImage() {
        return (new TextureRegion(new Texture(Gdx.files.internal("Overlays/items")))).split(32,32)[2][2];
    }

}
