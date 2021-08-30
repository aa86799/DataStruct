package stuct.set;

import stuct.map.BSTMap;

/**
 * desc  :
 * author: stone
 * email : aa86799@163.com
 * time  : 2019/3/10 21 42
 */
public class BSTMapSet<E extends Comparable<E>> implements ISet<E> {

    private BSTMap<E, Object> map;

    public BSTMapSet() {
        this.map = new BSTMap<>();
    }

    @Override
    public void add(E e) {
        map.add(e, null);
    }

    @Override
    public void remove(E e) {
        map.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return map.contains(e);
    }

    @Override
    public int getSize() {
        return map.getSize();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }
}
