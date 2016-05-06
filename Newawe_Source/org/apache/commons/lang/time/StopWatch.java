package org.apache.commons.lang.time;

public class StopWatch {
    private static final int STATE_RUNNING = 1;
    private static final int STATE_SPLIT = 11;
    private static final int STATE_STOPPED = 2;
    private static final int STATE_SUSPENDED = 3;
    private static final int STATE_UNSPLIT = 10;
    private static final int STATE_UNSTARTED = 0;
    private int runningState;
    private int splitState;
    private long startTime;
    private long stopTime;

    public StopWatch() {
        this.runningState = 0;
        this.splitState = STATE_UNSPLIT;
        this.startTime = -1;
        this.stopTime = -1;
    }

    public void start() {
        if (this.runningState == STATE_STOPPED) {
            throw new IllegalStateException("Stopwatch must be reset before being restarted. ");
        } else if (this.runningState != 0) {
            throw new IllegalStateException("Stopwatch already started. ");
        } else {
            this.stopTime = -1;
            this.startTime = System.currentTimeMillis();
            this.runningState = STATE_RUNNING;
        }
    }

    public void stop() {
        if (this.runningState == STATE_RUNNING || this.runningState == STATE_SUSPENDED) {
            if (this.runningState == STATE_RUNNING) {
                this.stopTime = System.currentTimeMillis();
            }
            this.runningState = STATE_STOPPED;
            return;
        }
        throw new IllegalStateException("Stopwatch is not running. ");
    }

    public void reset() {
        this.runningState = 0;
        this.splitState = STATE_UNSPLIT;
        this.startTime = -1;
        this.stopTime = -1;
    }

    public void split() {
        if (this.runningState != STATE_RUNNING) {
            throw new IllegalStateException("Stopwatch is not running. ");
        }
        this.stopTime = System.currentTimeMillis();
        this.splitState = STATE_SPLIT;
    }

    public void unsplit() {
        if (this.splitState != STATE_SPLIT) {
            throw new IllegalStateException("Stopwatch has not been split. ");
        }
        this.stopTime = -1;
        this.splitState = STATE_UNSPLIT;
    }

    public void suspend() {
        if (this.runningState != STATE_RUNNING) {
            throw new IllegalStateException("Stopwatch must be running to suspend. ");
        }
        this.stopTime = System.currentTimeMillis();
        this.runningState = STATE_SUSPENDED;
    }

    public void resume() {
        if (this.runningState != STATE_SUSPENDED) {
            throw new IllegalStateException("Stopwatch must be suspended to resume. ");
        }
        this.startTime += System.currentTimeMillis() - this.stopTime;
        this.stopTime = -1;
        this.runningState = STATE_RUNNING;
    }

    public long getTime() {
        if (this.runningState == STATE_STOPPED || this.runningState == STATE_SUSPENDED) {
            return this.stopTime - this.startTime;
        }
        if (this.runningState == 0) {
            return 0;
        }
        if (this.runningState == STATE_RUNNING) {
            return System.currentTimeMillis() - this.startTime;
        }
        throw new RuntimeException("Illegal running state has occured. ");
    }

    public long getSplitTime() {
        if (this.splitState == STATE_SPLIT) {
            return this.stopTime - this.startTime;
        }
        throw new IllegalStateException("Stopwatch must be split to get the split time. ");
    }

    public long getStartTime() {
        if (this.runningState != 0) {
            return this.startTime;
        }
        throw new IllegalStateException("Stopwatch has not been started");
    }

    public String toString() {
        return DurationFormatUtils.formatDurationHMS(getTime());
    }

    public String toSplitString() {
        return DurationFormatUtils.formatDurationHMS(getSplitTime());
    }
}
