import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;

public class CourseData {
    String fileInput;
    int[][] conflictMatrix;
    int jumlahCourse;
    ArrayList<String> course = new ArrayList<String>();
    ArrayList<String> student = new ArrayList<String>();
    public static boolean tampil = true;

    public CourseData(String fileInput) {
        // menbaca dan menyimpan file course ke dalam arraylist
        try {
            File crsFile = new File(fileInput + ".crs");
            Scanner crsReader = new Scanner(crsFile);
            while (crsReader.hasNextLine()) {
                String data = crsReader.nextLine();
                course.add(data);
            }
            crsReader.close();

            File stuFile = new File(fileInput + ".stu");
            Scanner stuReader = new Scanner(stuFile);
            while (stuReader.hasNextLine()) {
                String data = stuReader.nextLine();
                student.add(data);
            }
            stuReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        this.fileInput = fileInput;
    }

    public int getNumberOfCourses() {
        // menghitung banyak course
        this.jumlahCourse = course.size();
        return this.jumlahCourse;
    }

    public ArrayList<String> getStudentData() {
        return this.student;
    }

    public int[][] getConflictMatrix(){
        int[][] conflictMatrix = new int[getNumberOfCourses() + 1][getNumberOfCourses() + 1];

        // membuat conflictMatrix
        for (String s : student) {
            // membaca data course setiap student dan menyimpannya ke dalam array
            // courseTaken
            String[] courseTaken = s.split(" ");
            if (courseTaken.length > 1) {
                for (int i = 0; i < courseTaken.length; i++) {
                    for (int j = 0; j < courseTaken.length; j++) {
                        if (i != j) {
                            int course1 = Integer.parseInt(courseTaken[i]);
                            int course2 = Integer.parseInt(courseTaken[j]);
                            conflictMatrix[course1][course2] = 1;
                        }
                    }
                }
            }
        }
        this.conflictMatrix = conflictMatrix;
        return this.conflictMatrix;
    }

    public void showConflictMatrix(int matrixlength)  {

        // nunjukkin conflictMatrix:
        if (tampil) {
            System.out.println("Conflict Matrix: ");
            for (int i = 0; i < matrixlength; i++) {
                if (i == 0) {
                    System.out.print(i + "   ");
                } else if (i < 10) {
                    System.out.print(i + "  ");
                } else if (i < 100) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println("");
            for (int i = 1; i < matrixlength; i++) {
                if (i < 10) {
                    System.out.print(i + "   ");
                } else if (i < 100) {
                    System.out.print(i + "  ");
                } else {
                    System.out.print(i + " ");
                }

                for (int j = 1; j < matrixlength; j++) {
                    if (i == j && j < 100) {
                        System.out.print("x  ");
                    } else if (i == j && j >= 100) {
                        System.out.print("x   ");
                    } else if (j < 100) {
                        System.out.print(conflictMatrix[i][j] + "  ");
                    } else {
                        System.out.print(conflictMatrix[i][j] + "   ");
                    }

                }
                System.out.println();
            }
        }

        // itung density:
        double densityCounter = 0;
        for (int[] x : conflictMatrix) {
            for (int y : x) {
                if (y > 0) {
                    densityCounter = densityCounter + 1;
                }
            }
        }

        double density = densityCounter / (getNumberOfCourses() * getNumberOfCourses());
        if (tampil) {
            System.out.println("");
            System.out.println("Density= " + density);
        }

    }

    public int[][] sortByDegree() {
        // membuat tabel untuk menyimpan course&degree
        int[][] courseDegree = new int[this.jumlahCourse][2];
        int degree = 0;

        // menyimpan semua course ke kolom pertama
        for (int i = 0; i < courseDegree.length; i++) {
            courseDegree[i][0] = i + 1;
        }

        // mengecek nilai degree:
        for (int i = 1; i <= this.jumlahCourse; i++) {
            degree = 0;
            for (int j = 1; j <= this.jumlahCourse; j++) {
                if (this.conflictMatrix[i][j] > 0) {
                    degree++;
                }
                courseDegree[i - 1][1] = degree;
            }

        }

        // sort melihat largest degree (ascending):
        Arrays.sort(courseDegree, Comparator.comparingDouble(o -> o[1]));
        // reverse menjadi descending:
        Collections.reverse(Arrays.asList(courseDegree));
        if (tampil) {
            System.out.print("");
            System.out.println("Largest Degree Sorting: ");
            for (int i = 0; i <= 10; i++) {
                System.out.println("Degree dari course " + courseDegree[i][0] + " adalah " + courseDegree[i][1]);
            }
        }
        return courseDegree;
    }
}
