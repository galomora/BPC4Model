package com.bp.banking.c4.model;

public enum PersonName {
    BANK_CLIENT ("Bank Client", "Bank Client");
    private String name;
    private String description;

    PersonName(String name, String description) {
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
