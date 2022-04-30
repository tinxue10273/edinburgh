package dataStructures;

import java.util.BitSet;

/**
 * @ClassName MyBloomFilter
 * @Description 自己实现的布隆过滤器
 * @Author cxc
 * @Date 2020/10/6 15:57
 * @Verseion 1.0
 **/
public class BloomFilter {

    /**
     * 标记位数组的大小
     **/
    private static final int DEFAULT_SIZE = 2 << 24;

    /**
     * 根据这个数组来创建多个hash函数
     **/
    private static final int[] SEEDS = new int[]{3, 13, 46, 71, 91, 134};

    /**
     * 标记位数组
     **/
    private BitSet bitSet = new BitSet(DEFAULT_SIZE);

    /**
     * hash函数数组
     **/
    private SimpleHash[] simpleHashes = new SimpleHash[SEEDS.length];

    /**
     * @Description: 构造器
     * @Author: caixucheng
     * @Date: 2020/10/6 16:22
     * @version: v1.0.0
     **/
    public BloomFilter() {
        // 初始化hash数组
        for (int i = 0; i < SEEDS.length; ++i) {
            simpleHashes[i] = new SimpleHash(SEEDS.length, SEEDS[i]);
        }
    }


    /**
     * @param value: 元素
     * @Description: 将一个元素添加到布隆过滤器中
     * @Author: caixucheng
     * @Date: 2020/10/6 16:05
     * @version: v1.0.0
     **/
    public void add(Object value) {
        // 分别计算value的hash值然后将对应的位置true
        for (SimpleHash s : simpleHashes) {
            bitSet.set(s.hash(value), true);
        }
    }

    /**
     * @param value: 元素
     * @Description: 判断一个元素是否在布隆过滤器中
     * @Author: caixucheng
     * @Date: 2020/10/6 16:27
     * @return: boolean 存在则返回true，否则返回false
     * @version: v1.0.0
     **/
    public boolean isContain(Object value) {
        boolean res = true;
        // 计算全部的hash值，看看是否全为true
        for (SimpleHash s : simpleHashes) {
            res &= bitSet.get(s.hash(value));
        }
        return res;
    }

    /**
     * @Description: 静态内部类，用于实现hash
     * @Author: caixucheng
     * @Date: 2020/10/6 15:58
     * @version: v1.0.0
     **/
    public static class SimpleHash {

        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        /**
         * @param value: 需要进行hash的值
         * @Description: hash函数
         * @Author: caixucheng
         * @Date: 2020/10/6 16:00
         * @return: int 返回hash计算出来的值
         * @version: v1.0.0
         **/
        public int hash(Object value) {
            int h;
            return (value == null) ? 0 : Math.abs(seed * (cap - 1) & ((h = value.hashCode()) ^ (h >>> 16)));
        }

    }
}