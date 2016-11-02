package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GDXSoundEffectsHero {
	private Music soundJump=Gdx.audio.newMusic(Gdx.files.internal("Music/jump_02.wav"));
	private Music soundProyectile=Gdx.audio.newMusic(Gdx.files.internal("Music/laser4.wav"));
	private Music soundEndStage=Gdx.audio.newMusic(Gdx.files.internal("Music/FX098.wav"));
	private Music soundClaw=Gdx.audio.newMusic(Gdx.files.internal("Music/Swoosh 3-SoundBible.com-1573211927.mp3"));
	public GDXSoundEffectsHero(){
	}
	public void PlayJumpSound(){ 
		soundJump.play();
	}

	public void PlayProyectileSound(){
		soundProyectile.play();		
	}
	public void PlayEnd(){
		soundEndStage.play();
	}
	public void PlayClaw(){
		if(soundClaw.isPlaying()){
	    soundClaw.stop();
		soundClaw.play();
		}
		else{
		soundClaw.play();
		}
	}
}
