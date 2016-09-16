package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.fx.FxManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Potion extends Item {

    private TextureRegion reg = Item.textures[1][0];

    @Override
    public String getDescr() {
        return "Recovers 30HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
        hero.getFriend().setHealth(hero.getHealth()+30);
        FxManager.getInstance().addFx(FxManager.Fx.GREENFX,hero.getStageX(),hero.getStageY());
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
