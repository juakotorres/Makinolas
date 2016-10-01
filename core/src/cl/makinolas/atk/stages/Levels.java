package cl.makinolas.atk.stages;

public enum Levels {
  LEVEL1("ezpizi", false, 28, 20, "Background/SuPuente.jpg", "Music/Sanctuary.mp3"),
  FIRSTSTAGE("FirstStage", false, 28, 18, "Background/SandOcean.png", "Music/Sanctuary.mp3"),
  LEVEL2("Electric_Campament", false, 28, 16, "Background/AbandonedMansion.jpg", "Music/Sanctuary.mp3"),
  LEVEL3("UpAndDown", false, 26, 16, "Background/Night.png", "Music/Freesia.mp3"),
  BOSSLEVEL1("bossstage1", true, 24, 16, "Background/SuPuente.jpg", "Music/Never-Gonna-Give-You-Up.mp3"),
  LEVEL4("DownLevel", false, 24,18, "Background/OldRuins1.1.png", "Music/Sanctuary.mp3"),
  LEVEL5("cuito_maincra", false, 24,14, "Background/SandOcean2.1.png", "Music/Sanctuary.mp3");
  
  public String levelName;
  public boolean bossLevel;
  public int mapx, mapy; // x from 0 to 31, y from 0 to 24
  public String levelBackground;
  public String levelMusic;
  Levels(String name, boolean boss, int mx, int my, String background, String music){
    levelName = name;
    bossLevel = boss;
    mapx = mx;
    mapy = my;
    levelBackground = background;
    levelMusic = music;
  }
}
