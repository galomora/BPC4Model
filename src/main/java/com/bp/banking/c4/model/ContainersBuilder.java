package com.bp.banking.c4.model;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;

public class ContainersBuilder {
    private Workspace workspace;

    public ContainersBuilder (Workspace workspace) {
        this.workspace = workspace;
    }

    public void createContainersInternetSystem () {
        SoftwareSystem internetBankingSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.INTERNET_BANKING_SYSTEM);
        SoftwareSystem authSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.AUTH_SYSTEM);
        SoftwareSystem notificationsSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.NOTIFICATIONS_SYSTEM);
        SoftwareSystem bankingCoreSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.BANKING_CORE_SYSTEM);
        SoftwareSystem userInformationSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.USER_INFO_SYSTEM);
        SoftwareSystem onboardingSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.ONBOARDING_SYSTEM);

        Person user = workspace.getModel().getPersonWithName(PersonName.BANK_CLIENT.getName());

        Container webSPAContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.WEB_SPA);
        Container mobileContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.MOBILE_APP);
        Container databaseContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.AUDIT_DB);
        Container topClientsDatabaseContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.TOP_CLIENTS_DATABASE);
        Container notificationsAppContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.NOTIFICATIONS_APP);
        Container notificationsSenderAppContainer =
                addContainerToInternetSystem(internetBankingSystem, InternetBankingContainerName.NOTIFICATIONS_SENDER_APP);
        Container APIGatewayContainer = addContainerToInternetSystem(internetBankingSystem,
                InternetBankingContainerName.API_GATEWAY);
        Container userInfoAppContainer = addContainerToInternetSystem(internetBankingSystem,
                InternetBankingContainerName.USER_INFO_APP);
        Container movementsAppContainer = addContainerToInternetSystem(internetBankingSystem,
                InternetBankingContainerName.MOVEMENTS_APP);
        Container transferAppContainer = addContainerToInternetSystem(internetBankingSystem,
                InternetBankingContainerName.TRANSFER_APP);
        Container bankingCoreFacadeAppContainer = addContainerToInternetSystem(internetBankingSystem,
                InternetBankingContainerName.BANKING_CORE_FACADE_APP);

        user.uses(webSPAContainer, "Uses the web single page app to make transactions, query movements");
        user.uses(mobileContainer, "Uses the mobile app to make transactions, query movements");
        webSPAContainer.uses(authSystem,
                "Authenticates user using Authorization Code Flow with Proof Key for Code Exchange");
        mobileContainer.uses(authSystem,
                "Authenticates user using Authorization Code Flow with Proof Key for Code Exchange");
        webSPAContainer.uses(APIGatewayContainer,
                "Make API service calls to get user information, execute transactions, query movements");
        mobileContainer.uses(APIGatewayContainer,
                "Make API service calls to get user information, execute transactions, query movements");
        mobileContainer.uses(onboardingSystem,
                "Registers new users and devices using");
        movementsAppContainer.uses(databaseContainer, "Stores transaction history ");
        transferAppContainer.uses(databaseContainer, "Stores transaction history ");
        movementsAppContainer.uses(bankingCoreFacadeAppContainer, "Query movements from ");
        transferAppContainer.uses(bankingCoreFacadeAppContainer, "Make transactions in ");
        bankingCoreFacadeAppContainer.uses(bankingCoreSystem, "Query movements from ");
        bankingCoreFacadeAppContainer.uses(bankingCoreSystem, "Make transactions in ");
        userInfoAppContainer.uses(bankingCoreFacadeAppContainer, "Gets user info from ");

        userInfoAppContainer.uses(topClientsDatabaseContainer, "Stores top clients info in high availability DB ");
        userInfoAppContainer.uses(userInformationSystem, "Gets user information from ");

        APIGatewayContainer.uses(movementsAppContainer, "Publish services provided by");
        APIGatewayContainer.uses(transferAppContainer, "Publish services provided by");
        APIGatewayContainer.uses(userInfoAppContainer, "Publish services provided by");
        APIGatewayContainer.uses(notificationsAppContainer, "Publish services provided by");
        notificationsAppContainer.uses(notificationsSenderAppContainer, "Send required notifications to");
        notificationsSenderAppContainer.uses(notificationsSystem, "Queues notifications and requests them to");
    }

    private Container addContainerToInternetSystem (SoftwareSystem system, InternetBankingContainerName containerName) {
        return system.addContainer(containerName.getName(), containerName.getDescription(), containerName.getTechnology());
    }
}
