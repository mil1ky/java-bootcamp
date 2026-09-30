package com.northstar.crm.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Lab-only readiness toggle — NOT for production.
 * When down, readiness should be OUT_OF_SERVICE while liveness stays UP.
 */
//@Component
//public class CrmReadinessIndicator implements HealthIndicator {
//    private volatile boolean ready = true;
//
//    public void setReady(boolean ready) {
//        this.ready = ready;
//    }
//
//    public boolean isReady() {
//        return ready;
//    }
@Component
public class CrmReadinessIndicator implements HealthIndicator {

    private final AtomicBoolean ready = new AtomicBoolean(true);

    public void setReady(boolean value) {
        ready.set(value);
    }
//    @Override
//    public Health health() {
//        // Ready → UP; lab toggle off → OUT_OF_SERVICE (already correct — confirm in ActuatorIT)
//        return ready ? Health.up().build() : Health.outOfService().withDetail("reason", "lab-toggle").build();
//    }
    @Override
    public Health health() {
        if (!ready.get()) {
            return Health.outOfService()
                    .withDetail("crm", "not-ready")
                    .withDetail("reason", "dependency-unavailable")
                    .build();
        }

        return Health.up()
                .withDetail("crm", "ready")
                .build();
    }
}
