import java.util.ArrayList;

public class LowLevelHeuristic {
    int[] initialSolution;
    int timeslotCount;

    public LowLevelHeuristic(int[] initialSolution, int timeslotCount)
    {
        this.initialSolution = initialSolution;
        this.timeslotCount = timeslotCount;
    }


    public int[] move () {
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] newTimeslot = new int[initialSolution.length];
        for (int i = 1; i < initialSolution.length; i++) {
            newTimeslot[i] = initialSolution[i];
        }
        //Mengenerate randomcourse dan randomtimeslot
        int randomCourse = (int) (Math.random() * initialSolution.length);
        int randomTimeslot = (int) (Math.random() * timeslotCount);

        //Memilih course secara random dan mengubah timeslotnya secara random
        newTimeslot[randomCourse] = randomTimeslot;

        //Return newtimeslot
        return newTimeslot;
    }

    public int[] swap () {
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] newTimeslot = new int[initialSolution.length];
        for (int i = 1; i < initialSolution.length; i++) {
            newTimeslot[i] = initialSolution[i];
        }
        //Mengenerate 2 randomcourse
        int randomCourse1 = (int) (Math.random() * initialSolution.length);
        int randomCourse2 = (int) (Math.random() * initialSolution.length);

        //Memilih course secara random dan mengubah timeslotnya secara random
        newTimeslot[randomCourse1] = initialSolution[randomCourse2];
        newTimeslot[randomCourse2] = initialSolution[randomCourse1];

        //Return newtimeslot
        return newTimeslot;
    }

    public int[] move2 () {
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] newTimeslot = new int[initialSolution.length];
        for (int i = 1; i < initialSolution.length; i++) {
            newTimeslot[i] = initialSolution[i];
        }
        //Mengenerate 2 randomcourse dan randomtimeslot
        int randomCourse1 = (int) (Math.random() * initialSolution.length);
        int randomCourse2 = (int) (Math.random() * initialSolution.length);
        int randomTimeslot1 = (int) (Math.random() * timeslotCount);
        int randomTimeslot2 = (int) (Math.random() * timeslotCount);

        //Memilih course secara random dan mengubah timeslotnya secara random
        newTimeslot[randomCourse1] = randomTimeslot1;
        newTimeslot[randomCourse2] = randomTimeslot2;

        //Return newtimeslot
        return newTimeslot;
    }

    public int[] swap3 () {
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] newTimeslot = new int[initialSolution.length];
        for (int i = 1; i < initialSolution.length; i++) {
            newTimeslot[i] = initialSolution[i];
        }
        //Mengenerate 3 randomcourse
        int randomCoursea = (int) (Math.random() * initialSolution.length);
        int randomCourseb = (int) (Math.random() * initialSolution.length);
        int randomCoursec = (int) (Math.random() * initialSolution.length);

        //Memilih course secara random dan mengubah timeslotnya secara random
        newTimeslot[randomCourseb] = initialSolution[randomCoursea];
        newTimeslot[randomCoursec] = initialSolution[randomCourseb];
        newTimeslot[randomCoursea] = initialSolution[randomCoursec];

        //Return newtimeslot
        return newTimeslot;
    }

    public int[] move3 () {
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] newTimeslot = new int[initialSolution.length];
        for (int i = 1; i < initialSolution.length; i++) {
            newTimeslot[i] = initialSolution[i];
        }
        //Mengenerate 3 randomcourse dan randomtimeslot
        int randomCourse1 = (int) (Math.random() * initialSolution.length);
        int randomCourse2 = (int) (Math.random() * initialSolution.length);
        int randomCourse3 = (int) (Math.random() * initialSolution.length);

        int randomTimeslot1 = (int) (Math.random() * timeslotCount);
        int randomTimeslot2 = (int) (Math.random() * timeslotCount);
        int randomTimeslot3 = (int) (Math.random() * timeslotCount);

        //Memilih course secara random dan mengubah timeslotnya secara random
        newTimeslot[randomCourse1] = randomTimeslot1;
        newTimeslot[randomCourse2] = randomTimeslot2;
        newTimeslot[randomCourse3] = randomTimeslot3;

        //Return newtimeslot
        return newTimeslot;
    }

}
