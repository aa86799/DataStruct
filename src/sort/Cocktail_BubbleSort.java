package sort;

import java.util.Random;
/*
 * 冒泡排序_鸡尾酒排序	也就是双向冒泡排序
 * 	此算法与冒泡排序的不同处在于排序时是以双向在序列中进行排序
 * 效率上，O(N^2), 不比普通的冒泡快
 */
public class Cocktail_BubbleSort {
	public static void main(String[] args) {
		int len = 10;
		int[] ary = new int[len];
		Random random = new Random();
		for (int j = 0; j < len; j++) {
			ary[j] = random.nextInt(1000);
		}
		/*
		 * 交换次数最小也是1次，最大也是(n^2-n)/2次
		 */
//		ary=new int[]{10,9,8,7,6,5,4,3,2,1}; //测试交换次数
//		ary=new int[]{1,2,3,4,5,6,7,8,10,9}; //测试交换次数
		System.out.println("-------排序前------");
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}

		orderAsc1(ary);
//		orderAsc2(ary);
		
		//降序，只需要把判断大小于 置换一下
		
	}
	
	static void orderAsc1(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		int left = 0, right = len -1;
		while (left < right) {//外层固定循环次数
			for (int k = left; k < right; k++) {//内层从多到少递减比较次数, 从左向右
				if (ary[k] > ary[k + 1]) {//前大于后， 置换
					ary[k] = ary[k + 1] + (ary[k + 1] = ary[k]) * 0;//一步交换
					changeCount++;
				} 
				compareCount++;
			}
			right = right -1;
			for (int k = right; k > left; k--) {//内层从多到少递减比较次数, 从右向左
				if (ary[k] < ary[k - 1]) {//后小于前 置换
					ary[k] = ary[k - 1] + (ary[k - 1] = ary[k]) * 0;//一步交换
					changeCount++;
				} 
				compareCount++;
			}
			left = left + 1;
		}
		System.out.println("\n-----orderAsc1升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
	}
	
	//跟orderAsc1的思路没有区别
	static void orderAsc2(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		int l = 0, r = len -1;
		for (; l < r; ) {//外层固定循环次数
			/*
			 * 从左向右比，将大的移到后面
			 */
			for (int k = l; k < r; k++) {
				if (ary[k] > ary[k + 1]) {
					int temp = ary[k] + ary[k + 1];
					ary[k + 1] = temp - ary[k + 1];
					ary[k] = temp - ary[k + 1];
					changeCount++;
				}
				compareCount++;
			}
			r--;
			/*
			 * 从右向左比，将小的移到前面
			 */
			for (int k = r; k > l; k--) {
				if (ary[k] < ary[k - 1]) {
					int temp = ary[k] + ary[k - 1];
					ary[k - 1] = temp - ary[k - 1];
					ary[k] = temp - ary[k - 1];
					changeCount++;
				}
				compareCount++;
			}
			l++;
		}
		System.out.println("\n-----orderAsc2升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
	}
}
