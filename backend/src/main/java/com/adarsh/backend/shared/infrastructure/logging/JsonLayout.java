package com.adarsh.backend.shared.infrastructure.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.LayoutBase;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class JsonLayout extends LayoutBase<ILoggingEvent> {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
            .withZone(ZoneId.of("UTC"));

    private String appName = "bibliophiles-bazaar";
    private String env = "production";

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        if (event == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // Core fields (always present)
        appendField(sb, "timestamp", dateFormatter.format(Instant.ofEpochMilli(event.getTimeStamp())), false);
        appendField(sb, "level", event.getLevel().toString(), true);
        appendField(sb, "service", appName, true);
        appendField(sb, "environment", env, true);
        appendField(sb, "class", event.getLoggerName(), true);
        
        String message = event.getFormattedMessage();
        if (message != null) {
            appendField(sb, "message", message, true);
        }

        // MDC fields
        Map<String, String> mdc = event.getMDCPropertyMap();
        if (mdc != null) {
            for (Map.Entry<String, String> entry : mdc.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                if (val != null && !val.trim().isEmpty()) {
                    if ("trace_id".equals(key)) {
                        appendField(sb, "traceId", val, true);
                    } else if ("span_id".equals(key)) {
                        appendField(sb, "spanId", val, true);
                    } else {
                        appendField(sb, key, val, true);
                    }
                }
            }
        }

        // Exception formatting
        IThrowableProxy tp = event.getThrowableProxy();
        if (tp != null) {
            String stackTrace = ThrowableProxyUtil.asString(tp);
            if (stackTrace != null && !stackTrace.trim().isEmpty()) {
                appendField(sb, "exception", stackTrace, true);
            }
        }

        sb.append("}\n");
        return sb.toString();
    }

    private void appendField(StringBuilder sb, String key, String value, boolean prependComma) {
        if (prependComma) {
            sb.append(",");
        }
        sb.append("\"").append(key).append("\":\"").append(escapeJson(value)).append("\"");
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (ch < ' ') {
                        String hex = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - hex.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(hex);
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }
}
