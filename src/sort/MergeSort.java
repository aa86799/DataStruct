package sort;

import java.util.Arrays;
/**
 * 归并排序 O(N*logN)
 * 把一个数组分成两半，排序每一半，然后将两个有序的数组，用merge()归并成一个完整的有序数组
 * 
 * 利用递归，将数组递归拆分，直至最终拆分出的数组元素个数为1，假定只有1个元素的数组是有序的。
 * 
 * @author stone
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		int[] a = {1,3,5,7,9};
		int[] b = {2,7,88};
		int[] c = new int[a.length + b.length];
		merge(a, b, c);
	}
	/**
	 * 
	 * @param a 有序 
	 * @param b 有序 ab同为升序
	 * @param c 为归并出来的升序合集 length=a.length+b.length
	 */
	private static void merge(int[] a, int[] b, int[] c) {
		int indexA = 0, indexB = 0, indexC = 0;
		while (indexA < a.length && indexB < b.length) //直接a或b都循环完为止
			if (a[indexA] < b[indexB])
				c[indexC++] = a[indexA++];
			else 
				c[indexC++] = b[indexB++]; 
		while (indexA < a.length) {//b is empty， but a isn't  将余下的a元素赋给c
			c[indexC++] = a[indexA++];
		}
		while (indexB < b.length) {//a.is empty, but b isn't  将余的b元素赋给c
			c[indexC++] = b[indexB++];
		}
		System.out.println(Arrays.toString(c));
	}
	
	public static void recSort(int[] a, int low, int up) {
		
	}
}
