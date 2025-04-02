package com.carproject.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuditLogger {

    private static final Logger auditLog = LoggerFactory.getLogger("AUDIT");

    public static void log(String message) {
        auditLog.info(message);
    }
}
