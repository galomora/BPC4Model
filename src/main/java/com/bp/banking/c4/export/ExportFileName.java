package com.bp.banking.c4.export;

public enum ExportFileName {
    SYSTEM_VIEW ("system"),
    INTERNET_BANKING_CONTAINER_VIEW ("internet-banking-container"),
    TRANSFER_COMPONENT_VIEW ("transfer-component"),
    MOVEMENTS_COMPONENT_VIEW ("movements-component"),
    USER_INFO_COMPONENT_VIEW ("user-info-component"),
    NOTIFICATIONS_COMPONENT_VIEW ("notifications-component"),
    NOTIFICATIONS_SENDER_COMPONENT_VIEW ("notifications-sender-component"),
    MOBILE_APP_COMPONENT_VIEW ("mobile-app-component"),
    WEB_SPA_COMPONENT_VIEW ("web-spa-component");

    private String name;

    ExportFileName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
