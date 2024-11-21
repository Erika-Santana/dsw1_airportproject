package br.edu.ifsp.dsw1.model.observer;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class SalaDeEmbarque implements FlightDataObserver {
	
	
	private List<FlightData> voos;
	
	public SalaDeEmbarque() {
		
		voos = new LinkedList<>();
	}
	@Override
	public void update(FlightData flight) {
		
		if (flight.getState().getClass().getSimpleName().equals("Boarding")) {
			voos.add(flight);
		}
		
	}
	

	public List<FlightData> retornaLista(){
		return voos;
	}

}
