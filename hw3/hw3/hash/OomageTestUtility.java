package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /** Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (Oomage s : oomages) {
            int bucketNum = (s.hashCode() & 0x7FFFFFFF) % M;
            int count = hashMap.getOrDefault(bucketNum, 0);
            hashMap.put(bucketNum, count + 1);
        }

        int lowerBound = oomages.size() / 50;
        double upperBound = oomages.size() / 2.5;
        for (Integer key : hashMap.keySet()) {
            int value = hashMap.get(key);
            if (value < lowerBound || value > upperBound) {
                return false;
            }
        }
        return true;
    }
}
