package br.edu.ifsp.dsw1.model.observer;

import java.util.LinkedList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;

public class SalaDeDesembarque implements FlightDataObserver{

	private List<FlightData> voos;
	
	public SalaDeDesembarque() {
		
		voos = new LinkedList<>();
	}
	@Override
	public void update(FlightData flight) {
		
		if (!voos.contains(flight)) {
			if (flight.getState().getClass().getSimpleName().equals("Arriving")) {
				voos.add(flight);
			}			
		}else {
			if (!flight.getState().getClass().getSimpleName().equals("Arriving")) {
				voos.remove(flight);
			}
		}
	}

	public List<FlightData> retornaLista(){
		return voos;
	}
}
