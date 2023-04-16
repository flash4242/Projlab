/**
 * Kilyukasztja a csöveket. Célja, hogy minél több víz folyjon el a
 * lyukakon, a szabotőrt reprezentálja a játékban.
 */
public class Szabotor extends Jatekos {
    /**
     * Elrontja azt a csövet, amin éppen áll
     */
    public void csoKilyukasztasa(){
        Skeleton.startMethod(getClass().getName(), "csoKilyukasztasa()");
        getAktMezo().szabotorElront();
        Skeleton.endMethod();
    }
}
