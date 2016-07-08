package cl.makinolas.atk.actors.platform;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class PlatformResource {

    private static TextureAtlas platAtlas = new TextureAtlas("Background/Platforms.pack");
    private static HashMap<String,Sprite> textCodes = new HashMap<String, Sprite>();
    private static PlatformResource instance = new PlatformResource();

    private PlatformResource(){
        textCodes.put("CPL",platAtlas.createSprite("ClassicPrettyLeftCorner"));
        textCodes.put("CU",platAtlas.createSprite("ClassicUp"));
        textCodes.put("FPR",platAtlas.createSprite("FreezePrettyRightCorner"));
        textCodes.put("FG",platAtlas.createSprite("FreezeWithoutGrass"));
        textCodes.put("SRL",platAtlas.createSprite("SnowRoundedLeftCorner"));
        textCodes.put("CPR",platAtlas.createSprite("ClassicPrettyRightCorner"));
        textCodes.put("CG",platAtlas.createSprite("ClassicWithoutGrass"));
        textCodes.put("FRL",platAtlas.createSprite("FreezeRoundedLeftCorner"));
        textCodes.put("FGR",platAtlas.createSprite("FreezeWithoutGrassRounded"));
        textCodes.put("SRR",platAtlas.createSprite("SnowRoundedRightCorner"));
        textCodes.put("YB",platAtlas.createSprite("YellowBlock"));
        textCodes.put("CRL",platAtlas.createSprite("ClassicRoundedLeftCorner"));
        textCodes.put("CGR",platAtlas.createSprite("ClassicWithoutGrassRounded"));
        textCodes.put("FRR",platAtlas.createSprite("FreezeRoundedRightCorner"));
        textCodes.put("LB",platAtlas.createSprite("LineBox"));
        textCodes.put("SSL",platAtlas.createSprite("SnowSimpleLeftCorner"));
        textCodes.put("CRR",platAtlas.createSprite("ClassicRoundedRightCorner"));
        textCodes.put("CB",platAtlas.createSprite("CrossBox"));
        textCodes.put("FSL",platAtlas.createSprite("FreezeSimpleLeftCorner"));
        textCodes.put("SSR",platAtlas.createSprite("SnowSimpleRightCorner"));
        textCodes.put("CSL",platAtlas.createSprite("ClassicSimpleLeftCorner"));
        textCodes.put("FL",platAtlas.createSprite("Flame"));
        textCodes.put("FSR",platAtlas.createSprite("FreezeSimpleRightCorner"));
        textCodes.put("SPL",platAtlas.createSprite("SnowPrettyLeftCorner"));
        textCodes.put("SU",platAtlas.createSprite("SnowUp"));
        textCodes.put("CSR",platAtlas.createSprite("ClassicSimpleRightCorner"));
        textCodes.put("FPL",platAtlas.createSprite("FreezePrettyLeftCorner"));
        textCodes.put("FU",platAtlas.createSprite("FreezeUp"));
        textCodes.put("SPR",platAtlas.createSprite("SnowPrettyRightCorner"));
        textCodes.put("SGR",platAtlas.createSprite("SnowWithoutGrassRounded"));
    }

    public static PlatformResource getInstance() {
        return instance;
    }

    public TextureRegion getRegionWithCode(String code){
        return textCodes.get(code);
    }

}
