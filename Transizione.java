package letturaGrafo;

public class Transizione { //arco tra 2 stati (nodi)
	
	String lettera;
	String osservabilita;
	String destinazione;
	String guasto;
	
	public Transizione(String let, String oss, String gu, String dest)
	{
		lettera = let;
		osservabilita = oss;
		if(osservabilita.equalsIgnoreCase("n"))  // se non è osservabile non dovrei vedere la lettera..
			// ma ha comunque una lettera
		{
			lettera += "-ma è un segreto!" ;
		}
		destinazione = dest;
		guasto = gu;
	}
	
	public String getGuasto()
	{
		return guasto;
	}
	
	public String getLettera()
	{
		return lettera;
	}	
	
	public String getOss()
	{
		return osservabilita;
	}
	
	public String getDest()
	{
		return destinazione;
	}

}
