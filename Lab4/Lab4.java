
/**
 * Lab4
 */
public class Lab4 {

    public static void main(String[] args) {
        double[] test = new double[2564];

        for(int i = 0; i < test.length; i++){
            test[i] = 25.25;
        }

        long startTime = System.currentTimeMillis(); // record start time

            /* (run the algorithm) */
        prefixAverage2(test);

        long endTime = System.currentTimeMillis(); // record end time
        long elapsed = endTime - startTime; // compute elapsed time

        System.out.println("Start time: " + startTime + "\nEnd time: " + endTime + "\nElapsed: " + elapsed);
    }

    public static double[] prefixAverage2(double[] x) {
        int n = x.length;
        double[] a = new double[n]; // filled with zeros by default
        double total = 0; // compute prefix sum as x[0] + x[1] + ...
        for (int j=0; j < n; j++) {
           total += x[j]; // update prefix sum to include x[j]
           a[j] = total / (j+1); // compute average based on current sum
        }
        return a;
     }
}