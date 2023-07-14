package com.bp.banking.c4.model;

public enum SoftwareSystemName {
    BANKING_CORE_SYSTEM ("Banking Core System", "Banking Core System"),
    USER_INFO_SYSTEM ("User Information System", "User Information System" ),
    NOTIFICATIONS_SYSTEM ("Notifications System", "Notifications System"),
    INTERNET_BANKING_SYSTEM ("Internet Banking System", "Internet Banking System"),
    AUTH_SYSTEM ("OAuth2 Authentication Provider", "OAuth2 Authentication Provider"),
    ONBOARDING_SYSTEM ("Onboarding Provider", "Onboarding Provider");

    private String name;
    private String description;

    SoftwareSystemName(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }


}
