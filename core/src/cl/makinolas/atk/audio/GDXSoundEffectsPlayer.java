package cl.makinolas.atk.audio;

import java.util.HashMap;

import com.badlogic.gdx.audio.Music;

public class GDXSoundEffectsPlayer {
	private static GDXSoundEffectsPlayer instance;
	protected static HashMap<String, Music> sfxmap;

	protected GDXSoundEffectsPlayer() {
		sfxmap = new HashMap<String, Music>();
	}
	
	public static GDXSoundEffectsPlayer getInstance() {
		if (instance == null)
			instance = new GDXSoundEffectsPlayer();
		return instance;
	}
	
	public static HashMap<String, Music> getSFXMap() {
		return sfxmap;
	}

	public void SetVolume(float f) {
		for (String s : sfxmap.keySet()) {
			Music m = sfxmap.get(s);
			m.setVolume(f);
		}
	}
	
	
	//Menu
	public void PlayPauseMenuIn(){
		sfxmap.get("soundPauseMenuIn").play();
	}
	public void PlayPauseMenuOut(){
		sfxmap.get("soundPauseMenuOut").play();
	}
	
	public void PlayPressButton(){
		sfxmap.get("soundPressButton").play();
	}
	public void PlayMoveMenu(){
		sfxmap.get("soundMoveMenu").play();
	}
	public void PlayGetDmg(){
		sfxmap.get("soundGetDmg").play();
	}
	// Hero
	
	public void PlayJumpSound(){
		sfxmap.get("soundJump").play();
	}

	public void PlayProyectileSound(){
		sfxmap.get("soundProyectile").play();
	}
	
	public void StopProyectileSound(){
		sfxmap.get("soundProyectile").stop();
	}
	
	public void PlayEnd(){
		sfxmap.get("soundEndStage").play();
	}
	
	public void PlayClaw(){
		if (sfxmap.get("soundClaw").isPlaying())
			sfxmap.get("soundClaw").stop();
		sfxmap.get("soundClaw").play();
	}
	
	public void playpotion(){
		if (sfxmap.get("soundPotion").isPlaying())
			sfxmap.get("soundPotion").stop();
		sfxmap.get("soundPotion").play();
	}
	
	public void playthrow(){
		if (sfxmap.get("soundThrow").isPlaying())
			sfxmap.get("soundThrow").stop();
		sfxmap.get("soundThrow").play();
	}
	
	public void playcaptured(){
		if (sfxmap.get("soundCaptured").isPlaying())
			sfxmap.get("soundCaptured").stop();
		sfxmap.get("soundCaptured").play();
	}

	public void playnotcaptured(){
		if (sfxmap.get("soundnotCaptured").isPlaying())
			sfxmap.get("soundnotCaptured").stop();
		sfxmap.get("soundnotCaptured").play();
	}
	
	// Enemy
	public void PlayExplotion(){
		sfxmap.get("soundExplotion").play();
	}
	
	public void PlayExplotionEnd(){
		sfxmap.get("soundExplotionEnd").play();
	}

}
