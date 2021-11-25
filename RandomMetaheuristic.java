import java.util.ArrayList;

public class RandomMetaheuristic {
    int[][] conflictMatrix;
    int[] initialSolution;
    int iteration;
    int timeslotCount;
    ArrayList<String> student;
    public double finalPenalty;

    public RandomMetaheuristic(int[][] conflictMatrix, int[] initialSolution, int iteration, int timeslotCount, ArrayList<String> student)
    {
        this.conflictMatrix = conflictMatrix;
        this.initialSolution = initialSolution;
        this.iteration = iteration;
        this.timeslotCount = timeslotCount;
        this.student = student;
    }

    public int[] optimizeTimeslot (){
        //Membuat array optimizedTimeslot yang berisi solusi awal (largest degree)
        int[] optimizedTimeslot = new int[initialSolution.length];
        for(int i = 1; i<initialSolution.length; i++){
            optimizedTimeslot[i] = initialSolution[i];
        }
        //Menghitung penalty solusi awal
        double currentPenalty = Penalty.countPenalty(student, optimizedTimeslot);
        
        int counter = 0;
        //Melakukan iterasi LLH sebanyak jumlah iterasi yang diinput
        for (int i =0; i < iteration; i++){
            counter = counter + 1;
            //Membuat array newTimeslot untuk menyimpan solusi sementara
            int[] newTimeslot = new int[optimizedTimeslot.length];
            for(int j = 1; j<optimizedTimeslot.length; j++){
                newTimeslot[j] = optimizedTimeslot[j];
            }

            //Memilih LLH secara random
            int randomLLH = 1 + (int)(Math.random() * ((5 - 1) + 1));
            LowLevelHeuristic LLHRandom = new LowLevelHeuristic(newTimeslot,timeslotCount);
            switch (randomLLH) {
                case 1:
                    newTimeslot = LLHRandom.move(); break;
                case 2:
                    newTimeslot = LLHRandom.swap(); break;
                case 3:
                    newTimeslot = LLHRandom.move2(); break;
                case 4:
                    newTimeslot = LLHRandom.swap3(); break;
                case 5:
                    newTimeslot = LLHRandom.move3(); break;
                default :
                    System.out.println(randomLLH + " is Out of Bound");
            }


            //Menghitung penalty solusi timeslot baru
            double newPenalty = Penalty.countPenalty(student, newTimeslot);

            //Mengecek apakah timeslot course yang sudah diubah secara random konflik dengan course lain pada timeslot yang sama
            if(isConflicted(newTimeslot)){
                //Menambah penalti solusi sebanyak 1000000
                newPenalty = newPenalty + 1000000;
                //System.out.println(newPenalty);
            }

            //Mengecek apakah penalti solusi baru < penalti solusi lama
            else if(newPenalty<currentPenalty){
                //Jika lebih kecil maka solusi awal diubah sesuai solusi baru
                for(int j = 1; j<newTimeslot.length; j++){
                    optimizedTimeslot[j] = newTimeslot[j];
                }
                //Penalti lama diganti berdasarkan penalti baru
                currentPenalty = newPenalty;

                System.out.println("Iteration - " + counter);

                switch (randomLLH) {
                    case 1:
                        System.out.println("Penalty baru: "+newPenalty+" -- LLH Move Used");break;
                    case 2:
                        System.out.println("Penalty baru: "+newPenalty+" -- LLH Swap Used");break;
                    case 3:
                        System.out.println("Penalty baru: "+newPenalty+" -- LLH Move-2 Used"); break;
                    case 4:
                        System.out.println("Penalty baru: "+newPenalty+" -- LLH Swap-3 Used");break;
                    case 5:
                        System.out.println("Penalty baru: "+newPenalty+" -- LLH Move-3 Used");break;
                    default :
                        System.out.println(randomLLH + " is Out of Bound");
                }
                System.out.println("");
            }

            //Jika tidak konflik namun penalti baru lebih jelek maka tidak melakukan apa-apa
            else{
                //System.out.println(newPenalty);
                //System.out.println("New Penalty is worse");
            }
        }

        System.out.println("Timeslot Baru: ");
        for (int i = 1; i < optimizedTimeslot.length; i++){
            System.out.println(i + " " + optimizedTimeslot[i]);
        }

        System.out.println("Penalti baru = "+currentPenalty);
        finalPenalty = currentPenalty;

        return optimizedTimeslot;
    }

    public boolean isConflicted(int[] timeslot){
        for(int i = 1; i<timeslot.length; i++){
            for(int j=i; j<timeslot.length; j++){
                //System.out.println("Course " + i + " timeslot= "+ this.timeslot[i] + " dan " + j + " timeslot= "+ this.timeslot[j]);
                if(timeslot[i] == timeslot[j] && i!=j){
                    int course1 = i;
                    int course2 = j;
                    if(this.conflictMatrix[course1][course2]==1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
