package cl.makinolas.atk.actors.platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public class PlatformResource {

    private static TextureAtlas platAtlas = new TextureAtlas(Gdx.files.internal("Background/Platforms.atlas"));
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
        textCodes.put("BL",platAtlas.createSprite("LineBox"));
        textCodes.put("SSL",platAtlas.createSprite("SnowSimpleLeftCorner"));
        textCodes.put("CRR",platAtlas.createSprite("ClassicRoundedRightCorner"));
        textCodes.put("BC",platAtlas.createSprite("CrossBox"));
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
        textCodes.put("P",platAtlas.createSprite("ClassicUp")); //legacy
        textCodes.put("ZFL",platAtlas.createSprite("Flame"));
        textCodes.put("SAS",platAtlas.createSprite("SnowArrowSign.png"));
        textCodes.put("SCS",platAtlas.createSprite("SnowCrossSign.png"));
        textCodes.put("SNM",platAtlas.createSprite("SnowMan.png"));
        textCodes.put("SPT",platAtlas.createSprite("SnowPineTree.png"));
        textCodes.put("SQS",platAtlas.createSprite("SnowQuestionSign.png"));
        textCodes.put("SST",platAtlas.createSprite("SnowSquareTree.png"));
        textCodes.put("SSs",platAtlas.createSprite("SnowStairs.png"));
        textCodes.put("Tb1",platAtlas.createSprite("Tubo1.png"));
        textCodes.put("vl1",platAtlas.createSprite("valla1.png"));
        textCodes.put("vl2",platAtlas.createSprite("valla2.png"));
        textCodes.put("vl3",platAtlas.createSprite("valla3.png"));
        textCodes.put("AuT",platAtlas.createSprite("AutumnTree.png"));
        textCodes.put("BCc",platAtlas.createSprite("BigCactus.png"));
        textCodes.put("BST",platAtlas.createSprite("BonsaiSakuraTree.png"));
        textCodes.put("CoT",platAtlas.createSprite("CoconutTree.png"));
        textCodes.put("CuB",platAtlas.createSprite("CuteBush.png"));
        textCodes.put("CuT",platAtlas.createSprite("CuteTree.png"));
        textCodes.put("NST",platAtlas.createSprite("NormalSakuraTree.png"));
        textCodes.put("PiT",platAtlas.createSprite("PineTree.png"));
        textCodes.put("SmT",platAtlas.createSprite("SmallTree.png"));
        textCodes.put("SpT",platAtlas.createSprite("SpringTree.png"));
        textCodes.put("tr1",platAtlas.createSprite("tree-1.png"));
        textCodes.put("tr2",platAtlas.createSprite("tree-2.png"));
        textCodes.put("tro",platAtlas.createSprite("tree-ornament.png"));
        textCodes.put("WT",platAtlas.createSprite("Water"));
    }

    public static PlatformResource getInstance() {
        return instance;
    }

    public TextureRegion getRegionWithCode(String code){
        //System.out.println(code);
        //System.out.println(textCodes.get(code));
        return textCodes.get(code);
    }

}
