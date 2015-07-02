package recursion;

import utils.Util;


/*
 * 递归二分查找  (二分查找要求序列本身已有序)
 */
public class RecursionBinarySearch {
	
	//循环
	static int binarySearch(int[] array, int key) {
		int start = 0;
		int end = array.length - 1;
		int mid = -1;
		while (start <= end) {
			mid = (start + end) >>> 1;
			if (array[mid] < key) {
				start = mid + 1;
			} else if (array[mid] > key) {
				end = mid - 1;
			} else {
				return mid;
			}
			System.out.println("start=" + start+",end="+end+",mid="+mid);
		}
		return -1;
	}
	
	//递归二分查找
	static int binarySearch4Recursion(int[] array, int key, int start, int end) {
		int mid = -1;
		if (start > end) {
			return mid;
		}
		
		System.out.println("查找次数");
		
		mid = (start + end) >>> 1;
		if (array[mid] < key) {
			return binarySearch4Recursion(array, key, mid + 1, end);
		} else if (array[mid] > key) {
			return binarySearch4Recursion(array, key, start, mid - 1);
		} else {
			return mid;
		}
			
	}
	
	
	public static void main(String[] args) {
		int[] ary = new int[10];
		for (int i = 0; i < ary.length; i++) {
			ary[i] = i+8;
		}
		Util.printArray(ary);
		
//		System.out.println(binarySearch(ary, key));
//		System.out.println(Arrays.binarySearch(ary, 6));
		
		int key = 15;
		int keyIndex = binarySearch4Recursion(ary, key, 0, ary.length -1);
		System.out.println("index=" + keyIndex);
	}
}
