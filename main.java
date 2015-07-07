import java.util.Vector;

import primoMetodo.PrimoMetodo;
import letturaGrafo.Controllore;
import letturaGrafo.Graficatore;
import letturaGrafo.Nodo;


public class Main {
	
	// POSTULATi: 
	//1)LO STATO ROOT è il primo scritto nell'xml in alto
	//2)le parole Nodo e Stato sono usate in modo intercambiabile nei commenti
    //3) i twin generati secondo l'algoritmo possono violare le regole imposte sul grafo iniziale
	//(es: nel grafo iniziale ogni stato deve avere un'uscita, se nei twin capitasse uno stato
	//senza uscite perchè ad esempio facendo il good twin l'unica uscita con transizione guasto 
	//viene scartata) esso verrebbe accettato comunque])
	
	static Vector<Nodo> grafo = new Vector<Nodo>();

	public static void main(String[] args) {
		
		//LETTURA: il grafico è un vettore di nodi. un nodo ha un vettore di transizioni.
		Graficatore g = new Graficatore();
		grafo = g.getGrafo();  //leggi il grafo dall'xml competente
		boolean ok = false;
		Controllore c = new Controllore(grafo);  //controlla se il grafico è accettabile
		if(c.valida())
		{
			System.out.println("lettura ok;");
		}

//		//METODO RISOLUTIVO 1
//		PrimoMetodo p = new PrimoMetodo(grafo);
//		int l = 4; //livello da controllare
//		p.solve(l);
//		
	}

}
