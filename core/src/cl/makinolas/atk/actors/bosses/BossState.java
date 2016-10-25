package cl.makinolas.atk.actors.bosses;


public abstract class BossState {

    private StateProcessor processor;

    public BossState(StateProcessor processor){
        this.processor = processor;
    }

    protected StateProcessor getProcessor() {
        return processor;
    }

    public void goBack(){
        getProcessor().goBack();
    }

    public void onChoose(){}

    public abstract void act(float delta);

}
