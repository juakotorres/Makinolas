package cl.makinolas.atk.actors.items;

import cl.makinolas.atk.actors.Hero;
import cl.makinolas.atk.actors.fx.FxManager;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class OranBerry extends Item {

    private TextureRegion reg = Item.textures[2][2];

    @Override
    public String getDescr() {
        return "Recovers 10HP";
    }

    @Override
    public void use(Hero hero) {
    	hero.Getmplayer().playpotion();
        hero.getFriend().setHealth(hero.getHealth()+10);
        FxManager.getInstance().addFx(FxManager.Fx.GREENFX,hero.getStageX(),hero.getStageY());
    }

    @Override
    public TextureRegion getImage() {
        return reg;
    }

}
