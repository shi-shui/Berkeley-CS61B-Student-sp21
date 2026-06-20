package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        LinkedListDeque<Integer> M = new LinkedListDeque<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            switch (operationNumber) {
                case 0:
                    int randVal = StdRandom.uniform(0, 100);
                    L.addLast(randVal);
                    M.addLast(randVal);
                    System.out.println("addLast(" + randVal + ")");
                    break;
                case 1:
                    if (L.size() <= 0 || M.size() <= 0 || i > L.size() || i > M.size()) {
                        break;
                    }
                    int L_get_num = L.get(i);
                    int M_get_num = M.get(i);
                    System.out.println("L_getLast(" + L_get_num + ")");
                    System.out.println("M_getLast(" + M_get_num + ")");
                    assertEquals(L_get_num, M_get_num);
                    break;
                case 2:
                    if (L.size() <= 0 || M.size() <= 0) {
                        break;
                    }
                    int L_remove_num = L.removeLast();
                    int M_remove_num = M.removeLast();
                    System.out.println("L_removeLast(" + L_remove_num + ")");
                    System.out.println("M_removeLast(" + M_remove_num + ")");
                    assertEquals(L_remove_num, M_remove_num);
                    break;
                case 3:
                    int L_size = L.size();
                    int M_size = M.size();
                    System.out.println("L_size: " + L_size);
                    System.out.println("M_size: " + M_size);
                    assertEquals(L_size, M_size);
                case 4:
                    int randValf = StdRandom.uniform(0, 100);
                    L.addFirst(randValf);
                    M.addFirst(randValf);
                    System.out.println("addFirst(" + randValf + ")");
                    break;
                case 5:
                    if (L.size() <= 0 || M.size() <= 0) {
                        break;
                    }
                    int LF_remove_num = L.removeFirst();
                    int MF_remove_num = M.removeFirst();
                    System.out.println("L_removeFirst(" + LF_remove_num + ")");
                    System.out.println("M_removeFirst(" + MF_remove_num + ")");
                    assertEquals(LF_remove_num, MF_remove_num);
                    break;

            }
        }
    }
}
