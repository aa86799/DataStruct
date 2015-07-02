package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Util {
	
	public static void main(String[] args) {
		int[] ary = generateIntArray(5);
		printArray(ary);
	}
	
	/**
	 * 产生随机int[]  
	 * @param size  数组长度
	 * @return
	 */
	public static int[] generateIntArray(int size) {
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>();
		
		int temp;
		for (int i = 0; i < size; i++) {
			temp = random.nextInt(size);
			if (list.contains(temp)) {
				i--;
				continue;
			} 
			list.add(temp);

		}
		int[] ary = new int[size];
		for (int i = 0; i < size; i++) {
			ary[i] = list.get(i);
		}
		return ary;
	}
	
	/**
	 * 打印
	 * @param array
	 */
	public static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}
	
	/**
	 * a与b比较， a大置后，升序    a<b
	 * @param array
	 * @param aIndex
	 * @param bIndex
	 */
	public static void swapAsc(int[] array, int aIndex, int bIndex) {
		if (array[aIndex] > array[bIndex]) {
			array[aIndex] = array[bIndex] + 0 * (array[bIndex] = array[aIndex]);
		}
	}
	
	/**
	 * a与b比较， a大置后，降序   a<b
	 * @param array
	 * @param aIndex
	 * @param bIndex
	 */
	public static void swapDesc(int[] array, int aIndex, int bIndex) {
		if (array[aIndex] < array[bIndex]) {
			array[aIndex] = array[bIndex] + 0 * (array[bIndex] = array[aIndex]);
		}
	}
}
