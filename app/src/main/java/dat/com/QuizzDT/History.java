package dat.com.QuizzDT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class History {
    public String level = "null";
    public String topic = "null";
    public int correctCount = 0;

    public String time = "DD/MM/YYYY";

    public History() {
    }


    public static History current = new History();
    public static void setLevel(String _level){
        current.level = _level;
    }

    public static void setTopic(String _topic){current.topic = _topic;}

    public static String getTopic() {return current.topic;}
    public static String getLevel(){
        return current.level;
    }

    //luu so cau tra loi dung
    public static void increaseCorrectCount(){
        current.correctCount = current.correctCount + 1;
    }
    public static int getCorrectCount(){
        return current.correctCount;
    }


    //reset so cau tra loi dung ve 0
    public static void resetCorrectCount(){
        current.correctCount = 0;
    }

    //init date
    private final static SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
    public  String initTime(){
        return current.time = date.format(new Date());
    }

    //addHistory
    public static ArrayList<History> history_list = new ArrayList<>();
    public static void addHistory(){
        history_list.add(current);
        //add xong se tao lai 1 bien History moi
        current = new History();
    }

}
