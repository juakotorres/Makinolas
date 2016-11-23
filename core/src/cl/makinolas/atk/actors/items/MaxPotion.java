package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.fx.FxManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MaxPotion extends Item {

    private TextureRegion reg = Item.textures[1][3];

    @Override
    public String getDescr() {
        return "Recovers all HP from the current pokemon";
    }

    @Override
    public void use(Hero hero) {
    	hero.Getmplayer().playpotion();
        hero.getFriend().setHealth(hero.getFriend().getMaxHealth());
        FxManager.getInstance().addFx(FxManager.Fx.GREENFX,hero.getStageX(),hero.getStageY());
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
