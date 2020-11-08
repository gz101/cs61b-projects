package synthesizer;

import java.util.ArrayList;

//Make sure this class is public
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // for (int i = 0; i < buffer.capacity(); i++) {
        // buffer.dequeue();
        // }

        ArrayList<Double> nums = new ArrayList<>(buffer.capacity());
        while (buffer.fillCount() < buffer.capacity()) {
            Double nextDouble = Math.random() - 0.5;
            if (!nums.contains(nextDouble)) {
                buffer.enqueue(nextDouble);
                nums.add(nextDouble);
            }
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        Double firstItem = buffer.dequeue();
        Double secondItem = buffer.peek();
        buffer.enqueue(DECAY * 0.5 * (firstItem + secondItem));
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
