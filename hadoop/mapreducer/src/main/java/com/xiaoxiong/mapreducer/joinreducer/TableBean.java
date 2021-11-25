package com.xiaoxiong.mapreducer.joinreducer;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class TableBean implements Writable {

    private String id; // 订单id
    private String pid; // 商品id
    private int amount; // 商品数量
    private String pname;// 商品名称
    private String flag; // 标记是什么表 order pd

    // 空参构造
    public TableBean() {
    }


    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(pid);
        out.writeInt(amount);
        out.writeUTF(pname);
        out.writeUTF(flag);
    }

    @Override
    public void readFields(DataInput in) throws IOException {

        this.id = in.readUTF();
        this.pid = in.readUTF();
        this.amount = in.readInt();
        this.pname = in.readUTF();
        this.flag = in.readUTF();
    }

    @Override
    public String toString() {
        // id	pname	amount
        return  id + "\t" +  pname + "\t" + amount ;
    }
}
