package cl.makinolas.atk.actors.platform;

import cl.makinolas.atk.utils.Pair;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class PlatformResource {

    private static TextureRegion[][] platformTiles = new TextureRegion(new Texture(Gdx.files.internal("Background/platforms2.png"))).split(36,36);
    private static HashMap<String,Pair<Integer,Integer>> textCodes = new HashMap<String, Pair<Integer,Integer>>();
    private static PlatformResource instance = new PlatformResource();

    private PlatformResource(){
        textCodes.put("CU", Pair.fromInt(7,7));
        textCodes.put("BC", Pair.fromInt(3,4));
    }

    public static PlatformResource getInstance() {
        return instance;
    }

    public TextureRegion getRegionWithCode(String code){
        Pair<Integer,Integer> coords = textCodes.get(code);
        return platformTiles[coords.getFirst()][coords.getSecond()];
    }

}
