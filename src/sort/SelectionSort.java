package sort;

import java.util.Random;

import utils.Util;

/**
 * 选择排序
 * 比较次数 O(N^2),  交换O(N)
 * @author stone
 *
 */
public class SelectionSort {
	public static void main(String[] args) {
		int[] ary = Util.generateIntArray(10);
		Util.printArray(ary);
//		orderDesc(ary);
		orderAsc(ary);
		Util.printArray(ary);
	}
	
	static void orderDesc(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
			int index = i;
			for (int j = i + 1; j < ary.length; j++) {
				//降序记录大的一方
				if (ary[index] < ary[j]) {
					index = j;
				}
			}
			if (i != index) {//i<=index
				Util.swapDesc(ary, i, index);
			}
		}
	}
	
	static void orderAsc(int[] ary) {
		for (int i = 0; i < ary.length; i++) {
			int index = i;
			for (int j = i + 1; j < ary.length; j++) {
				//升序记录小的一方
				if (ary[index] > ary[j]) {
					index = j;
				}
			}
			if (i != index) {//i<=index
				Util.swapAsc(ary, i, index);
			}
		}
	}
}
