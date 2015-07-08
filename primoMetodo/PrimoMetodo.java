package primoMetodo;
import java.util.Vector;

import letturaGrafo.*;

public class PrimoMetodo {
	
	Vector <Nodo> grafo = new Vector<Nodo>();
	
	Vector <Nodo> badTwin = new Vector<Nodo>();	
	Vector <Nodo> goodTwin = new Vector<Nodo>();
	Vector <Nodo> sincro = new Vector<Nodo>();
	Vector <Nodo> badTwinVecchio = new Vector<Nodo>();
	
	public PrimoMetodo(Vector<Nodo> v) //costruttore, deve avere il grafico su cui lavorare
	// si ricorda che il grafo è un vettore di nodi
	{
		//stampaTwin(v,"entrato");
		grafo = v;
	}
	
	public int solve(int l)  //letteralmente, svolge l'algoritmo 
	{
		int risultato = 0;  //massimo livello di diagnosticabilità
		int i=0; // a che giro siamo

		//PRIMO GIRO
		badTwin = getBadTwin(badTwinVecchio,i);
		goodTwin = getGoodTwin(badTwin);
		sincro = sincronizza(badTwin, goodTwin, i);	
		
		if(condizione(sincro))
		{
			risultato = i-1;
		}	
		i++;	
		return risultato;
	}
	
	private Vector<Nodo> sincronizza(Vector<Nodo> bt,Vector<Nodo> gt, int i)
	{
		Vector<Nodo> ris = new Vector<Nodo>();
		System.out.println("TODO SINCRO");
		return ris;
	}
	
	public Vector<Nodo> getGoodTwin(Vector<Nodo> bt)
	{
		//stampaTwin(bt,"bad twin");
		//PRIMA COSA, TOLGO LE TRANSIZIONI DI GUASTO
		Vector<Nodo> ris = new Vector<Nodo>();
		Vector<Nodo> risToltiIrraggiungibili = new Vector<Nodo>();  //se uno stato è irraggiungibile bisogna toglierlo!
		for (int i =0; i<bt.size(); i++) //tolgo le transazioni di guasto
		{
			Nodo nuovo = new Nodo();
			Vector <Transizione> nt = bt.get(i).getTransizioniNonGuaste();
			nuovo.setNome(bt.get(i).getNome());
			nuovo.setTransizioni(nt);
			ris.add(nuovo);
			//stampaTwin(ris,"bad twin senza guasti al giro:"+i);			
		}
		//stampaTwin(ris,"bad twin senza guasti");
		
		//SECONDO: ORA ALCUNI STATI NON SONO PIÙ RAGGIUNGIBILI, LI TOLGO
		for (int i=0; i<ris.size(); i++)
		{
			if(inVettore(ris.get(i).getNome(),raggiungibili(ris)))
			{
				risToltiIrraggiungibili.add(ris.get(i));
			}
		}
      
		//stampaTwin(risToltiIrraggiungibili,"bad twin solo raggiungibili");
		
		return risToltiIrraggiungibili;
	}
	
	private Vector<String> raggiungibili(Vector<Nodo> grafo ) // dice quali stati sono raggiungibili
	//rispetto al root, l'inizio del grafico. (IL ROOT È SEMPRE IL PRIMO NODO DEL GRAFO, QUELLO
	//SCRITTO PRIMA NELL'XML!)
	{
//		System.out.println("chiamo raggiungibii");
		Vector<String> ragg = new Vector<String>();
		Vector<String> ris = new Vector<String>();
		ris.add(grafo.get(0).getNome()); // root sempre raggiungibile..
		Nodo root = grafo.get(0);
		ragg.addAll(root.getTutteDest());
		int dove=0;
		while(dove<ragg.size())
		{
//			System.out.println("raggprima="+ragg.size());
//			System.out.println("dove prima" +dove );
			String questa = ragg.get(dove);
			if(!inVettore(questa,ris))
			{
				ris.add(questa);
			}
			dove++;
	//		System.out.println("dove dopo" +dove );
			Nodo questo = getNodoByName(questa, grafo);
			Vector<String> nuove = questo.getTutteDest();
//			System.out.println("nodo:"+questo.getNome()+"; dests:"+nuove.size()); 
			for (int i=0; i<nuove.size(); i++)
			{
				//System.out.println("ago:"+nuove.get(i));
				for (int c=0; c<ris.size(); c++)
				{
					//System.out.println("pagliaio:"+ ris.get(c));
				}
				if(!inVettore(nuove.get(i),ris))
				{
					ris.add(nuove.get(i));
					ragg.add(nuove.get(i));
				}
			}
		}
		
		return ris;
	}
	
	private Nodo getNodoByName(String n,Vector<Nodo> g)
	{
		Nodo ris = null;
		for (int i=0; i<g.size(); i++)
		{
			if(g.get(i).getNome().equalsIgnoreCase(n))
			{
				ris=g.get(i);
			}
		}
		return ris;
	}
	
	private boolean inVettore(String ago, Vector<String> pagliaio)//dice se c'è un
	//occorrenza nel vettore
	{
		boolean ris = false;
		for(int i=0; i<pagliaio.size(); i++)
		{
			if(ago.equalsIgnoreCase(pagliaio.get(i)))
			{
				ris=true;
			}
		}
		return ris;
	}
	
