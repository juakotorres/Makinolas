package cl.makinolas.atk.actors.friend;

public enum Enemies {
  CHARMANDER(62, 45, 39, 52, 43, 60, 50, 65),
  CHARMELEON(142, 45, 58, 64, 58, 80, 65, 80),
  CHARIZARD(240, 45, 78, 84, 78, 109, 85, 100),
  CATERPIE(39, 255, 45, 30, 35, 20, 20, 45),
  METAPOD(72, 120, 50, 20, 55, 25, 25, 30),
  BUTTERFREE(173, 45, 60, 45, 50, 90, 80, 70),
  WEEDLE(39, 255, 40, 35, 30, 20, 20, 50),
  KAKUNA(72, 120, 45, 25, 50, 25, 25, 35),
  BEEDRILL(173, 45, 65, 90, 40, 45, 80, 75),
  PIKACHU(105, 190, 35, 55, 40, 50, 50, 90),
  RAICHU(214, 75, 60, 90, 55, 90, 80, 110),
  JIGGLYPUFF(95, 170, 115, 45, 20, 45, 25, 20),
  WIGGLYTUFF(191, 50, 140, 70, 45, 85, 50, 45),
  ZUBAT(49, 255, 40, 45, 35, 30, 40, 55),
  GOLBAT(159, 90, 75, 80, 70, 65, 75, 90),
  MAGNEMITE(65, 190, 25, 35, 70, 95, 55, 45),
  MAGNETON(163, 60, 50, 60, 95, 120, 70, 70),
  GASTLY(62, 190, 30, 35, 30, 100, 35, 80),
  HAUNTER(93, 90, 45, 50, 45, 115, 55, 95),
  GENGAR(225, 45, 60, 65, 60, 130, 75, 110),
  CUBONE(64, 190, 50, 50, 95, 40, 50, 35),
  MAROWAK(149, 75, 60, 80, 110, 50, 80, 45),
  KANGASKHAN(172, 45, 105, 95, 80, 40, 80, 90),
  SCYTHER(100, 45, 70, 110, 80, 55, 80, 105),
  EEVEE(65, 45, 55, 55, 50, 45, 65, 55),
  MEWTWO(306, 3, 106, 110, 90, 154, 90, 130),
  TOTODILE(63, 45, 50, 65, 64, 44, 48, 43),
  CROCONAW(142, 45, 65, 80, 80, 59, 63, 58),
  FERALIGATR(239, 45, 85, 105, 100, 79, 83, 78),
  CROBAT(241, 90, 85, 90, 80, 70, 80, 130),
  PICHU(41, 190, 20, 40, 15, 35, 35, 60),
  IGGLYBUFF(42, 170, 90, 30, 15, 40, 20, 15),
  MAREEP(56, 235, 55, 40, 40, 65, 45, 35),
  FLAAFFY(128, 120, 70, 55, 55, 80, 60, 45),
  AMPHAROS(225, 45, 90, 75, 85, 115, 90, 55),
  SCIZOR(175, 25, 70, 130, 100, 55, 80, 65),
  SURSKIT(54, 200, 40, 30, 32, 50, 52, 65),
  MASQUERAIN(145, 75, 70, 60, 62, 80, 82, 60),
  BAGON(60, 45, 45, 75, 60, 40, 30, 50),
  SHELGON(147, 45, 65, 95, 100, 60, 50, 50),
  SALAMENCE(270, 45, 95, 135, 80, 110, 80, 100),
  BELDUM(60, 3, 40, 55, 80, 35, 60, 30),
  METANG(147, 3, 60, 75, 100, 55, 80, 50),
  METAGROSS(270, 3, 80, 135, 130, 95, 90, 70),
  SHINX(53, 235, 45, 65, 34, 40, 34, 45),
  LUXIO(127, 120, 60, 85, 49, 60, 49, 60),
  LUXRAY(235, 45, 80, 120, 79, 95, 79, 70),
  GIBLE(60, 45, 58, 70, 45, 40, 45, 42),
  GABITE(144, 45, 68, 90, 65, 50, 55, 82),
  GARCHOMP(270, 45, 108, 130, 95, 80, 85, 102),
  MAGNEZONE(241, 30, 70, 70, 115, 130, 90, 60),
  ROTOM(154, 45, 50, 50, 77, 95, 77, 91),
  SNIVY(28, 45, 45, 45, 55, 45, 55, 63),
  SERVINE(145, 45, 60, 60, 75, 60, 75, 83),
  SERPERIOR(238, 45, 75, 75, 95, 75, 95, 113);
  
  public int baseExperience;
  public int catchRate;
  public int hpBase;
  public int attackBase;
  public int defenseBase;
  public int spAttackBase;
  public int spDefenseBase;
  public int speedBase;
  Enemies(int baseExperience, int catchRate, int hpBase, 
          int attackBase,int defenseBase, int spAttackBase,
          int spDefenseBase, int speedBase){
    this.baseExperience = baseExperience;
    this.catchRate = catchRate;
    this.hpBase = hpBase;
    this.attackBase = attackBase;
    this.defenseBase = defenseBase;
    this.spAttackBase = spAttackBase;
    this.spDefenseBase = spDefenseBase;
    this.speedBase = speedBase;
  }
  
}
