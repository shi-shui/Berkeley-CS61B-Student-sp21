package gh2;

import deque.ArrayDeque;
import deque.Deque;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate(采样率)
    private static final double DECAY = .996; // energy decay factor(能量衰减因子)

    /* Buffer for storing sound data. (用于存储声音数据的缓冲区。)*/

    private final Deque<Double> buffer;

    /* Create a guitar string of the given frequency.(做一根指定频率的吉他弦。) */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }

    }

    /* Pluck the guitar string by replacing the buffer with white noise.
       (用白噪声替换缓冲区来弹拨吉他弦。) */
    public void pluck() {
        for (int i = 0; i < buffer.size(); i++) {
            double r = Math.random() - 0.5;
            buffer.removeLast();
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     * 通过执行一次 Karplus-Strong 算法的迭代，将模拟向前推进一个时间步。
     */
    public void tic() {
        Double x = buffer.removeFirst();
        Double y = buffer.get(0);
        Double newDouble = DECAY * (x + y) / 2;
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer.
     *  (返回缓冲区前面的双精度值。)
     */
    public double sample() {
        return buffer.get(0);
    }
}

