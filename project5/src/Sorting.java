import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Sorting {

	// Bucket Sort Implementation
	public List<Integer> bucketSort(Integer[] arrayList) {

		List<Integer> newArrayList = new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();

		int maxVal = findMaxValue(arrayList);
		// Initializing Array List
		for (int i = 0; i <= maxVal; i++) {
			newArrayList.add(i, 0);
		}
		// Setting new values in array list
		// incrementing count on finding same value
		for (Integer x : arrayList) {
			newArrayList.set(x, (newArrayList.get(x) + 1));
		}

		// Reading data from Array List

		for (int j = 0; j <= maxVal; j++) {
			for (int k = 0; k < newArrayList.get(j); k++) {
				list.add(j);
			}
		}
		return list;
	}

	// Radix Sort Implementation
	public List<Integer>[] radixSort(Integer[] arr) {
		int maxVal = findMaxValue(arr);
		List<Integer> arrayList = Arrays.asList(arr);
		ArrayList<Integer>[] list = sort(arrayList, 10);
		return sortedArray(maxVal, list, 100);
	}

	private ArrayList<Integer>[] sort(List<Integer> arrayList, int radix) {

		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] list = new ArrayList[10];
		int n = arrayList.size();

		int i = 0;
		while (i != n) {
			int j = arrayList.get(i) % radix;
			if (list[j] == null) {
				list[j] = new ArrayList<Integer>();
			}
			list[j].add(arrayList.get(i));
			i++;
		}
		return list;
	}

	private List<Integer>[] sortedArray(int maxVal, List<Integer>[] list,
			int radix) {

		if (maxVal * 10 / radix != 0) {

			@SuppressWarnings("unchecked")
			ArrayList<Integer>[] arrayList = new ArrayList[10];
			for (int i = 0; i < 10; i++) {
				if (list[i] != null) {
					for (int z = 0; z < list[i].size(); z++) {
						int value = list[i].get(z);
						int radix1 = radix / 10;
						int j = (value % radix) / radix1;
						if (arrayList[j] == null) {
							arrayList[j] = new ArrayList<Integer>();
						}
						arrayList[j].add(value);
					}
				}
			}
			return sortedArray(maxVal, arrayList, radix * 10);
		} else {

			return list;
		}

	}

	// Function to find max value in an array
	private int findMaxValue(Integer[] a) {
		int i = 0;
		int m = a[0];
		int n = a.length;
		while (i != n) {

			if (a[i] > m)
				m = a[i];
			i++;
		}
		return m;
	}

	// Main Function
	public static void main(String[] args) {

		long startTime;
		long stopTime;
		long elapsedTime;
		int min = 0;
		int max = 1000;
		int size;

		Sorting bs = new Sorting();
		Sorting rs = new Sorting();
		Integer arr[] = { 11, 3, 4, 23, 54, 6677, 67543, 4, 1, 4, 6, 8, 4, 1, 5 };

		/* Sorting array using Bucket Sort */
		List<Integer> list = bs.bucketSort(arr);
		System.out.println(list);

		/* Sorting array using Radix Sort */
		List<Integer>[] newList = rs.radixSort(arr);
		List<Integer> sorted = new ArrayList<Integer>();
		for (List<Integer> li : newList) {
			if (li != null)
				sorted.addAll(li);
		}
		System.out.println(sorted);

		/* calculating time taken for size = 100000 */
		Random random = new Random();
		size = 100000;
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			int n = random.nextInt((max - min) + 1) + min;
			if (n == min)
				random = new Random();
			array[i] = n;
		}

		System.out.println("Bucket Sort : size = " + size);
		startTime = System.currentTimeMillis();
		bs.bucketSort(array);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

		startTime = System.currentTimeMillis();
		System.out.println("Radix Sort : size = " + size);
		rs.radixSort(array);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

		/* calculating time taken for size = 200000 */
		size = 200000;
		Integer[] array1 = new Integer[size];
		for (int i = 0; i < size; i++) {
			int n = random.nextInt((max - min) + 1) + min;
			if (n == min)
				random = new Random();
			array1[i] = n;
		}

		System.out.println("Bucket Sort: size = " + size);
		startTime = System.currentTimeMillis();
		bs.bucketSort(array1);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

		startTime = System.currentTimeMillis();
		System.out.println("Radix Sort : size = " + size);
		rs.radixSort(array1);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

		/* calculating time taken for size = 1000000 */
		size = 1000000;
		Integer[] array2 = new Integer[size];
		for (int i = 0; i < size; i++) {
			int n = random.nextInt((max - min) + 1) + min;
			if (n == min)
				random = new Random();
			array2[i] = n;
		}
		System.out.println("Bucket Sort : size = " + size);
		startTime = System.currentTimeMillis();
		bs.bucketSort(array2);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

		startTime = System.currentTimeMillis();
		System.out.println("Radix Sort : size = " + size);
		rs.radixSort(array2);
		stopTime = System.currentTimeMillis();
		elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + "ms");

	}
}
