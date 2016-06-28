package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Potion extends Item {

    private TextureRegion reg = Item.textures[1][0];

    @Override
    public String getDescr() {
        return "Recovers 30HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setVariables(hero.getHealth()+30);
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
