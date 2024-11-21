package br.edu.ifsp.dsw1.model.observer;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifsp.dsw1.model.entity.FlightData;
import br.edu.ifsp.dsw1.model.flightstates.Boarding;
import br.edu.ifsp.dsw1.model.flightstates.TookOff;

public class SalaDeEmbarque implements FlightDataObserver {

	private List<FlightData> voos;

	public SalaDeEmbarque() {

		voos = new LinkedList<FlightData>();
	}

	@Override
	public void update(FlightData flight) {
		if (!voos.contains(flight)) {
			if (flight.getState() instanceof Boarding) {
				voos.add(flight);
			}
		} else {

			if (!(flight.getState() instanceof Boarding)) {
				voos.remove(flight);
			}
		}
	}

	public List<FlightData> retornaLista() {
		return new ArrayList<FlightData>(voos);
	}

}
