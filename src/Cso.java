import java.util.ArrayList;
import java.util.List;

/**
 * A játékban levo Csöveket reprezentáló osztály.
 */
public class Cso extends Mezo{
    /**
     * A szomszédos csúcsok listája
     */
    private List<Csucs> szomszedosCsucs;

    public Cso(){
        super();
        szomszedosCsucs = new ArrayList<>();
	

    }

}
