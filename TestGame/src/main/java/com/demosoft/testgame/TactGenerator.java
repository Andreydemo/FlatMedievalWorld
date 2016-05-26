package com.demosoft.testgame;


/**
 * Created by Andrii_Korkoshko on 5/20/2016.
 */

public class TactGenerator {

    public static long tact = 50;
    public static long tactCountPerTurn = 5;


    public static TactToken getTactToken() {
        long tact = getCurrentTact();
        return new TactToken(tact, tact);
    }

    public static boolean isTheNextTact(TactToken tactToken) {
        long currentTact = System.currentTimeMillis() / tact;
        boolean result = false;
        if (!tactToken.isOutOfSync()) {
            result = currentTact - tactToken.getCurrentTact() >= tactCountPerTurn;
            if (result) {
                tactToken.setCurrentTact(currentTact);
                tactToken.setLastAttempt(currentTact);
            } else {
                tactToken.setLastAttempt(currentTact);
            }
        } else {
            result = currentTact - tactToken.getLastAttempt() >= tactCountPerTurn;
            if (result) {
                tactToken.setCurrentTact(currentTact);
                tactToken.setLastAttempt(currentTact);
            }
        }
        return result;
    }

    private static long getCurrentTact() {
        return System.currentTimeMillis() / tact;
    }


    public static class TactToken {
        public long currentTact;
        public long lastAttempt;

        public TactToken(long currentTact, long lastAttempt) {
            this.currentTact = currentTact;
            this.lastAttempt = lastAttempt;
        }

        public long getCurrentTact() {
            return currentTact;
        }

        private void setCurrentTact(long currentTact) {
            this.currentTact = currentTact;
        }

        public long getLastAttempt() {
            return lastAttempt;
        }

        public void setLastAttempt(long lastAttempt) {
            this.lastAttempt = lastAttempt;
        }

        public boolean isOutOfSync() {
            return currentTact == lastAttempt;
        }
    }

}
