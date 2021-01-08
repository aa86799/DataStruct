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
 *          hash() 冲突后，就放入同一个 table[i]所表示的 结构(链表、树)中;
 *              本例是 java的 TreeMap 中
 *      添加和删除时，需要动态的扩容/缩容
 *          M 值是 扩大或缩小两倍
 *
 */
public class HashTable<K, V> {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/pride-and-prejudice.txt", words)) {
            System.out.println("total size: " + words.size());

            // Collections.sort1(words);

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

            HashTable<String, Integer> ht = new HashTable<>();
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

    private static final int upperTol = 10; //上边界
    private static final int lowerTol = 2;  //下边界
    private static final int initCapacity = 7; //初始容量

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++)
            hashtable[i] = new TreeMap<>();
    }

    public HashTable() {
        this(initCapacity);
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

            if (size >= upperTol * M)
                resize(2 * M);
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && M / 2 >= initCapacity)
                resize(M / 2);
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
