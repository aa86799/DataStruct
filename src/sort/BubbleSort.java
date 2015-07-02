package sort;

import java.util.Arrays;
import java.util.Random;
/*
 * 冒泡排序
 */
public class BubbleSort {
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
		/*
		 * 升序， Asc1和Asc2优化了内部循环的比较次数，比较好
		 * 总的比较次数：
		 * 		Asc1、Asc2：(1+n-1)/2*(n-1) ==> n/2*(n-1) ==> n*(n-1)/2 ==>(n^2-n)/2 
		 * 		Asc： n^2-n
		 */
//		orderAsc(ary);
//		orderAsc2(ary);
		orderAsc1(ary);
		
		//降序，只需要把判断大小于 置换一下
		
	}
	
	static void orderAsc(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		
		int len = ary.length;
		for (int j = 0; j < len - 1; j++) {//外层固定循环次数
			for (int k = 0; k < len - 1; k++) {//内层固定循环次数
				if (ary[k] > ary[k + 1]) {
					ary[k] = ary[k + 1] + (ary[k + 1] = ary[k]) * 0;//一步交换
					changeCount++;
					/* 交换两个变量值
					 * a=a+b
					 * b=a-b
					 * a=a-b
					 */
				} 
				compareCount++;
			}
		}
		System.out.println("\n-----orderAsc升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
	}
	
	static void orderAsc1(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		for (int j = 0; j < len-1; j++) {//外层固定循环次数
			for (int k = len - 1; k > j; k--) {//内层从多到少递减比较次数
				if (ary[k] < ary[k - 1]) {
					ary[k] = ary[k - 1] + (ary[k - 1] = ary[k]) * 0;//一步交换
					changeCount++;
				} 
				compareCount++;
			}
		}
		System.out.println("\n-----orderAsc1升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
	}

	static void orderAsc2(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		for (int j = len - 1; j > 0; j--) {//外层固定循环次数
			/*
			 * k < j; 总的比较次数=(n^2-n)/2
			 */
			for (int k = 0; k < j; k++) {//内层从多到少递减比较次数
				if (ary[k] > ary[k + 1]) {
					ary[k] = ary[k + 1] + (ary[k + 1] = ary[k]) * 0;//一步交换
					changeCount++;
				}
				compareCount++;
			}
		}
		System.out.println("\n-----orderAsc2升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
	}
}
