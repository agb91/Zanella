package primoMetodo;

import static org.junit.Assert.*;

import java.util.Vector;

import letturaGrafo.Graficatore;
import letturaGrafo.Nodo;
import letturaGrafo.Transizione;

import org.junit.Test;

public class testPrimoMetodo {
	
	@Test
	public void testGetGoodTwin()
	{
//		// caso c'e 1 irraggiunibile
//		Graficatore g1 = new Graficatore("src/grafoBadTwin1.xml");
//		Graficatore gatteso1 = new Graficatore("src/grafoBadTwinSol1.xml");
//		PrimoMetodo pm = new PrimoMetodo( g1.getGrafo());
//		//stampaTwin(gatteso1.getGrafo(),"twin atteso:");
//		//System.out.println("_________________");
//		//stampaTwin(pm.getGoodTwin(g1.getGrafo()),"twin arrivato");
//		assertEquals("error su badTwin1", gatteso1.getGrafo().size() , pm.getGoodTwin(g1.getGrafo()).size());
//		for (int i=0; i<gatteso1.getGrafo().size(); i++)
//		{
//			assertEquals("error su badTwin1", gatteso1.getGrafo().get(i).quanteTransizioni() , pm.getGoodTwin(g1.getGrafo()).get(i).quanteTransizioni());
//			assertEquals("error su badTwin1", gatteso1.getGrafo().get(i).quanteTransizioniOsservabili() , pm.getGoodTwin(g1.getGrafo()).get(i).quanteTransizioniOsservabili());
//		}
//		
//		//caso no irraggiungibili
//		Graficatore g2 = new Graficatore("src/grafoBadTwin2.xml");
//		Graficatore gatteso2 = new Graficatore("src/grafoBadTwinSol2.xml");
//		PrimoMetodo pm2 = new PrimoMetodo( g2.getGrafo());
//		assertEquals("error su badTwin2", gatteso2.getGrafo().size() , pm2.getGoodTwin(g2.getGrafo()).size());
//		for (int i=0; i<gatteso2.getGrafo().size(); i++)
//		{
//			assertEquals("error su badTwin2", gatteso2.getGrafo().get(i).quanteTransizioni() , pm2.getGoodTwin(g2.getGrafo()).get(i).quanteTransizioni());
//			assertEquals("error su badTwin2", gatteso2.getGrafo().get(i).quanteTransizioniOsservabili() , pm2.getGoodTwin(g2.getGrafo()).get(i).quanteTransizioniOsservabili());
//		}
		
		//caso 2 irraggiugibili
		Graficatore g3 = new Graficatore("src/grafoBadTwin3.xml");
		Vector<Nodo> grafo3 = new Vector<Nodo>();
		grafo3=g3.getGrafo();
		PrimoMetodo pm3 = new PrimoMetodo(grafo3);
		pm3.getGoodTwin(grafo3);
	}
	
	private void stampaTwin(Vector<Nodo> a, String nome)
	{
	
		System.out.println("sono il twin: "+ nome);
		System.out.println("di dimensione: "+a.size());
		for (int i=0; i<a.size(); i++)
		{
			System.out.println(a.get(i).getNome());
			Vector<Transizione> v = a.get(i).getTransizioniTutte();
			for (int s=0; s<v.size(); s++)
			{
				System.out.println("    lett:"+v.get(s).getLettera());
				System.out.println("    oss:"+v.get(s).getOss());
				System.out.println("    guasto:"+v.get(s).getGuasto());
				System.out.println("    destinaz:"+v.get(s).getDest());
			}			
		}
	}

}
