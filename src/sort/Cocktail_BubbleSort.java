package sort;

import java.util.Random;

import utils.Util;
/*
 * 冒泡排序_鸡尾酒排序	也就是双向冒泡排序
 * 	此算法与冒泡排序的不同处在于排序时是以双向在序列中进行排序
 * 效率上，O(N^2), 不比普通的冒泡快
 */
public class Cocktail_BubbleSort {
	public static void main(String[] args) {
		int[] ary = Util.generateIntArray(10);
		Util.printArray(ary);
		orderAsc(ary);
		Util.printArray(ary);
		
	}
	
	//鸡尾 双向冒泡 升序
	static void orderAsc(int[] ary) {
		int l = 0;
		int r = ary.length - 1;
		while (l < r) {
			for (int i = l; i < r; i++) {
				Util.swapAsc(ary, i, i+1);
			}
			//从左向右比一轮，升序 最大值在最后了
			r--;
			for (int i = r; i > l; i--) {
				Util.swapAsc(ary, i-1, i);
			}
			l++;
		}
	}

}
