import java.util.Scanner;

public class Main {
    public static int MAX_TIME_MS = 7 * 86400000 + 23 * 3600000 + 59 * 60000;
    public static int MIN_TIME_MS = 0;

    public static void main(String[] args) {

        int currentMS;
        int dim;
        int[] matrixMS;
        int[] currentTripple = new int[3];
        int[][] matrixTripple;
        int[] out4 = {-1, -1, -1, -1};


        Scanner scanner = new Scanner(System.in);

        //initialize current tripple and current MS
        for (int i = 0; i < 3; i++) {
            currentTripple[i] = scanner.nextInt();
        }
        currentMS = currentTripple[0] * 86400000 + currentTripple[1] * 3600000 + currentTripple[2] * 60000;

        //dimension read and dependency initialize
        dim = scanner.nextInt();
        matrixMS = new int[dim];
        matrixTripple = new int[dim][3];

        //rest of reminders filling and MS for them
        for (int i = 0; i < dim; i++) {
            matrixTripple[i][0] = scanner.nextInt();
            matrixTripple[i][1] = scanner.nextInt();
            matrixTripple[i][2] = scanner.nextInt();

            if (matrixTripple[i][0] == 0) {
                if (currentTripple[0] == 7) {
                    matrixMS[i] = matrixTripple[i][1] * 3600000 + matrixTripple[i][2] * 60000;
                } else {
                    matrixMS[i] = (currentTripple[0] + 1) * 86400000 + matrixTripple[i][1] * 3600000 + matrixTripple[i][2] * 60000;
                }
            } else {
                matrixMS[i] = matrixTripple[i][0] * 86400000 + matrixTripple[i][1] * 3600000 + matrixTripple[i][2] * 60000;
            }
        }

        //check is there any between current and max or equal to current
        for (int i = 0; i < matrixMS.length; i++) {

            if (matrixMS[i] == currentMS) {
                System.out.println(matrixTripple[i][0] + " " + matrixTripple[i][1] + " " + matrixTripple[i][2]);
                System.exit(0);
            }

            if ((matrixMS[i] > currentMS) & (matrixMS[i] <= MAX_TIME_MS)) {
                if (out4[0] == -1) {
                    out4[0] = matrixTripple[i][0];
                    out4[1] = matrixTripple[i][1];
                    out4[2] = matrixTripple[i][2];
                    out4[3] = matrixMS[i];
                } else {
                    if (matrixMS[i] < out4[3]) {
                        out4[0] = matrixTripple[i][0];
                        out4[1] = matrixTripple[i][1];
                        out4[2] = matrixTripple[i][2];
                        out4[3] = matrixMS[i];
                    }
                }
            }

        }

        //if previous step gave not empty array, then out4 output needed
        if (out4[0] != -1) {
            System.out.println(out4[0] + " " + out4[1] + " " + out4[2]);
            System.exit(0);
        }

        //if reached, then all events betwween 0 and current
        for (int i = 0; i < matrixMS.length; i++) {

            if ((matrixMS[i] >= MIN_TIME_MS) & (matrixMS[i] < currentMS)) {
                if (out4[0] == -1) {
                    out4[0] = matrixTripple[i][0];
                    out4[1] = matrixTripple[i][1];
                    out4[2] = matrixTripple[i][2];
                    out4[3] = matrixMS[i];
                } else {
                    if (matrixMS[i] < out4[3]) {
                        out4[0] = matrixTripple[i][0];
                        out4[1] = matrixTripple[i][1];
                        out4[2] = matrixTripple[i][2];
                        out4[3] = matrixMS[i];
                    }
                }
            }

        }

        System.out.println(out4[0] + " " + out4[1] + " " + out4[2]);
        scanner.close();

    }
}