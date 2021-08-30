package stuct.segmenttree;


/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/12 19 55
 */
public class SegmentTree<E> {

    public static void main(String[] args) {
        Integer[] arr = new Integer[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        SegmentTree<Integer> tree = new SegmentTree<>(arr, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
//                return a > b ? a : b;
//                return a < b ? a : b;
                return a + b; //
            }
        });
        System.out.println(tree);

        System.out.println(tree.query(1, 6));

    }

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        this.tree = (E[]) new Object[4 * arr.length];
        this.merger = merger;

        buildSegmentTree(0, 0, arr.length - 1);
    }

    //创建线段的区间[l...r] 的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2; //本来可以用 (l+r)/2 但为了防止 int 范围边界溢出；用 l+(r-l)/2
        /*
         *   当前 treeIndex  表示 l..r 的区间
         *   其左子，表示： l..mid;  右子：mid+1..r
         */
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);

        /*
         * 利用递归， 在底层对 tree[treeIndex从大到小] 赋值为 data[l]
         * 递归后，再左右孩子 合并 merge() 保存
         *
         */
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }

        return data[index];
    }

    //index位置的元素的左子索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    //index位置的元素的右子索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    //查询一段区间，所表示的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length
                || queryR < 0 || queryR >= data.length || queryR < queryL) {
            throw new IllegalArgumentException("index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在线段树中[l,r]的范围里，查询 [queryL, queryR]的区间值
     * @param treeIndex 树中索引
     * @param l  左边界
     * @param r  右边界
     * @param queryL  要查询的左边界
     * @param queryR  要查询的右边界
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {//要查询区间在右孩子中
            return query(rightChildIndex, mid + 1, r, queryL, queryR);

        } else if (queryR <= mid) {//要查询的区间在左孩子中
            return query(leftChildIndex, l, mid, queryL, queryR);

        } else {//要查询的区间既在左孩子中，也有部分在右孩子中
            //
            E leftE = query(leftChildIndex, l, mid, queryL, mid);
            //
            E rightE = query(rightChildIndex, mid + 1, r, mid + 1, queryR);

            return merger.merge(leftE, rightE);
        }

    }

    public void update(int index, int val) {
        if(index < 0 || index >=data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        update(0, 0, data.length - 1, index, val);
    }

    private void update(int treeIndex, int l, int r, int index, int val) {
        if (l == r) {
            tree[treeIndex] = data[r];
            return;
        }
        int mid = l + (r - l) / 2;
//        int leftChildIndex = leftChild(treeIndex);
//        int rightChildIndex = rightChild(treeIndex);
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        if (index >= mid + 1) {//区间在右孩子中
            update(rightChildIndex, mid + 1, r, index, val);
        } else if (index <= mid) {
            update(leftChildIndex, l, mid, index, val);
        }

        //更新当前，是左右子合并的结果
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
//        for (int i = 0; i < data.length; i++) {
//            builder.append(data[i]).append("-").append(tree[i]).append("\n");
//        }
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                builder.append(tree[i]);
            } else {
                builder.append("null");
            }
            if (i != tree.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
//                        0(45)
//        1(10)                       2(35)
//    3(3)       4(7)               5(18)       6(17)
//  7(1)  8(2)     9(3)

}
