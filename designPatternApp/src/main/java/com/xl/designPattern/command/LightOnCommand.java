package com.xl.designPattern.command;

/**
 * @author liangliang
 * @date 2021/3/6 14:37
 * @e-mail 1640432919@qq.com
 */
public class LightOnCommand implements Command {

    LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}
