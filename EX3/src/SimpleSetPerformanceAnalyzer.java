import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Tests of running time
 */
public class SimpleSetPerformanceAnalyzer {

	private static String[] data1, data2;
	private final static String DATA1_PATH = "ex3\\src\\data1.txt", DATA2_PATH = "ex3\\src\\data2.txt",
			TEST1_1 = "hi", TEST1_2 = "-13170890158", TEST2_1 = "23", TEST2_2 = "hi";
	private final static int WARMUP_ITER = 2000, CONTAIN_TIMES = 1000;

	public static void main(String args[]) {
		SimpleSet[] sets = initializeSets();
		if (data1 != null && data2 != null)
			for (SimpleSet set : sets)//For every set in the array
				testSet(set);
	}

	private static SimpleSet[] initializeSets() {
		// load data1 data2
		data1 = Ex3Utils.file2array(DATA1_PATH);
		data2 = Ex3Utils.file2array(DATA2_PATH);
		// create the sets we want to test
		SimpleHashSet closedHashSet = new ClosedHashSet();
		SimpleHashSet openHashSet = new OpenHashSet();
		CollectionFacadeSet treeSet = new CollectionFacadeSet(new TreeSet<>());
		CollectionFacadeSet linkedList = new CollectionFacadeSet(new LinkedList<>());
		CollectionFacadeSet hashSet = new CollectionFacadeSet(new HashSet<>());
		// put sets in array of simple sets
		SimpleSet[] sets = new SimpleSet[5];
		sets[0] = openHashSet;
		sets[1] = closedHashSet;
		sets[2] = treeSet;
		sets[3] = linkedList;
		sets[4] = hashSet;
		return sets;
	}

	private static void testSet(SimpleSet set) {
		System.out.println("Testing data1");
		testMassInsertion(set, data1);
		testContains(set, TEST1_1);
		testContains(set, TEST1_2);
		System.out.println("Testing data2");
		testMassInsertion(set, data2);
		testContains(set, TEST2_1);
		testContains(set, TEST2_2);
	}

	private static void testMassInsertion(SimpleSet set, String[] data) {
		System.out.println("Testing set: " + set.getClass());
		// warm up on a quarter of the list
		for (int j = 0; j < data.length / 8; j++)
			set.add(data[j]);
		// remove warm up items
		for (int j = 0; j < data.length / 8; j++)
			set.delete(data[j]);
		// start measuring time
		Instant before = Instant.now();
		for (int j = 0; j < data.length; j++) {
			set.add(data[j]);
			// print every 10% of items
			if (j % 10000 == 0)
				System.out.println(j / 1000 + "%");
		}
		Instant after = Instant.now();//Stop measuring
		long delta = Duration.between(before, after).toMillis();
		System.out.println("Time took to complete insertion" + " is: " + delta + " milliseconds");
	}

	private static void testContains(SimpleSet set, String value) {
		// warm up - 2000 rounds
		for (int j = 0; j < WARMUP_ITER; j++)
			set.contains(value);
		long time1 = System.nanoTime();//Measure in nanos
		for (int j = 0; j < CONTAIN_TIMES; j++)
			set.contains(value);
		long time2 = System.nanoTime();
		System.out.println("The time took to check if " + value + " is in: " + ((time2 - time1)
				/CONTAIN_TIMES) + " nanoseconds");
	}
}

