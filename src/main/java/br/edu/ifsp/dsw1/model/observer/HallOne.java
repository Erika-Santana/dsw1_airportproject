package br.edu.ifsp.dsw1.model.observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.TakingOff;

public class HallOne implements FlightDataObserver{
	

	private List<FlightData> voos;
	
	public HallOne() {
		
		voos = new LinkedList<FlightData>();
	}
	public void update(FlightData flight) {
	   
	    if (!voos.contains(flight)) {
	        if (flight.getState() instanceof TakingOff) { 
	            voos.add(flight);
	        }
	    } else {
	      
	        if (!(flight.getState() instanceof TakingOff)) {
	            voos.remove(flight);
	        }
	    }
	}


	public List<FlightData> retornaLista(){
		return new ArrayList<FlightData>(voos);
	}

}
