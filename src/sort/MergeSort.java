package sort;


import utils.Util;
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
		int[] a = {1,3,66,81,95};
		System.out.println("------排序前a------");
		Util.printArray(a);
		
		int[] b = new int[]{77,88,99};
		System.out.println("------排序前b------");
		Util.printArray(b);
		
		int[] c = new int[a.length + b.length];
		merge(a, b, c);
		System.out.println("------两个有序数组归并后c------");
		Util.printArray(c);
		System.out.println();
		
		int[] ary = Util.generateIntArray(10);
		System.out.println("------排序前ary------");
		Util.printArray(ary);
		int[] temp = new int[ary.length];
		mergeSort(ary, 0, ary.length - 1, temp);
		System.out.println("------排序前后ary------");
		Util.printArray(ary);
	}
	
	//合并两个有序a和b， 到第三方c
	private static void merge(int[] a, int[] b, int[] c) {
		int n = a.length;
		int m = b.length;
		int i,j,k;
		i = j = k = 0;
		while (i < n && j < m) {
			if (a[i] < b[j]) {
				c[k++] = a[i++]; //c[0] = a[0] ...
			} else {
				c[k++] = b[j++];
			}
//			System.out.println("i=" + i + ",j=" +j);
		} //有一个数组下标结束了
		
		while (i < n) {
			c[k++] = a[i++];
		}
		while (j < m) {
			c[k++] = b[j++];
		}
		
	}
	
	/*
	归并排序具体工作原理如下（假设序列共有n个元素）：
	将序列每相邻两个数字进行归并操作（merge)，形成floor(n/2)个序列，排序后每个序列包含两个元素
	将上述序列再次归并，形成floor(n/4)个序列，每个序列包含四个元素
	重复步骤2，直到所有元素排序完毕
 */
	private static void mergeArray(int a[], int first, int mid, int last, int temp[]) {
		int i = first;
		int j = mid + 1;  
	    int m = mid;
	    int n = last;  
	    int k = 0; //目标存储索引
	    
	    while (i <= m && j <= n) { //i左 j右
	    	if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
	    }
	    
	    while (i <= m)   {
			temp[k++] = a[i++];
		}
	    while (j <= n) {
			temp[k++] = a[j++];
	    }
	    
	    //每次合并的时候是从first到last比较的，所以用temp的first到last存这个区间的已排数据，置到a中
	    
	    for (i = 0; i < k; i++)  
	        a[first + i] = temp[i];
	}
	/*
	 * 可以将A，B组各自再分成二组。依次类推，当分出来的小组只有一个数据时，
	 * 可以认为这个小组组内已经达到了有序，然后再合并相邻的二个小组就可以了
	 */
	private static void mergeSort(int a[], int first, int last, int temp[])   {
		if (first < last) {
			int mid = (first + last) >> 1;
			mergeSort(a, first, mid, temp);//左  
			mergeSort(a, mid + 1, last, temp);//右
			mergeArray(a, first, mid, last, temp);
//			System.out.println("\n----- 一次归并 start -----");
//			Util.printArray(a);
//			System.out.println("----- 一次归并 end -----\n");
		}
	}
	
}
