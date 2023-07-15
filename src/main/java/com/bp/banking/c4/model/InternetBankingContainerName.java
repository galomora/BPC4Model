package com.bp.banking.c4.model;

public enum InternetBankingContainerName {
    WEB_SPA ("Web Single Page App", "Web Single Page App: React/Angular", "React/Angular"),
    MOBILE_APP ("Mobile App", "Mobile App: Flutter/Apache Cordova/React Native",
            "Flutter/Apache Cordova/React Native"),
    AUDIT_DB ("Audit Database", "Audit Database: SQL DB", "SQL Database"),
    TOP_CLIENTS_DATABASE ("Top clients Database", "Top clients Database: High Availability Database Cluster",
            "High Availability Database Cluster"),

    API_GATEWAY ("API Gateway", "API Gateway: AWS API Gateway / Spring Cloud", "AWS API Gateway/ Spring Cloud"),
    USER_INFO_APP ("User Info App", "User Info App: Spring Boot", "Spring Boot"),
    MOVEMENTS_APP ("Movements App", "Movements App: Spring Boot", "Spring Boot"),
    TRANSFER_APP ("Transfer App", "Transfer App: Spring Boot", "Spring Boot"),
    NOTIFICATIONS_SENDER_APP ("Notifications sender App", "Queues notifications: Java, Messaging suite, Spring Boot",
            "Java, Messaging suite, Spring Boot"),
    NOTIFICATIONS_APP ("Notifications App", "Notifications Service: Spring Boot", "Spring Boot"),
    BANKING_CORE_FACADE_APP ("Banking Core Facade App", "Banking Core Facade App: Spring Boot", "Spring Boot");


    private String name;
    private String description;
    private String technology;

    InternetBankingContainerName(String name, String description, String technology) {
        this.name = name;
        this.description = description;
        this.technology = technology;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnology() {
        return technology;
    }
}
