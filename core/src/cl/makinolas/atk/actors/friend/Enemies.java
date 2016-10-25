package cl.makinolas.atk.actors.friend;

import cl.makinolas.atk.actors.friend.EvStates.*;

public enum Enemies {

  CHARMANDER(62, 45, 39, 52, 43, 60, 50, 65, new EvSpeedState(1)),
  CHARMELEON(142, 45, 58, 64, 58, 80, 65, 80, new EvSpeedState(1), new EvSpAttackState(1)),
  CHARIZARD(240, 45, 78, 84, 78, 109, 85, 100, new EvSpAttackState(3)),
  CATERPIE(39, 255, 45, 30, 35, 20, 20, 45, new EvHpState(1)),
  METAPOD(72, 120, 50, 20, 55, 25, 25, 30, new EvDefenseState(2)),
  BUTTERFREE(173, 45, 60, 45, 50, 90, 80, 70, new EvSpAttackState(2), new EvSpDefenseState(1)),
  WEEDLE(39, 255, 40, 35, 30, 20, 20, 50, new EvSpeedState(1)),
  KAKUNA(72, 120, 45, 25, 50, 25, 25, 35, new EvDefenseState(2)),
  BEEDRILL(173, 45, 65, 90, 40, 45, 80, 75, new EvAttackState(2), new EvSpDefenseState(1)),
  PIKACHU(105, 190, 35, 55, 40, 50, 50, 90, new EvSpeedState(2)),
  RAICHU(214, 75, 60, 90, 55, 90, 80, 110, new EvSpeedState(3)),
  SANDSHREW(60, 255, 50, 75, 85, 20, 30, 40, new EvDefenseState(1)),
  SANDSLASH(158, 90, 75, 100, 110, 45, 55, 65, new EvDefenseState(2)),
  VULPIX(60, 190, 38, 41, 40, 50, 65, 65, new EvSpeedState(1)),
  NINETALES(177, 75, 73, 76, 75, 81, 100, 100, new EvSpDefenseState(1), new EvSpeedState(1)),
  JIGGLYPUFF(95, 170, 115, 45, 20, 45, 25, 20, new EvHpState(2)),
  WIGGLYTUFF(191, 50, 140, 70, 45, 85, 50, 45, new EvHpState(3)),
  ZUBAT(49, 255, 40, 45, 35, 30, 40, 55, new EvSpeedState(1)),
  GOLBAT(159, 90, 75, 80, 70, 65, 75, 90, new EvSpeedState(2)),
  GROWLITHE(70, 190, 55, 70, 45, 70, 50, 60, new EvAttackState(1)),
  ARCANINE(194, 75, 90, 110, 80, 100, 80, 95, new EvAttackState(2)),
  GEODUDE(60, 255, 40, 80, 100, 30, 30, 20, new EvDefenseState(1)),
  GRAVELER(137, 120, 55, 95, 115, 45, 45, 35, new EvDefenseState(2)),
  GOLEM(218, 45, 80, 120, 130, 55, 65, 45, new EvDefenseState(3)),
  MAGNEMITE(65, 190, 25, 35, 70, 95, 55, 45, new EvSpAttackState(1)),
  MAGNETON(163, 60, 50, 60, 95, 120, 70, 70, new EvSpAttackState(2)),
  GASTLY(62, 190, 30, 35, 30, 100, 35, 80, new EvSpAttackState(1)),
  HAUNTER(93, 90, 45, 50, 45, 115, 55, 95, new EvSpAttackState(2)),
  GENGAR(225, 45, 60, 65, 60, 130, 75, 110, new EvSpAttackState(3)),
  CUBONE(64, 190, 50, 50, 95, 40, 50, 35, new EvDefenseState(1)),
  MAROWAK(149, 75, 60, 80, 110, 50, 80, 45, new EvDefenseState(2)),
  KANGASKHAN(172, 45, 105, 95, 80, 40, 80, 90, new EvHpState(2)),
  SCYTHER(100, 45, 70, 110, 80, 55, 80, 105, new EvAttackState(1)),
  MAGIKARP(40, 255, 20, 10, 55, 15, 20, 80, new EvSpeedState(1)),
  GYARADOS(189, 45, 95, 125, 79, 60, 100, 81, new EvAttackState(2)),
  EEVEE(65, 45, 55, 55, 50, 45, 65, 55, new EvSpDefenseState(1)),
  MEWTWO(306, 3, 106, 110, 90, 154, 90, 130, new EvSpAttackState(3)),
  TOTODILE(63, 45, 50, 65, 64, 44, 48, 43, new EvAttackState(1)),
  CROCONAW(142, 45, 65, 80, 80, 59, 63, 58, new EvAttackState(1), new EvDefenseState(1)),
  FERALIGATR(239, 45, 85, 105, 100, 79, 83, 78, new EvAttackState(2), new EvDefenseState(1)),
  CROBAT(241, 90, 85, 90, 80, 70, 80, 130, new EvSpeedState(3)),
  PICHU(41, 190, 20, 40, 15, 35, 35, 60, new EvSpeedState(1)),
  IGGLYBUFF(42, 170, 90, 30, 15, 40, 20, 15, new EvHpState(1)),
  MAREEP(56, 235, 55, 40, 40, 65, 45, 35, new EvSpAttackState(1)),
  FLAAFFY(128, 120, 70, 55, 55, 80, 60, 45, new EvSpAttackState(2)),
  AMPHAROS(225, 45, 90, 75, 85, 115, 90, 55, new EvSpAttackState(3)),
  GLIGAR(89, 60, 65, 75, 105, 35, 65, 85, new EvDefenseState(1)),
  SCIZOR(175, 25, 70, 130, 100, 55, 80, 65, new EvAttackState(2)),
  SURSKIT(54, 200, 40, 30, 32, 50, 52, 65, new EvSpeedState(1)),
  MASQUERAIN(145, 75, 70, 60, 62, 80, 82, 60, new EvSpAttackState(1), new EvSpDefenseState(1)),
  CASTFORM(147, 45, 70, 70, 70, 70, 70, 70, new EvHpState(1)),
  BAGON(60, 45, 45, 75, 60, 40, 30, 50, new EvAttackState(1)),
  SHELGON(147, 45, 65, 95, 100, 60, 50, 50, new EvDefenseState(2)),
  SALAMENCE(270, 45, 95, 135, 80, 110, 80, 100, new EvAttackState(3)),
  BELDUM(60, 3, 40, 55, 80, 35, 60, 30, new EvDefenseState(1)),
  METANG(147, 3, 60, 75, 100, 55, 80, 50, new EvDefenseState(2)),
  METAGROSS(270, 3, 80, 135, 130, 95, 90, 70, new EvDefenseState(3)),
  GROUDON(302,3,100,150,140,100,90,90, new EvAttackState(3)),
  SHINX(53, 235, 45, 65, 34, 40, 34, 45, new EvAttackState(1)),
  LUXIO(127, 120, 60, 85, 49, 60, 49, 60, new EvAttackState(2)),
  LUXRAY(235, 45, 80, 120, 79, 95, 79, 70, new EvAttackState(3)),
  SPIRITOMB(170, 100, 50, 92, 108, 92, 108, 35, new EvDefenseState(1), new EvSpDefenseState(1)),
  GIBLE(60, 45, 58, 70, 45, 40, 45, 42, new EvAttackState(1)),
  GABITE(144, 45, 68, 90, 65, 50, 55, 82, new EvAttackState(2)),
  GARCHOMP(270, 45, 108, 130, 95, 80, 85, 102, new EvAttackState(3)),
  SKORUPI(66, 120, 40, 50, 90, 30, 55, 65, new EvDefenseState(1)),
  DRAPION(175, 45, 70, 90, 110, 60, 75, 95, new EvDefenseState(2)),
  MAGNEZONE(241, 30, 70, 70, 115, 130, 90, 60, new EvSpAttackState(3)),
  GLISCOR(179, 30, 75, 95, 125, 45, 75, 95, new EvDefenseState(2)),
  ROTOM(154, 45, 50, 50, 77, 95, 77, 91, new EvSpAttackState(1), new EvSpeedState(1)),
  SNIVY(28, 45, 45, 45, 55, 45, 55, 63, new EvSpeedState(1)),
  SERVINE(145, 45, 60, 60, 75, 60, 75, 83, new EvSpeedState(2)),
  SERPERIOR(238, 45, 75, 75, 95, 75, 95, 113, new EvSpeedState(3)),
  
