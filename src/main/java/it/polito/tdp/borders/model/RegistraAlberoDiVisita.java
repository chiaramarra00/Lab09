package it.polito.tdp.borders.model;

import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;

public class RegistraAlberoDiVisita implements TraversalListener<Country, DefaultEdge> {
	
	private Graph<Country, DefaultEdge> grafo;
	private Map<Country, Country> alberoInverso;

	public RegistraAlberoDiVisita(Graph<Country, DefaultEdge> grafo, Map<Country, Country> alberoInverso) {
		super();
		this.grafo = grafo;
		this.alberoInverso = alberoInverso;
	}

	@Override
	public void connectedComponentFinished(ConnectedComponentTraversalEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectedComponentStarted(ConnectedComponentTraversalEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> e) {
		
		Country source = grafo.getEdgeSource(e.getEdge());
		Country target = grafo.getEdgeTarget(e.getEdge());
		
		if (!alberoInverso.containsKey(target)) {
			alberoInverso.put(target, source);
		}
		else if (!alberoInverso.containsKey(source)) {
			alberoInverso.put(source, target);
		}

	}

	@Override
	public void vertexTraversed(VertexTraversalEvent<Country> e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void vertexFinished(VertexTraversalEvent<Country> e) {
		// TODO Auto-generated method stub

	}

}
