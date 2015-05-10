package recursion;

import java.util.Arrays;

/*
 * 递归二分查找
 */
public class RecursionBinarySearch {
	
	static int binarySearch(int[] array, int data) {
		int start = 0;
		int end = array.length - 1;
		int mid = -1;
		while (start <= end) {
			System.out.println("查找次数");
			mid = (start + end) >>> 1;
			if (array[mid] < data) {
				start = mid + 1;
			} else if (array[mid] > data) {
				end = mid - 1;
			} else {
				return mid;
			}
			System.out.println("start=" + start+",end="+end+",mid="+mid);
		}
		return -1;
	}
	//递归二分查找
	static int binarySearch4Recursion(int[] array, int data, int start, int end) {
		int mid = -1;
		System.out.println("查找次数");
		if (start > end) {
			return mid;
		}
		mid = (start + end) >>> 1;
		if (array[mid] < data) {
			return binarySearch4Recursion(array, data, mid + 1, end);
		} else if (array[mid] > data) {
			return binarySearch4Recursion(array, data, start, mid - 1);
		} else {
			return mid;
		}
			
	}
	
	public static void main(String[] args) {
		int[] ary = new int [100];
		for (int i = 0; i < 100; i++) {
			ary[i] = i;
		}
		int key = 64;
//		System.out.println(binarySearch(ary, key));
//		System.out.println(Arrays.binarySearch(ary, 6));
		
		System.out.println(binarySearch4Recursion(ary, key, 0, ary.length -1));
	}
}
