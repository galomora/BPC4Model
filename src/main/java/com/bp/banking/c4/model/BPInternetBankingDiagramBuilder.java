package com.bp.banking.c4.model;

import com.structurizr.Workspace;
import com.structurizr.model.*;



public class BPInternetBankingDiagramBuilder {

    public void createWorkspaceAndExportDiagrams () {
        Workspace workspace = createSystemWorkspace();
        ContainersBuilder containersBuilder = new ContainersBuilder(workspace);
        containersBuilder.createContainersInternetSystem ();
        ComponentsBuilder componentsBuilder = new ComponentsBuilder(workspace);
        componentsBuilder.createComponents ();
        ViewsBuilder viewsBuilder = new ViewsBuilder(workspace);
        viewsBuilder.createViews();
    }

    private Workspace createSystemWorkspace () {
        Workspace workspace = new Workspace("BP Internet Banking System",
                "C4 model for BP Internet Banking System");
        Model model = workspace.getModel();
        SoftwareSystem bankingCoreSystem = model.addSoftwareSystem(SoftwareSystemName.BANKING_CORE_SYSTEM.getName(),
                SoftwareSystemName.BANKING_CORE_SYSTEM.getDescription());
        SoftwareSystem userInformationSystem = model.addSoftwareSystem(SoftwareSystemName.USER_INFO_SYSTEM.getName(),
                SoftwareSystemName.USER_INFO_SYSTEM.getDescription());
        SoftwareSystem notificationsSystem = model.addSoftwareSystem(SoftwareSystemName.NOTIFICATIONS_SYSTEM.getName(),
                SoftwareSystemName.NOTIFICATIONS_SYSTEM.getDescription());
        SoftwareSystem internetBankingSystem = model.addSoftwareSystem(SoftwareSystemName.INTERNET_BANKING_SYSTEM.getName(),
                SoftwareSystemName.INTERNET_BANKING_SYSTEM.getDescription());
        SoftwareSystem authSystem = model.addSoftwareSystem(SoftwareSystemName.AUTH_SYSTEM.getName(),
                SoftwareSystemName.AUTH_SYSTEM.getDescription());
        SoftwareSystem onboardingSystem = model.addSoftwareSystem(SoftwareSystemName.ONBOARDING_SYSTEM.getName(),
                SoftwareSystemName.ONBOARDING_SYSTEM.getDescription());

        Person user = model.addPerson(PersonName.BANK_CLIENT.getName(), PersonName.BANK_CLIENT.getDescription() );
        user.uses(internetBankingSystem, "Get user info");
        user.uses(internetBankingSystem, "Get movements");
        user.uses(internetBankingSystem, "Make transfer");
        user.uses(internetBankingSystem, "Make payment");

        internetBankingSystem.uses(bankingCoreSystem, "Get user Info from");
        internetBankingSystem.uses(bankingCoreSystem, "Make transactions in");
        internetBankingSystem.uses(userInformationSystem, "Get user Info from");
        internetBankingSystem.uses(notificationsSystem, "Send notifications using");
        internetBankingSystem.uses(authSystem, "Authenticates user using");
        internetBankingSystem.uses(onboardingSystem, "Registers new users and devices using");
        notificationsSystem.delivers(user, "Delivers notifications to");

        return workspace;
    }

}
