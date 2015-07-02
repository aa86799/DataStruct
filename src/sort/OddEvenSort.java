package sort;

import java.util.Random;

import utils.Util;

/**
 * 奇偶排序 
 *  第一轮扫描选择所有的奇数据项对，与相邻偶数比较，a[j]和a[j+1]，j是奇数(j=1,3,5...), j<N-1
 *  第二轮扫描选择所有的偶数据项对，与相邻奇数比较，a[j]和a[j+1]，j是偶数(j=0,2,4...), j<N-1
 *  如此循环
 * @author stone
 *
 */
public class OddEvenSort {
	
public static void main(String[] args) {
		
		int[] ary = Util.generateIntArray(10);
		Util.printArray(ary);
		oddEvenSortAsc(ary);
		Util.printArray(ary);
	}
	
	static void oddEvenSortAsc(int[] ary) {
		//奇偶排序
		
		boolean flag = true;
		while (flag) {
			boolean odd = false, even = false;
			for (int i = 0; i < ary.length - 1; i+=2) {
				if (ary[i] > ary[i + 1]) {
					ary[i] = ary[i + 1] + 0 * (ary[i + 1] = ary[i]);
					odd = true;
				} 
			}
			for (int i = 1; i < ary.length - 1; i+=2) {
				if (ary[i] > ary[i + 1]) {
					ary[i] = ary[i + 1] + 0 * (ary[i + 1] = ary[i]);
					even = true;
				} 
			}
			System.out.println(odd + "," + even);
			flag = odd && even; //若为false，表示不论奇偶序列，一个符合条件的比较都没有
		}
	}
		
}
