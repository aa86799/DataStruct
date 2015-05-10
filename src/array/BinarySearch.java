package array;

import java.util.Arrays;

/*
 * 实现的不好，见递归里的二分查找法
 */
public class BinarySearch {

	public static void main(String[] args) {
//		Arrays.binarySearch(a, key);  //java系统的二分查找工具方法
		
		int key = 5;
		int len = 10;
		int[] array = new int[len];
		for (int i = 0; i < len; i++) {
			array[i] = i;
		}
		int index = half2Find(array, key);
		System.out.println("查找到的索引为：" + index);
		
		delete(array, key);
	}
	/**
	 * 二分查找
	 * @param array 需要为升序,若为降序，则内部判断需要置换一下
	 * @param key	查找的值
	 * @return
	 */
	public static int half2Find(int[] array, int key) {
		int start = 0;//开始索引
		int end = array.length - 1;//结束索引
		int mid = -1; //二分的中间点
		int count = 0;
		while (start <= end) {
			mid = (start + end) >>> 1; //开始索引和结束索引的中间值
			System.out.println("打印 看循环的次数" + (++count));
			if (key > array[mid]) {
				start = mid + 1;
			} else if (key < array[mid]) {
				end = mid - 1;
			} else {
				return mid;
			}
		}
		return mid;
	}
	
	static void delete(int[] array, int key) {
		int find = half2Find(array, key);// 若是无序数组，那只能线性查找了，逐一比较
		int len = array.length;
		for (int i = find; i < len - 1; i++) {
			array[i] = array[i + 1];
		}
		int[] copyOf = Arrays.copyOf(array, len - 1);
		for (Integer i : copyOf) {
			System.out.println(i);
		}
	}
}
