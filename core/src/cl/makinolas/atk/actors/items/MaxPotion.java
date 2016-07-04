package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MaxPotion extends Item {

    private TextureRegion reg = Item.textures[1][3];

    @Override
    public String getDescr() {
        return "Recovers all HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setHealth(hero.getFriend().getMaxHealth());
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
