package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;

import cl.makinolas.atk.stages.OptionsStage;

public class GDXSoundEffectsEnemy extends GDXSoundEffectsPlayer {
	
	public static GDXSoundEffectsPlayer getInstance() {
		GDXSoundEffectsPlayer myInstance = GDXSoundEffectsPlayer.getInstance();
		
		sfxmap.putIfAbsent("soundExplotion", Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_01.wav")));
		sfxmap.putIfAbsent("soundExplotionEnd", Gdx.audio.newMusic(Gdx.files.internal("Music/SFX_Explosion_03.wav")));
		
		myInstance.SetVolume(OptionsStage.getSFXVolume());
		return myInstance;
	}
	
}
