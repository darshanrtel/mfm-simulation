/**
 * 
 */
package org.ieee.iwson2.mfm.algorithm;

/**
 * @author Darshan
 */
public class TimerThread extends Thread {

    private static final int FACTOR = 1000;
    private final int delay;
    private boolean myIsStopped;

    public TimerThread(final int size) {
        this.delay = size * FACTOR;
        setDaemon(true);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        myIsStopped = true;
    }

    public boolean isStopped() {
        return myIsStopped;
    }

}
