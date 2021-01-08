package com.stone.stuct.hashtable;

import com.stone.stuct.FileOperation;
import com.stone.stuct.avl.AVLTreeMap;
import com.stone.stuct.map.BSTMap;
import com.stone.stuct.redblack.RBTree;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * 哈希表，
 *      hash() 计算哈希值
 *      链地址法处理哈希冲突
 *          hash() 冲突后，就放入同一个 table[i]所表示的 结构(链表、树等)中;
 *              本例是 java的 TreeMap 中
 *      添加和删除时，需要动态的扩容/缩容
 *      对 M 值进行动态的合理的赋值。
 *
 */
public class HashTable2<K, V> {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("total size: " + words.size());

            // Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            BSTMap<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for (String word : words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();
            AVLTreeMap<String, Integer> avl = new AVLTreeMap<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");


            // Test RBTree
            startTime = System.nanoTime();
            RBTree<String, Integer> rbt = new RBTree<>();
            for (String word : words) {
                if (rbt.contains(word))
                    rbt.set(word, rbt.get(word) + 1);
                else
                    rbt.add(word, 1);
            }

            for (String word : words)
                rbt.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + " s");


            // Test HashTable
            startTime = System.nanoTime();

            HashTable2<String, Integer> ht = new HashTable2<>();
            //HashTable<String, Integer> ht = new HashTable<>(131071);
            for (String word : words) {
                if (ht.contains(word))
                    ht.set(word, ht.get(word) + 1);
                else
                    ht.add(word, 1);
            }

            for (String word : words)
                ht.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("HashTable: " + time + " s");
        }

        System.out.println();
    }


    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;//用于计算 hash 值。也是数组表的大小

    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;

   /* public HashTable2(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();
    }*/

    public HashTable2() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for(int i = 0 ; i < M ; i ++)
            hashtable[i] = new TreeMap<>();
    }

    private int hash(K key) {
        //hashcode.. % M
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);
        else {
            map.put(key, value);
            size++;

            if (size >= upperTol * M && capacityIndex + 1 < capacity.length)
                resize(capacity[++capacityIndex]);
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && capacityIndex - 1 >= 0)
                resize(capacity[--capacityIndex]);
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + " doesn't exist!");

        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];//新的数组表
        for (int i = 0; i < newM; i++)
            newHashTable[i] = new TreeMap<>();

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet())
                newHashTable[hash(key)].put(key, map.get(key));
        }

        this.hashtable = newHashTable;
    }
}