	//versione con l'oggetto invece che con la parola
	private boolean inVettore(Nodo ago, Vector<Nodo> pagliaio)
	{
		boolean ris = false;
		for(int i=0; i<pagliaio.size(); i++)
		{
			if(ago.getNome().equalsIgnoreCase(pagliaio.get(i).getNome()))
			{
				ris=true;
			}
		}
		return ris;
	}
	
	private void stampaTwin(Vector<Nodo> a, String nome) //molto comodo per il debug,
	//stampa a schermo il contenuto del grafo
	{
	
		System.out.println("sono il twin: "+ nome);
		for (int i=0; i<a.size(); i++)
		{
			System.out.println(a.get(i).getNome());
			Vector<Transizione> v = a.get(i).getTransizioniTutte();
			for (int s=0; s<v.size(); s++)
			{
				System.out.println("    "+v.get(s).getLettera());
				System.out.println("    "+v.get(s).getOss());
				System.out.println("    "+v.get(s).getDest());
			}			
		}
	}

	private Vector<Nodo> getBadTwin(Vector<Nodo> bt, int a)
	{
		Vector<Nodo> ris = new Vector<Nodo>();
		if(a==0) //se siamo al primo ciclo
		{
			ris = grafo;
		}
		else
		{
			ris = getBadTwinOver1(bt);
		}
		return ris;
	}
	
	private Vector<Nodo> getBadTwinOver1(Vector<Nodo> bt) // PREPARA LE ENTRATE PREVISTE PER L'ALGORITMO
	{
		Vector<Nodo> ris = new Vector<Nodo>();
		//eseguo l'algoritmo dato dai testo
		Vector<Nodo> S = bt; //S
		Nodo s0 = bt.get(1); //s0
		Vector<Transizione> T = getTransizioni(bt);
		Vector<Transizione> To = getTransizioniOsservabili(bt);
		Vector<Transizione> Tf = getTransizioniGuaste(bt);
		ris = algoritmoBadTwinOver1(S,s0,T,To,Tf);		
		return ris;
	}
	
	private Vector<Nodo> algoritmoBadTwinOver1(Vector<Nodo> S, Nodo s0, 
			Vector<Transizione> T, Vector<Transizione> To,Vector<Transizione> Tf)
	{
		Vector<Nodo> Sprimo = S;
		Nodo s0primo = s0;
		Sprimo = svuotaTransizioni(Sprimo);
		Vector<Transizione> Tprimo = To;
		Vector<Transizione> Tfprimo = new Vector<Transizione>();
		Vector<Transizione> Tnoprimo = getTransizioniNonOsservabili(S);
		boolean fault = true;
		for(int i=0; i<S.size(); i++)
		{
			for (int a=0; a<Tnoprimo.size(); a++)
			{
				if (inVettore(Tnoprimo.get(a),Tfprimo))
				{
					fault = true;
				}
				else
				{
					fault = false;
				}
				String sd= Tnoprimo.get(a).getDest();
				String epselon = Tnoprimo.get(a).getLettera();???
				Icorsiva = find(S, s0, T, To, Tf,sd,1,fault,epselon);
			}
		}
		return Sprimo;
	}
	
	private boolean inVettore(Transizione ago, Vector<Transizione> pagliaio)
	{
		boolean ris = false;
		for(int i=0; i<pagliaio.size(); i++)
		{
			if(pagliaio.get(i).getLettera().equalsIgnoreCase(ago.getLettera()))
			{
				ris = true;
			}
		}
		return ris;
	}
	
	private Vector<Nodo> svuotaTransizioni(Vector<Nodo> v)
	{
		Vector<Nodo> ris = v;
		for (int i=0; i<ris.size(); i++)
		{
			ris.get(i).svuotaTransizioni();
		}
		return ris;
	}
	
	private Vector<Transizione> getTransizioni(Vector<Nodo> v)
	{
		Vector<Transizione> ris = new Vector<Transizione>();
		for(int i=0; i<v.size(); i++)
		{
			ris.addAll(v.get(i).getTransizioniTutte());
		}
		return ris;
	}
	
	private Vector<Transizione> getTransizioniGuaste(Vector<Nodo> v)
	{
		Vector<Transizione> ris = new Vector<Transizione>();
		for(int i=0; i<v.size(); i++)
		{
			ris.addAll(v.get(i).getTransizioniGuaste());
		}
		return ris;
	}
	
	private Vector<Transizione> getTransizioniNonOsservabili(Vector<Nodo> v)
	{
		Vector<Transizione> ris = new Vector<Transizione>();
		for(int i=0; i<v.size(); i++)
		{
			ris.addAll(v.get(i).getTransizioniNonOsservabili());
		}
		return ris;
	}
	
	private Vector<Transizione> getTransizioniOsservabili(Vector<Nodo> v)
	{
		Vector<Transizione> ris = new Vector<Transizione>();
		for(int i=0; i<v.size(); i++)
		{
			ris.addAll(v.get(i).getTransizioniOsservabili());
		}
		return ris;
	}
	
	private boolean condizione(Vector<Nodo> si)
	{
		boolean ris = false;
		System.out.println("crea stocazzo di condizione");
		return ris;
	}

}
