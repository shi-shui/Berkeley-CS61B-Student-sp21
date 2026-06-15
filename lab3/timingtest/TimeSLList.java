package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = getNs();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        for (int i = 0; i < Ns.size(); i++) {
            double time = get_time(Ns.get(i), 10000);
            times.addLast(time);
            opCounts.addLast(10000);
        }
        printTimingTable(Ns, times, opCounts);
    }

    private static AList<Integer> getNs() {
        AList<Integer> Ns = new AList<>();
        int i = 1000;
        while (i <= 128000) {
            Ns.addLast(i);
            i = i * 2;
        }
        return Ns;
    }

    private static double get_time(int n, int m) {
        SLList<Integer> num = new SLList<>();
        for (int i = 0; i < n; i++)
            num.addLast(i);
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < m; i++) {
            num.getLast();
        }
        return sw.elapsedTime();
    }

}
