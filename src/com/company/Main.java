import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Prinel {
    static class Task {
        public static final String INPUT_FILE = "prinel.in";
        public static final String OUTPUT_FILE = "prinel.out";

        public static int[] target;

        private void readInput() {
            try {

                BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE));

                //Read n
                String line = br.readLine();
                String[] array = line.split(" ");
                int n = Integer.parseInt(array[0]);
                int k = Integer.parseInt(array[1]);

                int[] costs = new int[n];
                int[] points = new int[n];

                target = new int[1000001];

                //Read targets
                line = br.readLine();
                array = line.split(" ");

                target[1] = 0;

                for(int i = 0; i < n; i++){

                    int input = Integer.parseInt(array[i]);
                    costs[i] = getResult(input);

                    //costs[i] = 1;
                }

                int sum = 0;
                //Read points
                line = br.readLine();

                array = line.split(" ");

                for(int i = 0; i < n; i++)
                    points[i] = Integer.parseInt(array[i]);

                sum = dP(n, k, points, costs);

                //Write the result

                writeOutput(sum);

                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(long result) throws IOException {

            PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
            pw.printf("%d\n", result);
            pw.close();

        }

        private int getResult(int input){
            if(input <= 1)
                return 0;

            if(target[input] != 0){

                return target[input];
            }

            int minim = getResult(input - 1);
            for(int d = 2; d <= input / 2; d++){
                if(input % d == 0){

                    int aux = getResult(input - input/d);
                    if(minim > aux)
                        minim = aux;
                }
            }

            target[input] = 1 + minim;

            return target[input];
        }

        private int dP(int n, int c, int[] val, int[] cost){

            int[][] mat = new int[n + 1][c + 1];

            for (int r = 0; r < c + 1; r++) {
                mat[0][r] = 0;
            }
            for (int i = 0; i < n + 1; i++) {
                mat[i][0] = 0;
            }

            for (int item = 1; item <= n; item++) {
                for (int capacity = 1; capacity <= c; capacity++) {
                    int maxValWithoutCurr = mat[item - 1][capacity]; // This is guaranteed to exist
                    int maxValWithCurr = 0; // We initialize this value to 0

                    int current = cost[item - 1]; // We use item -1 to account for the extra row at the top
                    if (capacity >= current) { // We check if the knapsack can fit the current item
                        maxValWithCurr = val[item - 1]; // If so, maxValWithCurr is at least the value of the current item

                        int remainingCapacity = capacity - current; // remainingCapacity must be at least 0
                        maxValWithCurr += mat[item - 1][remainingCapacity]; // Add the maximum value obtainable with the remaining capacity
                    }

                    mat[item][capacity] = Math.max(maxValWithoutCurr, maxValWithCurr); // Pick the larger of the two
                }
            }

            //System.out.println(Arrays.deepToString(mat));

            return mat[n][c];
        }

        public void solve() {
            readInput();
        }
    }
    public static void main(String[] args) {
        new Task().solve();
    }
}