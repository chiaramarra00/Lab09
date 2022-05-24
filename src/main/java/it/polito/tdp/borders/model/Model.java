package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private List<Country> countries;
	Map<Integer, Country> countriesIdMap;

	private Graph<Country, DefaultEdge> grafo;

	public Model() {
		countries = getCountries();
	}

	public List<Country> getCountries() {
		if (countries == null) {
			BordersDAO dao = new BordersDAO();
			countries = dao.loadAllCountries();

			countriesIdMap = new HashMap<Integer, Country>();
			for (Country c : countries)
				countriesIdMap.put(c.getcCode(), c);

		}
		return countries;
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		
		BordersDAO dao = new BordersDAO();
		
		List<Border> countriesToConnect = dao.getCountryPairs(anno);
		for (Border border : countriesToConnect) {
			grafo.addVertex(countriesIdMap.get(border.getC1()));
			grafo.addVertex(countriesIdMap.get(border.getC2()));
			grafo.addEdge(countriesIdMap.get(border.getC1()), countriesIdMap.get(border.getC2()));
		}
	}
	
	public List<String> elencoStati() {
		List<String> elenco = new ArrayList<String>();
		for (Country c : grafo.vertexSet()) {
				elenco.add(c + " (grado = " + grafo.degreeOf(c)/2 + ")");
		}
		return elenco;
	}
	
	public int nComponentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(grafo);
		return ci.connectedSets().size();
	}
	
	public Map<Country,Country> visitaGrafo(Country partenza) {
		GraphIterator<Country, DefaultEdge> visita = new BreadthFirstIterator<>(grafo,partenza);
		
		Map<Country,Country> alberoInverso = new HashMap<>();
		alberoInverso.put(partenza, null);
		
		visita.addTraversalListener(new RegistraAlberoDiVisita(grafo, alberoInverso));
		while (visita.hasNext()) {
			Country c = visita.next();
		}
		
		return alberoInverso;
	}

}
