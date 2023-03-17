package com.xiaoxiong.demo.listener;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @Author 六月
 * @Date 2023/3/4 19:38
 * @Version 1.0
 */
public class PrintTriggerListener implements TriggerListener {

    private String name;

    public PrintTriggerListener(String name) {
        this.name = name;
    }


    /**
     * <p>
     * Get the name of the <code>TriggerListener</code>.
     * </p>
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * has fired, and it's associated <code>{@link JobDetail}</code>
     * is about to be executed.
     * </p>
     *
     * <p>
     * It is called before the <code>vetoJobExecution(..)</code> method of this
     * interface.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has fired.
     * @param context The <code>JobExecutionContext</code> that will be passed to
     *                the <code>Job</code>'s<code>execute(xx)</code> method.
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * has fired, and it's associated <code>{@link JobDetail}</code>
     * is about to be executed.  If the implementation vetos the execution (via
     * returning <code>true</code>), the job's execute method will not be called.
     * </p>
     *
     * <p>
     * It is called after the <code>triggerFired(..)</code> method of this
     * interface.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has fired.
     * @param context The <code>JobExecutionContext</code> that will be passed to
     *                the <code>Job</code>'s<code>execute(xx)</code> method.
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        String name = this.getClass().getSimpleName();
        // TriggerListener给了一个选择去否决Job的执行。假如这个方法返回true，这个Job将不会为此次Trigger触发而得到执行。
        System.out.println(name + " 没有被触发");
        return false;  // true:表示不会执行Job的方法
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * has misfired.
     * </p>
     *
     * <p>
     * Consideration should be given to how much time is spent in this method,
     * as it will affect all triggers that are misfiring.  If you have lots
     * of triggers misfiring at once, it could be an issue it this method
     * does a lot.
     * </p>
     *
     * @param trigger The <code>Trigger</code> that has misfired.
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        String name = this.getClass().getSimpleName();
        // Scheduler调用这个方法是在Trigger错过触发时
        System.out.println(name + " 错过触发");
    }

    /**
     * <p>
     * Called by the <code>{@link Scheduler}</code> when a <code>{@link Trigger}</code>
     * has fired, it's associated <code>{@link JobDetail}</code>
     * has been executed, and it's <code>triggered(xx)</code> method has been
     * called.
     * </p>
     *
     * @param trigger                The <code>Trigger</code> that was fired.
     * @param context                The <code>JobExecutionContext</code> that was passed to the
     *                               <code>Job</code>'s<code>execute(xx)</code> method.
     * @param triggerInstructionCode the result of the call on the <code>Trigger</code>'s<code>triggered(xx)</code>
     *                               method.
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        String name = this.getClass().getSimpleName();
        // Trigger被触发并且完成了Job的执行时，Scheduler调用这个方法。
        System.out.println(name + " 完成之后触发");
    }
}
