import java.util.ArrayList;
import java.util.List;
/**
 * A játékban lévő Mezőket reprezentáló absztrakt osztály.
 */
public abstract class Mezo {
	/**
	 * A rajta álló játékosok listája.
	 */
	protected List<Jatekos> jatekosRajta;

	/**
	 * Létrehoz egy mezőt, és inicializálja a jatekoRajta listát.
	 */
	public Mezo(){
		jatekosRajta = new ArrayList<Jatekos>();
	}

	/**
	 * Kap egy játékost a mező. Leszármazottakban eldől, hogy elfogadja-e.
	 * @param j Játékos aki érkezik.
	 * @return Igazat ad vissza ha elfogadja a játékost.
	 */
	public abstract boolean jatekostElfogad(Jatekos j);

	/**
	 * Eltávolítja a paramétkent kapott játekost a játekosRajta listából.
	 * @param j A játékos, akit el akarunk távolítani a listaból.
	 */
	public void jatekostEltavolit(Jatekos j){
		jatekosRajta.remove(j);
	}

	public void targyLerakas(Cso cs){}

	public void targyLerakas(Pumpa p){}

	public abstract boolean setCsucsToNull(Mezo m);

	public abstract boolean setCsoToNull(Mezo m);

	public void szereloJavit(){}

	public void szabotorElront(){}

	public Pumpa pumpaLetrehozasa(){return null;}

	public abstract List<? extends Mezo> getNeighbours();

	public void atallit(int kimeneti, int bemeneti){}
}
