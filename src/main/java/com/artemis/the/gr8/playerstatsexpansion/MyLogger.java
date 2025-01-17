package com.artemis.the.gr8.playerstatsexpansion;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Logger;

public final class MyLogger {

    private final static Logger myLogger = Logger.getLogger("PlayerStatsExpansion");
    private final static ArrayList<String> loggedMessages = new ArrayList<>();
    private static Instant storedMessagesLastClearedTimestamp = Instant.now();

    public static void logInfo(String msg) {
        myLogger.info(msg);
    }

    public static void logWarning(String msg) {
        if (storedMessagesNeedClearing()) {
            clearStoredMessages();
        }
        if (!loggedMessages.contains(msg)) {
            loggedMessages.add(msg);
            myLogger.warning(msg);
        }
    }

    public static void clear() {
        loggedMessages.clear();
    }

    private static boolean storedMessagesNeedClearing() {
        return storedMessagesLastClearedTimestamp.until(Instant.now(), ChronoUnit.SECONDS) > 30;
    }

    private static void clearStoredMessages() {
        myLogger.info("clearing stored messages...");
        storedMessagesLastClearedTimestamp = Instant.now();
        clear();
    }
}