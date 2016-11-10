package cl.makinolas.atk.stateEfects;

import java.util.ArrayList;

import cl.makinolas.atk.actors.Monsters;

public abstract class AbstractStateEfects implements IStateEfects {
	
	protected DrawStateEfects drawEfects;
	protected Monsters monster;

	@Override
	public  void affect(Monsters monsters, int prob, ArrayList<IStateEfects> states) {
		if(!states.contains(this)){
			double val = 100*Math.random();
			if(val<prob){
				this.affectMonsters();
				states.add(this);
			}
		}
	}

	public DrawStateEfects getDrawStateEfects(){
		return this.drawEfects;
	}
	
	@Override
	public  void destroy() {
		monster.removeState(this);
	}
	
	@Override
	public boolean equals(Object o){
		if(o.getClass()==this.getClass()){
			return true;
		}else{
			return super.equals(o);
		}
	}

}
