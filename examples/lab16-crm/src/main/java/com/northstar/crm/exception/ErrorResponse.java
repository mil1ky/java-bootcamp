package com.northstar.crm.exception;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;

public class ErrorResponse {
    // TODO: fields timestamp, status, error, message, correlationId, errors (always present, maybe empty)
    private final Instant timestamp;
    private final int status;
    private final String error;
    private final String msg;
    private final String correlationId;
    private final Map<String, String> errors;


    // TODO: constructor + getters
    public ErrorResponse(Instant timestamp, int status, String error, String msg, String correlationId, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.msg = msg;
        this.correlationId = correlationId;
        this.errors = (errors != null) ? errors : Map.of(); // Ensure errors is never null
    }

    // TODO: toJson() that always includes errors:{}
    // Convenience constructor when there are no field-level errors
//    public ErrorResponse(int status, String error, String message, String correlationId) {
//        this(status, error, message, correlationId, Collection.<String, String>emptyMap());
//    }

    public Instant getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return msg; }
    public String getCorrelationId() { return correlationId; }
    public Map<String, String> getErrors() { return errors; }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"timestamp\":\"").append(escape(timestamp.toString())).append("\",");
        sb.append("\"status\":").append(status).append(",");
        sb.append("\"error\":\"").append(escape(error)).append("\",");
        sb.append("\"message\":\"").append(escape(msg)).append("\",");
        sb.append("\"correlationId\":\"").append(escape(correlationId)).append("\",");
        sb.append("\"errors\":{");

        boolean first = true;
        for (Map.Entry<String, String> entry : errors.entrySet()) {
            if (!first) sb.append(",");
            sb.append("\"").append(escape(entry.getKey())).append("\":\"")
                    .append(escape(entry.getValue())).append("\"");
            first = false;
        }

        sb.append("}");
        sb.append("}");
        return sb.toString();
    }

    private String escape(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
