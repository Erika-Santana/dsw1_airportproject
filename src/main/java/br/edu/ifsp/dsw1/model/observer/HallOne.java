package br.edu.ifsp.dsw1.model.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class HallOne implements FlightDataObserver{
	

	private List<FlightData> voos;
	
	public HallOne() {
		
		voos = new LinkedList<FlightData>();
	}
	@Override
	public void update(FlightData flight) {
		if (!voos.contains(flight)) {
			if (flight.getState().getClass().getSimpleName().equals("TakingOff")) {
				voos.add(flight);
			}			
		}else {
			if (!flight.getState().getClass().getSimpleName().equals("TakingOff")) {
				voos.remove(flight);
			}
		}
		
	}
	

	public List<FlightData> retornaLista(){
		return new ArrayList<FlightData>(voos);
	}

}
