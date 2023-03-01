package com.xiaoxiong.demo.trigger;

import org.quartz.*;
import org.quartz.spi.JobStore;
import org.quartz.spi.OperableTrigger;

import java.util.Date;
import java.util.TimeZone;

/**
 * @Author 六月
 * @Date 2023/3/1 17:31
 * @Version 1.0
 */
public class PrintTrigger implements CronTrigger {
    @Override
    public String getCronExpression() {
        return null;
    }

    /**
     * <p>
     * Returns the time zone for which the <code>cronExpression</code> of
     * this <code>CronTrigger</code> will be resolved.
     * </p>
     */
    @Override
    public TimeZone getTimeZone() {
        return null;
    }

    @Override
    public String getExpressionSummary() {
        return null;
    }

    @Override
    public TriggerKey getKey() {
        return null;
    }

    @Override
    public JobKey getJobKey() {
        return null;
    }

    /**
     * Return the description given to the <code>Trigger</code> instance by
     * its creator (if any).
     *
     * @return null if no description was set.
     */
    @Override
    public String getDescription() {
        return null;
    }

    /**
     * Get the name of the <code>{@link Calendar}</code> associated with this
     * Trigger.
     *
     * @return <code>null</code> if there is no associated Calendar.
     */
    @Override
    public String getCalendarName() {
        return null;
    }

    /**
     * Get the <code>JobDataMap</code> that is associated with the
     * <code>Trigger</code>.
     *
     * <p>
     * Changes made to this map during job execution are not re-persisted, and
     * in fact typically result in an <code>IllegalStateException</code>.
     * </p>
     */
    @Override
    public JobDataMap getJobDataMap() {
        return null;
    }

    /**
     * The priority of a <code>Trigger</code> acts as a tiebreaker such that if
     * two <code>Trigger</code>s have the same scheduled fire time, then the
     * one with the higher priority will get first access to a worker
     * thread.
     *
     * <p>
     * If not explicitly set, the default value is <code>5</code>.
     * </p>
     *
     * @see #DEFAULT_PRIORITY
     */
    @Override
    public int getPriority() {
        return 0;
    }

    /**
     * Used by the <code>{@link Scheduler}</code> to determine whether or not
     * it is possible for this <code>Trigger</code> to fire again.
     *
     * <p>
     * If the returned value is <code>false</code> then the <code>Scheduler</code>
     * may remove the <code>Trigger</code> from the <code>{@link JobStore}</code>.
     * </p>
     */
    @Override
    public boolean mayFireAgain() {
        return false;
    }

    /**
     * Get the time at which the <code>Trigger</code> should occur.
     */
    @Override
    public Date getStartTime() {
        return null;
    }

    /**
     * Get the time at which the <code>Trigger</code> should quit repeating -
     * regardless of any remaining repeats (based on the trigger's particular
     * repeat settings).
     *
     * @see #getFinalFireTime()
     */
    @Override
    public Date getEndTime() {
        return null;
    }

    /**
     * Returns the next time at which the <code>Trigger</code> is scheduled to fire. If
     * the trigger will not fire again, <code>null</code> will be returned.  Note that
     * the time returned can possibly be in the past, if the time that was computed
     * for the trigger to next fire has already arrived, but the scheduler has not yet
     * been able to fire the trigger (which would likely be due to lack of resources
     * e.g. threads).
     *
     * <p>The value returned is not guaranteed to be valid until after the <code>Trigger</code>
     * has been added to the scheduler.
     * </p>
     *
     * @see TriggerUtils#computeFireTimesBetween(OperableTrigger, Calendar, Date, Date)
     */
    @Override
    public Date getNextFireTime() {
        return null;
    }

    /**
     * Returns the previous time at which the <code>Trigger</code> fired.
     * If the trigger has not yet fired, <code>null</code> will be returned.
     */
    @Override
    public Date getPreviousFireTime() {
        return null;
    }

    /**
     * Returns the next time at which the <code>Trigger</code> will fire,
     * after the given time. If the trigger will not fire after the given time,
     * <code>null</code> will be returned.
     *
     * @param afterTime
     */
    @Override
    public Date getFireTimeAfter(Date afterTime) {
        return null;
    }

    /**
     * Returns the last time at which the <code>Trigger</code> will fire, if
     * the Trigger will repeat indefinitely, null will be returned.
     *
     * <p>
     * Note that the return time *may* be in the past.
     * </p>
     */
    @Override
    public Date getFinalFireTime() {
        return null;
    }

    /**
     * Get the instruction the <code>Scheduler</code> should be given for
     * handling misfire situations for this <code>Trigger</code>- the
     * concrete <code>Trigger</code> type that you are using will have
     * defined a set of additional <code>MISFIRE_INSTRUCTION_XXX</code>
     * constants that may be set as this property's value.
     *
     * <p>
     * If not explicitly set, the default value is <code>MISFIRE_INSTRUCTION_SMART_POLICY</code>.
     * </p>
     *
     * @see #MISFIRE_INSTRUCTION_SMART_POLICY
     * @see SimpleTrigger
     * @see CronTrigger
     */
    @Override
    public int getMisfireInstruction() {
        return 0;
    }

    @Override
    public TriggerBuilder<CronTrigger> getTriggerBuilder() {
        return null;
    }

    /**
     * Get a {@link ScheduleBuilder} that is configured to produce a
     * schedule identical to this trigger's schedule.
     *
     * @see #getTriggerBuilder()
     */
    @Override
    public ScheduleBuilder<? extends Trigger> getScheduleBuilder() {
        return null;
    }

    /**
     * <p>
     * Compare the next fire time of this <code>Trigger</code> to that of
     * another by comparing their keys, or in other words, sorts them
     * according to the natural (i.e. alphabetical) order of their keys.
     * </p>
     *
     * @param other
     */
    @Override
    public int compareTo(Trigger other) {
        return 0;
    }
}
