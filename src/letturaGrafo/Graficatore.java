package letturaGrafo;
import java.util.Vector;


public class Graficatore {  //si occupa di dare un grafico pronto al main

	static LeggiGrafo lg;
	private static String path="";

	public Graficatore()  //costruttore in caso generale
	{
		path="src/grafo.xml";
	}
	
	public Graficatore(String _path)  //costruttore speciale per i test
	{
		path=_path;
	}
	
	public static Vector<Nodo> getGrafo()  //restituisce un grafo completo
	{
		Vector <Nodo> g = new Vector <Nodo>();
		lg = new LeggiGrafo(path);     //dato un path questa classe legge dall'xml e rende
		// delle stringhe e un numero di nodi. queste stringhe diventano sensate appena
		// vengono passate al contruttore della classe Transizione che sa cosa farne
		int quantiNodi = lg.getNumNodi(); //quanti nodi hai letto?
		for (int i =0; i<quantiNodi; i++)
		{
			Nodo n = new Nodo(getNodo(i));	//costruisce nodo
			//usa una funzione getNodo vedi sotto
			g.add(n); //lo aggiunge a grafico
		}
	
		return g;
	}
	
	private static String getNodo(int n)   // usa lettore per leggere una stringa. Nodo sa cosa 
	// deve fare con quella string  per costruire un oggetto nodo con le sue transazioni
	{
		String ris = lg.leggi(n);
		return ris;
	}

}
