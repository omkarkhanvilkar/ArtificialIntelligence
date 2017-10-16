package homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

// enum of all possible opertions
enum operations {
	M_MINUS_1,
	M_MINUS_2,
	C_MINUS_1,
	C_MINUS_2,
	M_MINUS_1_C_MINUS_1,
	M_PLUS_1,
	M_PLUS_2,
	C_PLUS_1,
	C_PLUS_2,
	M_PLUS_1_C_PLUS_1
}

public class BFSearch {
	
	public State[] searchState(State s) {
		if (s.isGoalState()) {
			// Goal reached
			System.out.println("Goal reached !!");
			State[] s1 = {s};
			return s1;
		}
		else {
			// Goal not reached, continue further
			State[] childStates = this.findChildStates(s);
			return childStates == null ? null : childStates;
		}
	}
	
	public State[] findChildStates(State s) {
		// Find child nodes for a state
		
		ArrayList<State> childStates = this.performOperation(s);
		ArrayList<State> validChilds = new ArrayList<State>();
		
		for (Iterator<State> iterator = childStates.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (state.isStateValid()) {
				validChilds.add(state);
			}
		}
		return !validChilds.isEmpty() ? validChilds.toArray(new State[validChilds.size()]) : null;		
	}
	
	
	private ArrayList<State> performOperation(State s) {
		// Performs app possible operations on the state to get child states 
		ArrayList<State> res = new ArrayList<State>();
		if (s.isBoat() == true) {
			// Reduces 1 or 2 Missionaries or Cannibals from the source
			
			for (operations o : operations.values()) {
				State operateState;
				switch (o) {
				case M_MINUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.subtractMissionaries();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case M_MINUS_2:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.subtractMissionaries();
					operateState.subtractMissionaries();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case C_MINUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.subtractCanniable();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case C_MINUS_2:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.subtractCanniable();
					operateState.subtractCanniable();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case M_MINUS_1_C_MINUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.subtractMissionaries();
					operateState.subtractCanniable();
					operateState.updateBoat();
					res.add(operateState);
					break;

				default:
					break;
				}			
			}
		}
		else {
			// Adds 1 or 2 Missionaries or Cannibals from the destination
			for (operations o : operations.values()) {
				State operateState;
				switch (o) {
				case M_PLUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.addMissionary();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case M_PLUS_2:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.addMissionary();
					operateState.addMissionary();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case C_PLUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.addCanniable();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case C_PLUS_2:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.addCanniable();
					operateState.addCanniable();
					operateState.updateBoat();
					res.add(operateState);
					break;
					
				case M_PLUS_1_C_PLUS_1:
					operateState = new State(s.getMissionaries(), s.getCanniables(), s.isBoat());
					operateState.addCanniable();
					operateState.addMissionary();
					operateState.updateBoat();
					res.add(operateState);
				
				default:
					break;
				}			
			}
		}
		return res;
	}
}
