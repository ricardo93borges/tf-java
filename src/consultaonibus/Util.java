package consultaonibus;

/**
 * Created by ricardo on 14/11/15.
 */
public class Util {

    public Util(){

    }

    public static double round4decimais(double n){
        double factor = 1e6; // = 1 * 10^4 = 100000.
        double lat = Math.round(n * factor) / factor;
        return lat;
    }

    public static String substituir(String str, String oldChar, String newChar){
        if(str.contains(oldChar)){
            str = str.replace(oldChar, newChar);
        }
        return str;
    }
}
