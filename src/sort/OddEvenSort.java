package sort;

import java.util.Random;

/**
 * 奇偶排序 
 * @author stone
 *
 */
public class OddEvenSort {
	
	public static void main(String[] args) {
		int len = 10;
		Integer[] ary = new Integer[len];
		Random random = new Random();
		for (int j = 0; j < len; j++) {
			ary[j] = random.nextInt(1000);
		}
		/*
		 * 交换次数最小也是1次，最大也是(n^2-n)/2次
		 */
//		ary=new Integer[]{10,9,8,7,6,5,4,3,2,1}; //测试交换次数
//		ary=new Integer[]{1,2,3,4,5,6,7,8,10,9}; //测试交换次数
		System.out.println("-------排序前------");
		printAry(ary);
		
		long start = System.currentTimeMillis();
		orderAsc(ary);
		long end = System.currentTimeMillis();
		System.out.println("排序所用时间：" + (end - start) + "毫秒");
		printAry(ary);
		System.out.println("-----orderAsc升序排序后------比较次数：" + compareCount + ", 交换次数：" + changeCount);
	}
	static int compareCount = 0;//比较次数
	static int changeCount = 0;//交换次数
	/**
	 * 第一轮扫描选择所有的奇数据项对，与相邻偶数比较，a[j]和a[j+1]，j是奇数(j=1,3,5...), j<N-1
	 * 第二轮扫描选择所有的偶数据项对，与相邻奇数比较，a[j]和a[j+1]，j是偶数(j=0,2,4...), j<N-1
	 * @param ary
	 */
	public static <T extends Comparable<? super T>> void orderAsc(T[] ary) {
		int len = ary.length;
		boolean unsort = true, oddsort = false, evensort = false;
		while (unsort) {
			int j = 0;
			evensort = scan(ary, j, len);
			j = 1;
			oddsort = scan(ary, j, len);
			unsort = oddsort || evensort;//若为false，表示不论奇偶序列，一个符合条件的比较都没有
		}
		
	}
	
	public static <T extends Comparable<? super T>> boolean scan(T[] ary, int j, int len) {
		boolean unsort = false;
		for (; j < len - 1; j += 2) {
			if (ary[j].compareTo(ary[j + 1]) > 0) {
				T t = ary[j];
				ary[j] = ary[j + 1];
				ary[j + 1] = t;
				changeCount++;
				unsort = true;
			}
			compareCount++;
		}
		return unsort;
	}
	
	private static void printAry(Object[] ary) {
		int len = ary.length;
		for (int j = 0; j < len; j++) {
			System.out.print(ary[j] + " ");
		}
		System.out.println("");
	}
}
