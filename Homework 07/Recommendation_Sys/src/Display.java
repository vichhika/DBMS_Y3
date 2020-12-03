import java.util.ArrayList;

public class Display {
    public static void moiveTitle(ArrayList<String> list){
        for(String title : list){
            System.out.println("==>" + title);
        }
        System.out.println("");
    }
}
