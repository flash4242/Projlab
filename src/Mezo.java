import java.util.ArrayList;
import java.util.List;
/**
 * A játékban lévő Mezőket reprezentáló absztrakt osztály.
 * A játékosok mezőkön állnak és ezeknek a szomszédaira mozoghatnak.
 */
public abstract class Mezo {
	/**
	 * A rajta álló játékosok listája.
	 */
	protected List<Jatekos> jatekosRajta;

	/**
	 * Létrehoz egy mezőt, és inicializálja a jatekosRajta listát.
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
		Skeleton.startMethod(getClass().getName(), "jatekostEltavolit()");
		jatekosRajta.remove(j);
		Skeleton.endMethod();
	}

	/**
	 * Az adott mezőre lerakja a csövet, leszármazottak felülírják.
	 * @param cs A kapott cső
	 */
	public void targyLerakas(Cso cs){
		Skeleton.startMethod(getClass().getName(), "targyLerakas()");
		Skeleton.endMethod();
	}

	/**
	 * Az adott mezőre lerakja a pumpát, leszármazottak felülírják.
	 * @param p A kapott pumpa
	 */
	public void targyLerakas(Pumpa p){
		Skeleton.startMethod(getClass().getName(), "targyLerakas()");
		Skeleton.endMethod();
	}

	/**
	 * A szerelő megjavít egy mezőt. Üres függvény, mivel a Mezo nem tud megjavulni.
	 * Azon leszármazottakban amiket meg lehet javítani meg kell valósítani.
	 */
	public void szereloJavit(){
		Skeleton.startMethod(getClass().getName(), "szereloJavit()");
		Skeleton.endMethod();
	}

	/**
	 * A szerelő megjavít egy mezőt. Üres függvény, mivel a Mezo nem tud elromlani.
	 * Azon leszármazottakban amiket el tud rontani meg kell valósítani.
	 */
	public void szabotorElront(){
		Skeleton.startMethod(getClass().getName(), "szabotorElront()");
		Skeleton.endMethod();
	}

	/**
	 * A mező létrehoz egy pumpát. Üres függvény, mivel a Mezo nem hozhat létre pumpát.
	 * Azon leszármazottakban amik létrehoznak, meg kell valósítani.
	 * @return A létrejött pumpa
	 */
	public Pumpa pumpaLetrehozasa(){
		Skeleton.startMethod(getClass().getName(), "pumpaLetrehozasa()");
		Skeleton.endMethod();
		return null;
	}

	/**
	 * Jelezzük a mezőnek, hogy szeretnénk a hozzá kapcsolódó cs-edik csövet megkapni.
	 * Minden leszármazott implementálja
	 * @param cs A szomszédos csövek közül melyiket szeretnénk megkapni.
	 * @return A mező által visszaadott cső, vagy null.
	 */
	public abstract Cso adjCsovet(int cs);

	/**
	 * Visszaadja a mezővel szomszédos mezők listáját.
	 * @return A szomszédos mezők listája.
	 */
	public abstract List<? extends Mezo> getNeighbours();

	/**
	 * Egy Jatekos átállítja a mezőt. Üres függvény, mivel a Mezo nem átállítható.
	 * Azon leszármazottakban amik átállíthatók, meg kell valósítani.
	 * @param kimeneti bemeneti cső index a szomszédos mezőkhöz
	 * @param bemeneti kimeneti cső index a szomszédos mezőkhöz
	 */
	public void atallit(int kimeneti, int bemeneti){
		Skeleton.startMethod(getClass().getName(), "atallit()");
		Skeleton.endMethod();
	}

	public void setJatekosRajta(Jatekos j){
		Skeleton.startMethod(getClass().getName(), "setJatekosRajta()");
		jatekosRajta.add(j);
		Skeleton.endMethod();
	}
}
