package cl.makinolas.atk.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GDXSoundEffectsHero {
	
	private Music soundJump=Gdx.audio.newMusic(Gdx.files.internal("Music/jump_02.wav"));
	private Music soundProyectile=Gdx.audio.newMusic(Gdx.files.internal("Music/laser4.wav"));
	private Music soundEndStage=Gdx.audio.newMusic(Gdx.files.internal("Music/FX098.wav"));
	private Music soundClaw=Gdx.audio.newMusic(Gdx.files.internal("Music/Swoosh 3-SoundBible.com-1573211927.mp3"));
	private Music soundPotion=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_potion.wav"));
	private Music soundThrow=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_throw.wav"));
	private Music soundCaptured=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_captured.wav"));
	private Music soundnotCaptured=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_notcaptured.wav"));
	private Music soundgetDmg=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_00F1.wav"));
	private Music soundmovemenu=Gdx.audio.newMusic(Gdx.files.internal("Music/firered_000A.wav")); 
	
	public GDXSoundEffectsHero(){
	}
	
	public void PlayJumpSound(){ 
		soundJump.play();
	}

	public void PlayProyectileSound(){
		soundProyectile.play();		
	}
	public void StopProyectileSound(){
		soundProyectile.stop();		
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
	public void playpotion(){
		if(soundPotion.isPlaying()){
			soundPotion.stop();
			soundPotion.play();
			}
			else{
			soundPotion.play();
			}
	}
	public void playthrow(){
		if(soundThrow.isPlaying()){
			soundThrow.stop();
			soundThrow.play();
			}
			else{
				soundThrow.play();
			}
	}
	public void playcaptured(){
		if(soundCaptured.isPlaying()){
			soundCaptured.stop();
			soundCaptured.play();
			}
			else{
				soundCaptured.play();
			}
	}

	public void playnotcaptured(){
		if(soundnotCaptured.isPlaying()){
		soundnotCaptured.stop();
		soundnotCaptured.play();
		}
		else{
			soundnotCaptured.play();
		}
	}

	public void playgetDmg() {
		if(soundgetDmg.isPlaying()){
			soundgetDmg.stop();
			soundgetDmg.play();
		}
		else{
			soundgetDmg.play();
		}
		
	}
	public void playmovemenu() {
		if(soundmovemenu.isPlaying()){
			soundmovemenu.stop();
			soundmovemenu.play();
		}
		else{
			soundmovemenu.play();
		}
		
	}

}
