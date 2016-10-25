package cl.makinolas.atk.actors.bosses;

public abstract class StateProcessor {

    protected BossState[] states;
    private int currentState;
    private float acc, maxT;

    protected StateProcessor(float mt) {
        maxT = mt;
        acc = 0;
        currentState = -1;
    }


    public void addStates(BossState ... states){
        this.states = states;
    }

    public void act(float delta){
        if(currentState == -1){
            acc += delta;
            if(acc >= maxT){
                changeState();
                acc = 0;
            }
        }
        else
            states[currentState].act(delta);
    }

    public void goBack(){
        currentState = -1;
    }

    public abstract void changeState();

    protected void setCurrentState(int st){
        currentState = st;
        states[currentState].onChoose();
    }

}
