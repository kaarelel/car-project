package com.carproject.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLogger {

    private static final Logger auditLogger = LoggerFactory.getLogger("AUDIT");

    private AuditLogger() {
    }

    public static void log(String message) {
        auditLogger.info(message);
    }
}
