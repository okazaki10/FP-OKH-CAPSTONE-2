import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.lang.reflect.Array;

public class Main {
    static String fileName[][] = { { "car-s-91", "CAR91" }, { "car-f-92", "CAR92" }, { "ear-f-83", "EAR83" },
            { "hec-s-92", "HEC92" }, { "kfu-s-93", "KFU93" }, { "lse-f-91", "LSE91" }, { "pur-s-93", "PUR93" },
            { "rye-s-93", "RYE92" }, { "sta-f-83", "STA83" }, { "tre-s-92", "TRE92" }, { "uta-s-92", "UTA92" },
            { "ute-s-92", "UTE92" }, { "yor-f-83", "YOR83" } };
    static int[][] conflictMatrix, sortedCourse, weightedClashMatrix, sortedWeightedCourse;
    static int timeslot[];

    public static void main(String[] args) throws IOException {
        System.out.println("");
        System.out.println("List Dataset: ");

        for (int i = 0; i < fileName.length; i++) {
            System.out.println(i + 1 + ". " + fileName[i][1]);
        }
        System.out.println(fileName.length + 1 + ". tampilkan semua");
        System.out.print("\nPilih dataset  : ");
        Scanner input = new Scanner(System.in);

        int dataset = input.nextInt();

        System.out.print("Jumlah iterasi : ");
        int itr = input.nextInt();
        if (dataset != 14) {
            jalankan(dataset, fileName[dataset - 1][0], fileName[dataset - 1][1], true, itr);
        } else {
            // jika input sama dengan 14 maka akan menampilkan semua dataset tanpa
            // menampilkan timeslot
            
            for (int i = 0; i < fileName.length; i++) {
                //multithreading
                Runner1 t1 = new Runner1(dataset, fileName[i][0], fileName[i][1], false, itr);
                t1.start();
                //synchronous
                //jalankan(dataset, fileName[i][0], fileName[i][1], false, itr);
            }
        }

    }

    public static void jalankan(int dataset, String filePilihanInput, String namadataset, boolean tampil, int itr)
            throws IOException {
        if (tampil) {
            System.out.println("\n================================================\n");
        }
        CourseData course = new CourseData(filePilihanInput);

        // Mendapatkan conflict_matrix:
        conflictMatrix = course.getConflictMatrix();
        course.tampil = tampil;
        course.showConflictMatrix(50);
        System.out.println(" ");

        // Mendapatkan hasil sorting largest degree:
        sortedCourse = course.sortByDegree();
        System.out.println("\n================================================\n");

        // Melakukan scheduling (Largest Degree)
        ExamScheduling sch = new ExamScheduling(conflictMatrix, sortedCourse);
        sch.tampil = tampil;
      
        timeslot = sch.scheduleByDegree();
 

        // Mengecek apakah ditemukan konflik pada schedule
        System.out.println("Dataset yang dipilih : " + namadataset);
        System.out.println("Jumlah Iterasi : " + itr);
        System.out.println("Ada konflik? : " + (sch.isConflicted() ? "Ya" : "Tidak"));

        int minimumTimeslot = sch.getTimeslot();
        System.out.println("Minimal Timeslots: " + minimumTimeslot);
        double initialPenalty = Penalty.countPenalty(course.getStudentData(), timeslot);

        // -------------------------------------Hill
        // Climbiing--------------------------------//

        HillClimbing hcl = new HillClimbing(conflictMatrix, timeslot, itr, minimumTimeslot, course.getStudentData());
        hcl.tampil = tampil;
        long startTimeHC = System.nanoTime();
        System.out.println("Optimized Timeslots with HillClimbing Algorithm : " + Integer.toString(hcl.optimizeTimeslot()));
        long endTimeHC = System.nanoTime();
        double penaltyHC = hcl.finalPenalty;
        long timeElapsedHC = endTimeHC - startTimeHC;

        double delta = ((initialPenalty - penaltyHC) / initialPenalty) * 100;

        System.out.println("Initial penalty = " + initialPenalty);
        System.out.println("Final penalty = " + penaltyHC);
        System.out.println("Delta = " + delta + "%");
        System.out.println("Hill Climbing execution time in miliseconds : " + timeElapsedHC / 1000000);
    }
}