  /*agregados*/
  
  ELEKID(53, 45, 45, 63, 37, 65, 55, 95, new EvSpeedState(1)),
  ELECTABUZZ(127, 45, 65, 83, 57, 95, 85, 105, new EvSpeedState(2)),
  ELECTIVIRE(270, 45, 75, 123, 67, 95, 85, 95, new EvSpeedState(3)),
  
  MAGBY(47, 45, 45, 75, 47, 70, 55, 83, new EvSpeedState(1)),
  MAGMAR(136, 45, 65, 95, 57, 100, 85, 93, new EvSpeedState(2)),
  MAGMORTAR(270, 45, 75, 95, 67, 125, 95, 83, new EvSpeedState(3)),
  
  LARVITAR(60, 45, 50, 64, 50, 45, 50, 41, new EvSpeedState(1)),
  PUPITAR(144, 45, 70, 84, 70, 65, 70, 51, new EvSpeedState(2)),
  TYRANITAR(270, 45, 100, 134, 110, 95, 100, 61, new EvSpeedState(3)),
  
  PIPLUP(62, 45, 53, 51, 53, 61, 56, 40, new EvSpeedState(1)),
  PRINPLUP(142, 45, 64, 66, 68, 81, 76, 50, new EvSpeedState(2)),
  EMPOLEON(240, 45, 84, 86, 88, 111, 101, 60, new EvSpeedState(3)),
  
  AXEW(60, 75, 46, 87, 60, 30, 40, 57, new EvSpeedState(1)),
  FRAXURE(144, 60, 66, 117, 70, 40, 50, 67, new EvSpeedState(2)),
  HAXORUS(270, 45, 76, 147, 90, 60, 70, 97, new EvSpeedState(3));
  
  public int baseExperience;
  public int catchRate;
  public int hpBase;
  public int attackBase;
  public int defenseBase;
  public int spAttackBase;
  public int spDefenseBase;
  public int speedBase;
  public EvState[] evState;
  Enemies(int baseExperience, int catchRate, int hpBase, 
          int attackBase,int defenseBase, int spAttackBase,
          int spDefenseBase, int speedBase, EvState... evState){
    this.baseExperience = baseExperience;
    this.catchRate = catchRate;
    this.hpBase = hpBase;
    this.attackBase = attackBase;
    this.defenseBase = defenseBase;
    this.spAttackBase = spAttackBase;
    this.spDefenseBase = spDefenseBase;
    this.speedBase = speedBase;
    this.evState = evState;
  }


}
