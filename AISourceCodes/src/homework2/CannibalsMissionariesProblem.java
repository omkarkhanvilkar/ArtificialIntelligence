package homework2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class CannibalsMissionariesProblem {
	// Open List - contains all the states that are to be expanded
	Queue<State> openList = new LinkedList<State>();
	
	// Closed List - contains all the states that are expanded and searched
	static Queue<State> closedList = new LinkedList<State>();
	
	public static boolean checkIfClosed(State s) {
		// Checks if a particular state is in the closed list 
		for (Iterator<State> iterator = closedList.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (s.compareWith(state) == true) {
				return true;
			}			
		}
		return false;
	}
	
	public static boolean checkIfInOpen(Queue<State>openlist, State s) {
		// Checks if a particular state is in the open list 
		for (Iterator<State> iterator = openlist.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			if (s.compareWith(state)){
				return true;
			}			
		}
		return false;
	}
	
	public static void main(String[] args) {
		CannibalsMissionariesProblem cmp = new CannibalsMissionariesProblem();
		State root = new State(3, 3, true);
		BFSearch search = new BFSearch();
		cmp.openList.add(root);
		
		while(cmp.openList.isEmpty() == false) {
			System.out.println("Open List");
			for (State state : cmp.openList) {
				System.out.println(state.toString());
			}
			
			State currentSearchState = cmp.openList.poll();
			System.out.println("Current state: " + currentSearchState);
			closedList.add(currentSearchState);
			State[] childs = search.searchState(currentSearchState);
			
			if (childs == null) {
				System.out.println("Tree over, goal not found");
			}
			else if(childs.length == 1 && childs[0].compareWith(currentSearchState) == true) {
				// Current node is the goal node. End search.
				break;
			}
			else {
				if (cmp.openList.size() == 0) {
					// Open list is empty, add first batch of child nodes
					for (State child : childs) {
						cmp.openList.add(child);
					}										
				}
				else {					
					for (State child : childs) {
						// Check if open list already contains this state
						if (CannibalsMissionariesProblem.checkIfInOpen(cmp.openList, child) == false) {
							cmp.openList.add(child);
						}
					}
				}
			}			
		}
		
		System.out.println("Complete--------------");
		System.out.println("Closed list:");
		for (Iterator<State> iterator = closedList.iterator(); iterator.hasNext();) {
			State state = (State) iterator.next();
			System.out.println(state.toString());
		}
		
	}

}
