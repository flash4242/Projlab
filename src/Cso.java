import java.util.ArrayList;
import java.util.List;

/**
 * A játékban lévő Csöveket reprezentáló osztály.
 */
public class Cso extends Mezo{
    /**
     * A szomszédos csúcsok listája
     */
    private List<Csucs> szomszedosCsucs;

    /**
     * Létrehoz egy Csövet, és inicializálja a szomszedosCsucs listát.
     */
    public Cso(){
        super();
        szomszedosCsucs = new ArrayList<>();
    }

    /**
     * Kap egy játékost és ha üres a jatekosRajta lista és kötöttek a csővégei, akkor hozzáadja a jatekosRajta listához.
     * @param j A játékos, aki érkezik.
     * @return Igazat ad vissza ha elfogadja a játékost.
     */
    public boolean jatekostElfogad(Jatekos j){
        if(Skeleton.kerdes("Allnak az adott csovon?")){
            return false;
        } else if (Skeleton.kerdes("Szabad-e valamelyik csoveg?")) {
            return false;
        }
        else {
            jatekosRajta.add(j);
            return true;
        }
    }

    @Override
    public void targyLerakas(Pumpa p){

    }


    public boolean setCsucsToNull(Mezo m){

    }



}
