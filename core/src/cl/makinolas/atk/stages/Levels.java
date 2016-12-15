package cl.makinolas.atk.stages;

import cl.makinolas.atk.climate.*;

public enum Levels {
  LEVEL1("ezpizi", // Nombre del .lvl -- reemplazar por "test_displacement_bug" para probar bug de desplazamiento
          false,   // true si es bossStage
          new NormalClimate(), //clima 
          28,      // posición x del mapa
          20,      // posicion y del mapa
          "Background/SuPuente.jpg",  // imagen de fondo
          "Music/Sanctuary.mp3" ,     // musica de la etapa
          new Spot(28,20 , new NullSpot(), new NullSpot(), new NullSpot(), new NullSpot()), // etapas de alrededor (recordar agregar etapas de alrededor)
          1,
          8), // etapas que se desboquean al completar esta
  FIRSTSTAGE("FirstStage",
          false,
          new NormalClimate(),
          28,
          18,
          "Background/SandOcean.png",
          "Music/Sanctuary.mp3",
          new Spot(28,18 , LEVEL1.levelSpot, new NullSpot(), new NullSpot(), new NullSpot()),
          2),
  LEVEL2("Electric_Campament",
          false,
          new NormalClimate(),
          28,
          16,
          "Background/AbandonedMansion.jpg",
          "Music/Sanctuary.mp3",
          new Spot(28, 16, FIRSTSTAGE.levelSpot, new NullSpot(), new NullSpot(), new NullSpot()),
          3),
  LEVEL3("UpAndDown",
          false,
          new NormalClimate(),
          26,
          16,
          "Background/Night.png",
          "Music/Freesia.mp3",
          new Spot(26, 16, new NullSpot(), new NullSpot(), LEVEL2.levelSpot, new NullSpot()),
          4),
  BOSSLEVEL1("bossstage1",
          true,
          new NormalClimate(),
          24,
          16,
          "Background/SuPuente.jpg",
          "Music/Never-Gonna-Give-You-Up.mp3",
          new Spot(24, 16, new NullSpot(), new NullSpot(), LEVEL3.levelSpot, new NullSpot()),
         5, // caso en que se desbloquean más de una etapa
         6),
  LEVEL4("DownLevel",
          false,
          new NormalClimate(),
          24,
          18,
          "Background/OldRuins1.1.png",
          "Music/Sanctuary.mp3",
          new Spot(24, 18, new NullSpot(), BOSSLEVEL1.levelSpot, new NullSpot(), new NullSpot()),
         7),
  LEVEL5("cuito_maincra",
          false,
          new NormalClimate(),
          24,
          14,
          "Background/SandOcean2.1.png",
          "Music/Sanctuary.mp3",
          new Spot(24, 14,  BOSSLEVEL1.levelSpot, new NullSpot(), new NullSpot(), new NullSpot())),
  LEVEL6("Fibonacci_confirmed",
          false,
          new NormalClimate(),
          22,
          18,
          "Background/OldRuins1.1.png",
          "Music/06-first-battle.mp3",
          new Spot(22, 18,  new NullSpot(), new NullSpot(), LEVEL4.levelSpot, new NullSpot())),
  LEVEL8("level2",
      false,
      new NormalClimate(),
      26,
      20,
      "Background/Pradera.png",
      "Music/Freesia.mp3",
      new Spot(26, 20,  new NullSpot(), new NullSpot(), LEVEL1.levelSpot, new NullSpot()),
      9),
  LEVEL9("level3",
      false,
      new NormalClimate(),
      26,
      22,
      "Background/SuPuente.jpg",
      "Music/Freesia.mp3",
      new Spot(26, 22,  new NullSpot(), LEVEL8.levelSpot, new NullSpot(), new NullSpot()));



  public String levelName;
  public boolean bossLevel;
  public int mapx, mapy; // x from 0 to 31, y from 0 to 24
  public String levelBackground;
  public String levelMusic;
  public Spot levelSpot;
  public int[] unlockableLevels;
  public IClimate climate;
  Levels(String name, boolean boss, IClimate weather, int mx, int my, String background, String music, Spot levelSpot, int... unlockableLevels){
    levelName = name;
    bossLevel = boss;
    climate = weather;
    mapx = mx;
    mapy = my;
    levelBackground = background;
    levelMusic = music;
    this.levelSpot = levelSpot;
    this.levelSpot.setLevel(this);
    this.unlockableLevels = unlockableLevels;
  }
}
