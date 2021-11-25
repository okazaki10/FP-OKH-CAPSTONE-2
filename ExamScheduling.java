import java.util.Arrays;

public class ExamScheduling
{
    String fileInput;
    int[][] conflictMatrix;
    int[][] courseSorted;
    int[] timeslot;
    int jumlahCourse;

    int  indexts;
    public static boolean tampil = true;

    public ExamScheduling(int[][] conflictMatrix, int[][] courseSorted)
    {
        this.conflictMatrix = conflictMatrix;
        this.courseSorted = courseSorted;
    }

    public static boolean isTimeslotAvailableSorted(int jumlahCourse, int indexts, int[][] courseSorted, int[][] conflictMatrix, int[] timeslot)
    {
        for(int i = 1; i <= courseSorted.length; i++)
        {
            if(conflictMatrix[courseSorted[jumlahCourse][0]][i] != 0 && timeslot[i] == indexts)
                return false;
        }
        return true;
    }

    public int[] scheduleByDegree()
    {
        int[] timeslot = new int[courseSorted.length+1];
        indexts = 1;

        for(int i= 1; i <= courseSorted.length; i++)
        {
            timeslot[i] = 0;
        }

        for(int i = 0; i < courseSorted.length; i++)
        {
            for (int j = 1; j <= j; j++)
            {
                if(isTimeslotAvailableSorted(i, j, courseSorted, conflictMatrix, timeslot))
                {
                    timeslot[courseSorted[i][0]] = j;
                    break;
                }
            }
        }
        if (tampil){
        System.out.println("Timeslot Scheduling: ");
        for (int i = 1; i < timeslot.length; i++)
            //System.out.println("Timeslot untuk course "+ i + " adalah timeslot " + timeslot[i]);
            System.out.println(i + " " + timeslot[i]);
        }
        this.timeslot = timeslot;
        return timeslot;
    }

    public int getTimeslot()
    {
        // sort melihat largest degree (ascending):
        int[] timeslotCount = new int[this.timeslot.length];
        for(int i=0; i<this.timeslot.length; i++){
            timeslotCount[i]= this.timeslot[i];
        }
        Arrays.sort(timeslotCount);
        return timeslotCount[timeslotCount.length-1];
    }

    public boolean isConflicted(){
        for(int i = 1; i<this.timeslot.length; i++){
            for(int j=i; j<this.timeslot.length; j++){
                //System.out.println("Course " + i + " timeslot= "+ this.timeslot[i] + " dan " + j + " timeslot= "+ this.timeslot[j]);
                if(this.timeslot[i] == this.timeslot[j] && i!=j){
                    int course1 = i;
                    int course2 = j;
                    if(this.conflictMatrix[course1][course2]==1){
                        if (tampil){
                        System.out.println("Course " + course1 + " dan " + course2 + " Konflik");
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

}