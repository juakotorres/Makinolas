package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;

import cl.makinolas.atk.stages.OptionsStage;

public class GDXSoundEffectsHero extends GDXSoundEffectsPlayer {
	
	GDXSoundEffectsHero() {
		sfxmap.put("soundJump", Gdx.audio.newMusic(Gdx.files.internal("Music/jump_02.wav")));
		sfxmap.put("soundProyectile", Gdx.audio.newMusic(Gdx.files.internal("Music/laser4.wav")));
		sfxmap.put("soundEndStage", Gdx.audio.newMusic(Gdx.files.internal("Music/FX098.wav")));
		sfxmap.put("soundClaw", Gdx.audio.newMusic(Gdx.files.internal("Music/Swoosh 3-SoundBible.com-1573211927.mp3")));
		sfxmap.put("soundPotion", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_potion.wav")));
		sfxmap.put("soundThrow", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_throw.wav")));
		sfxmap.put("soundCaptured", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_captured.wav")));
		sfxmap.put("soundnotCaptured", Gdx.audio.newMusic(Gdx.files.internal("Music/firered_notcaptured.wav")));
	}
	
	public static GDXSoundEffectsPlayer getInstance() {
		instance = new GDXSoundEffectsHero();
		instance.SetVolume(OptionsStage.getSFXVolume());
		return instance;
	}

}
