package stuct.map;

/**
 * desc  : 映射
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/2/18 20 12
 */
public interface IMap<K, V> {

    V put(K key, V value);

    void add(K key, V value);

    V get(K key);

    V remove(K key);

    boolean contains(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
