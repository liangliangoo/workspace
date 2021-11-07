package com.xiaoxiong.mapreducer.writeable;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2021/11/7  19:22
 */
@Data
public class FlowBean implements Writable {

    private Long upFlow;
    private Long downFlow;
    private Long sumFlow;

    public FlowBean() {
    }

    public FlowBean(Long upFlow, Long downFlow, Long sumFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = sumFlow;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow     = in.readLong();
        this.downFlow   = in.readLong();
        this.sumFlow    = in.readLong();
    }

    public void setSumFlow() {
        this.sumFlow = this.upFlow + this.downFlow;
    }

}
