import java.util.ArrayList;

public class Display {

    public static void moiveTitle(ArrayList<String> list){
        for(String title : list){
            System.out.println("==>" + title);
        }
        System.out.println("");
    }

    public static void allMoive(ArrayList<String> list){
        //String movieID,title;
        System.out.println("|\tID\t| Title");
        System.out.println("-------------------------------------------------");
        for(int i = 0; i < list.size() ; i+=2){
            //movieID = list.get(i);
            //title = list.get(i*2);
            System.out.println("|\t" + list.get(i) +"\t| " +list.get(i+1));
        }
        System.out.println("");
    }
}
