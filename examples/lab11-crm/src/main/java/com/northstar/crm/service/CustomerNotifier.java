package com.northstar.crm.service;

import com.northstar.crm.entity.CustomerStatus;

/**
 * Notifier callback for customer status changes.
 */
public interface CustomerNotifier {
    void notifyStatusChange(String customerId, CustomerStatus oldStatus, CustomerStatus newStatus);
}
