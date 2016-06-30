package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LeppaBerry extends Item {

    private TextureRegion region = Item.textures[2][1];

    @Override
    public String getDescr() {
        return "Recovers 100 Magic points";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setMagic(hero.getFriend().getMagic()+100);
    }

    @Override
    public TextureRegion getImage() {
        return region;
    }
}
