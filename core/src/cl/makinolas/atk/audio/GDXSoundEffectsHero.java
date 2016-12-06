package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;

import cl.makinolas.atk.stages.OptionsStage;



public class GDXSoundEffectsHero extends GDXSoundEffectsPlayer {
	
	public static GDXSoundEffectsPlayer getInstance() {
		
	
		GDXSoundEffectsPlayer myInstance = GDXSoundEffectsPlayer.getInstance();
		sfxmap.putIfAbsent("soundPauseMenuOut",Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00FB.wav")));
		sfxmap.putIfAbsent("soundPauseMenuIn",Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00FA.wav")));
		sfxmap.putIfAbsent("soundPressButton",Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00F6.wav")));
		sfxmap.putIfAbsent("soundMoveMenu", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_000A.wav")));
		sfxmap.putIfAbsent("soundGetDmg", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00F1.wav")));
		sfxmap.putIfAbsent("soundJump", Gdx.audio.newMusic(Gdx.files.internal("Music/jump_02.wav")));
		sfxmap.putIfAbsent("soundProyectile", Gdx.audio.newMusic(Gdx.files.internal("Music/laser4.wav")));
		sfxmap.putIfAbsent("soundEndStage", Gdx.audio.newMusic(Gdx.files.internal("Music/FX098.wav")));
		sfxmap.putIfAbsent("soundClaw", Gdx.audio.newMusic(Gdx.files.internal("Music/Swoosh 3-SoundBible.com-1573211927.mp3")));
		sfxmap.putIfAbsent("soundPotion", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_potion.wav")));
		sfxmap.putIfAbsent("soundThrow", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_throw.wav")));
		sfxmap.putIfAbsent("soundCaptured", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_captured.wav")));
		sfxmap.putIfAbsent("soundnotCaptured", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_notcaptured.wav")));
		
		myInstance.SetVolume(OptionsStage.getSFXVolume());
		return myInstance;

	}	

	
}
