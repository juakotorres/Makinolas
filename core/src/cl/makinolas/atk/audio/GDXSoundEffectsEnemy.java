package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;

import cl.makinolas.atk.stages.OptionsStage;

public class GDXSoundEffectsEnemy extends GDXSoundEffectsPlayer {
	
	GDXSoundEffectsEnemy() {
		sfxmap.put("soundExplotion", Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_01.wav")));
		sfxmap.put("soundExplotionEnd", Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_03.wav")));
	}
	
	public static GDXSoundEffectsPlayer getInstance() {
		instance = new GDXSoundEffectsEnemy();
		instance.SetVolume(OptionsStage.getSFXVolume());
		return instance;
	}
}
