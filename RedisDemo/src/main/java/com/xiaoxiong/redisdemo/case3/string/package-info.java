/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/1/21 11:38
 *
 * redis针对string类型提供了三种数据类型分别是：
 *      REDIS_ENCODING_INT（long 类型的整数）
 *  我们根据上一节知道每个hashtable中的值作为一个指针会指向redisObject对象，该对象有一个属性是ptr（指针类型），
 *  一个指针比如在64位系统中就是用8个字节来存储，
 *  这个存储大小正好是一个长整型的存储，所以为了节省内存空间，
 *  如果一个字符串内容可转为 long，那么该字符串会被转化为 long 类型，
 *  对象 ptr 指向该 long，并将 encoding 设置为 int，这样就不需要重新开辟空间，算是长整形的一个优化
 *
 *      REDIS_ENCODING_EMBSTR  （embstr编码的简单动态字符串）
 *  如果字符串对象保存的是一个字符串值，并且这个字符串的长度小于等于 44 字节，那么字符串对象将使用 embstr 编码的方式来保存这个字符串。
 *  3.2版本之后是44个字节，之前是39个字节，这也是因为sds结构的版本变化所导致的。
 *
 *      REDIS_ENCODING_RAW （简单动态字符串）
 *  如果字符串对象保存的是一个字符串值，并且这个字符串的长度大于 32 字节，
 *  那么字符串对象将使用一个简单动态字符串（SDS）来保存这个字符串值，并将对象的编码设置为 raw。
 *
 */
package com.xiaoxiong.redisdemo.case3.string;