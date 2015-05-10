package sort;

import java.util.Random;

/**
 * 选择排序
 * 比较次数 O(N^2),  交换O(N)
 * @author stone
 *
 */
public class SelectionSort {
	public static void main(String[] args) {
		int len = 15;
		int[] ary = new int[len];
		Random random = new Random();
		for (int j = 0; j < len; j++) {
			ary[j] = random.nextInt(1000);
		}
		System.out.println("-------排序前------");
//		ary=new int[]{10,9,8,7,6,5,4,3,2,1}; //测试交换次数
//		ary=new int[]{1,2,3,4,5,6,7,8,10,9}; //测试交换次数
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
		
		selectDesc(ary);
		selectAsc(ary);
	}
	/*
	 * 选择排序：降序
	 */
	static void selectDesc(int[] ary) {
		int compareCount = 0;//比较次数
		int changeCount = 0;//交换次数
		int len = ary.length;
		int maxValueIndex = -1; //记录一轮比较下来的最小值索引
		for (int i = 0; i < len - 1; i++) {
			maxValueIndex = i; //从0开始
			for (int j = i + 1; j < len; j++) {
				if (ary[maxValueIndex] < ary[j]) {
					maxValueIndex = j; //记录较大的索引
					compareCount++;
				}
			}
//			System.out.println("minValueIndex==" + maxValueIndex);
			if (maxValueIndex != i) {//如果跟左边的记录索引不同，则交换
				ary[i] = ary[maxValueIndex] + (ary[maxValueIndex] = ary[i]) * 0;//一步交换
				changeCount++;
			}
		}
		
		System.out.println("\n-------降序排序后------比较次数：" + compareCount + "，交换次数" + changeCount);
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
	}
	
	/*
	 * 选择排序：升序
	 */
	static void selectAsc(int[] ary) {
		int compareCount = 0, changeCount = 0;
		int len = ary.length;
		int minIndex = -1;
		for (int i = 0; i < len - 1; i++) {
			minIndex = i;
			for (int j = i + 1; j < len; j++) {
				if (ary[minIndex] > ary[j]) {
					minIndex = j; //记录较小的索引
					compareCount++;
				}
			}
			if (minIndex != i) {//如果跟左边的记录索引不同，则交换
				ary[i] = ary[minIndex] + (ary[minIndex] = ary[i]) * 0;
				changeCount++;
			}
		}
		System.out.println("\n-------升序排序后------比较次数：" + compareCount + "，交换次数" + changeCount);
		for (int j = 0; j < ary.length; j++) {
			System.out.print(ary[j] + " ");
		}
	}
}
