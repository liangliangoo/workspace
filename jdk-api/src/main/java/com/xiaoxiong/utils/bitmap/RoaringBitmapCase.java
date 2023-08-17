package com.xiaoxiong.utils.bitmap;

import com.alibaba.fastjson.JSON;
import org.roaringbitmap.RoaringBitmap;

/**
 * @Author 六月
 * @Date 2023/8/17 13:51
 * @Version 1.0
 */
public class RoaringBitmapCase {

    public static void main(String[] args) {
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1,2,3,1000);
        RoaringBitmap rr2 = new RoaringBitmap();
        rr2.add(4000L,4255L);
        rr.select(3); // would return the third value or 1000
        rr.rank(2); // would return the rank of 2, which is index 1
        rr.contains(1000); // will return true
        rr.contains(7); // will return false

        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);// new bitmap
        rr.or(rr2); //in-place computation
        boolean equals = rror.equals(rr);// true
        if(!equals) throw new RuntimeException("bug");
        // number of values stored?
        long cardinality = rr.getLongCardinality();
        System.out.println(cardinality);
        // a "forEach" is faster than this loop, but a loop is possible:
        for(int i : rr) {
            System.out.println(i);
        }
        System.out.println(JSON.toJSONString(rror));
        String json = "{\"batchIterator\":{},\"cardinality\":259,\"containerPointer\":{\"bitmapContainer\":false,\"cardinality\":259,\"container\":{\"arraySizeInBytes\":14,\"batchIterator\":{},\"cardinality\":259,\"charIterator\":{},\"charRankIterator\":{},\"containerName\":\"run\",\"empty\":false,\"full\":false,\"reverseCharIterator\":{},\"sizeInBytes\":16},\"runContainer\":true},\"empty\":false,\"intIterator\":{},\"longCardinality\":259,\"longSizeInBytes\":26,\"reverseIntIterator\":{},\"sizeInBytes\":26}\n";
    }

}
