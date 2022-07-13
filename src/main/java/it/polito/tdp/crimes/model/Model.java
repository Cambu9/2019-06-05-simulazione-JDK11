package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	public Graph<District, DefaultWeightedEdge> grafo;
	private EventsDao dao = new EventsDao();

	public String creaGrafo(int anno) {
		this.grafo = new SimpleWeightedGraph<District, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//aggiungo vertici
		Graphs.addAllVertices(this.grafo, dao.getVertici(anno));
		
		//aggiungo i vertici
		List<District> distretti = new ArrayList<>(this.grafo.vertexSet());
		
		for (District d1: distretti) {
			for(District d2: distretti) {
				if(d1.getId() != (d2.getId())) {
					double peso = LatLngTool.distance(d1.getPosizione(), d2.getPosizione(), LengthUnit.KILOMETER);
					Graphs.addEdgeWithVertices(this.grafo, d1, d2, peso);
				}
			}
		}
		
		return "Grafo creato \n#Vertici: " + this.grafo.vertexSet().size() + "\n#Archi: " + this.grafo.edgeSet().size();
	}
	
	public List<District> getVertici(){
		List<District> distretti = new ArrayList<>(this.grafo.vertexSet());
		return distretti;
	}
	
	public Map<Double, District> distrettiAdiacenti(District d){
		Map<Double, District> ordinati = new TreeMap<>();
		List<District> adiacenti = new ArrayList<District>();
		adiacenti = Graphs.neighborListOf(this.grafo, d);
		for(District d1: adiacenti) {
			double peso = this.grafo.getEdgeWeight(this.grafo.getEdge(d1, d));
			ordinati.put(peso, d1);
		}
		return ordinati;
	}
	
	public List<Integer> getAnni(){
		return dao.getAnni();
	}
	
	public District cittaMenoCriminosa() {
		int cMin = 1000000;
		int crimini;
		List<District> vertici = new ArrayList<District>();
		vertici = this.getVertici();
		for(District d: vertici) {
			crimini = d.getCrimini();
			if(crimini<cMin) {
				cMin = crimini;
			}
		}
		for(District d: vertici) {
			if(d.getCrimini() == cMin) {
				return d;
			}
		}
		return null;
	}
	
	public List<Event> allEvent(int anno, int mese, int giorno){
		return dao.listAllEvents(anno, mese, giorno);
	}
}
