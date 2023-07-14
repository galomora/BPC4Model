package com.bp.banking.c4.model;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;

public class ComponentsFinder {
    public static SoftwareSystem getSystemByName (Workspace workspace, SoftwareSystemName softwareSystemName) {
        return workspace.getModel().getSoftwareSystemWithName(softwareSystemName.getName());
    }

    public static Container getInternetBankingContainerByName (Workspace workspace, InternetBankingContainerName containerName) {
        SoftwareSystem internetBankingSystem = getSystemByName(workspace, SoftwareSystemName.INTERNET_BANKING_SYSTEM);
        return internetBankingSystem.getContainerWithName(containerName.getName());
    }
}
