package cl.makinolas.atk.types;

public abstract class AbstractType implements IType{
	
	protected boolean isBug = false;
	protected boolean isDark = false;
	protected boolean isDragon = false;
	protected boolean isElectric = false;
	protected boolean isFairy = false;
	protected boolean isFight = false;
	protected boolean isFire = false;
	protected boolean isFlying = false;
	protected boolean isGhost = false;
	protected boolean isGrass = false;
	protected boolean isGround = false;
	protected boolean isIce = false;
	protected boolean isNormal = false;
	protected boolean isPoison = false;
	protected boolean isPsychic = false;
	protected boolean isRock = false;
	protected boolean isSteel = false;
	protected boolean isWater = false;
	
	public double  attackFromType(IType type){
		return 1;
	}
	
	public double attackToType(IType type){
		return 1;
	}
	
	public double bugPokemonAttacks(IType type) {
		return 1;
	}
	
	public double darkPokemonAttacks(IType type) {
		return 1;
	}
	
	public double dragonPokemonAttacks(IType type) {
		return 1;
	}
	
	public double electricPokemonAttacks(IType type) {
		return 1;
	}
	
	public double fairyPokemonAttacks(IType type) {
		return 1;
	}
	
	public double fightPokemonAttacks(IType type) {
		return 1;
	}
	
	public double firePokemonAttacks(IType type) {
		return 1;
	}
	
	public double flyingPokemonAttacks(IType type) {
		return 1;
	}
	
	public double ghostPokemonAttacks(IType type) {
		return 1;
	}
	
	public double grassPokemonAttacks(IType type) {
		return 1;
	}
	
	public double groundPokemonAttacks(IType type) {
		return 1;
	}
	
	public double icePokemonAttacks(IType type) {
		return 1;
	}
	
	public double normalPokemonAttacks(IType type) {
		return 1;
	}
	
	public double poisonPokemonAttacks(IType type) {
		return 1;
	}
	
	public double psychicPokemonAttacks(IType type) {
		return 1;
	}
	
	public double rockPokemonAttacks(IType type) {
		return 1;
	}
	
	public double steelPokemonAttacks(IType type) {
		return 1;
	}
	
	public double waterPokemonAttacks(IType type) {
		return 1;
	}
	
	@Override
	public double monsterHasAttackedFromBug() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromDark() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromDragon() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromElectric() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromFairy() {
		return 1;
	}
	
	@Override
	public double monsterHasAttackedFromFight() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromFire() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromFlying() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromGhost() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromGrass() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromGround() {
		return 1;
	}
	
	@Override
	public double monsterHasAttackedFromIce() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromNormal() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromPoison() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromPsychic() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromRock() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromSteel() {
		return 1;
	}

	@Override
	public double monsterHasAttackedFromWater() {
		return 1;
	}
	
	public boolean isBug(){
		return isBug;
	}
	public boolean isDark(){
		return isDark;
	}
	public boolean isDragon(){
		return isDragon;
	}
	public boolean isElectric(){
		return isElectric;
	}
	public boolean isFairy(){
		return isFairy;
	}
	public boolean isFight(){
		return isFight;
	}
	public boolean isFire(){
		return isFire;
	}
	public boolean isFlying(){
		return isFlying;
	}
	public boolean isGhost(){
		return isGhost;
	}
	public boolean isGrass(){
		return isGrass;
	}
	public boolean isGround(){
		return isGround;
	}
	public boolean isIce(){
		return isIce;
	}
	public boolean isNormal(){
		return isNormal;
	}
	public boolean isPoison(){
		return isPoison;
	}
	public boolean isPsychic(){
		return isPsychic;
	}
	public boolean isRock(){
		return isRock;
	}
	public boolean isSteel(){
		return isSteel;
	}
	public boolean isWater(){
		return isWater;
	}
	
}
