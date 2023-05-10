/**
 * A játékban lévő szabotőröket reprezentálja. Képes csövet kilyukasztani valamint ragadóssá
 * és csúszóssá tenni. Célja, hogy minél több víz elfolyjon a lyukas csöveken keresztül.
 */
public class Szabotor extends Jatekos {
    /**
     * A csövet, amin áll csúszóssá teszi.
     * A szerelő az aktMezo-nek meghívja az allapotValtozas() metódusát CSUSZOS paraméterrel.
     */
    void csuszosit(){
        getAktMezo().allapotValtozas(Allapot.CSUSZOS);
    }
}
