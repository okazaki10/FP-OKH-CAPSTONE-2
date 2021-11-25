import java.util.ArrayList;
import java.util.Arrays;

public class Penalty
{
    public static double countPenalty(ArrayList<String> student, int[] timeslot){
        //System.out.println(Arrays.toString(timeslot));
        //System.out.println(timeslot.length);
        double penalty = 0;

        //membaca course yang diambil tiap student
        for (String s : student) {
            String[] courseTaken = s.split(" ");
           
            if (courseTaken.length > 1) {
                for (int i = 0; i < courseTaken.length; i++) {
                    for (int j = i+1; j < courseTaken.length; j++) {
                        if (i != j) {
                            int timeslotCourse1 = timeslot[Integer.parseInt(courseTaken[i])-1];
                            int timeslotCourse2 = timeslot[Integer.parseInt(courseTaken[j])-1];
                            //menghitung jarak timeslot antara course 1 dan course 2 yang diambil oleh student
                            int jarak = Math.abs(timeslotCourse1 - timeslotCourse2);
                            //apabila jarak lebih dari 4 maka dianggap tidak ada penalti
                            if(jarak < 5){
                                penalty= penalty+(Math.pow(2,(4-jarak)));
                            }
                        }
                    }
                }
            }
        }
        return (penalty/student.size());
    }
}