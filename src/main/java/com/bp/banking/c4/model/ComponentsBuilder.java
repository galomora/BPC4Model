package com.bp.banking.c4.model;

import com.structurizr.Workspace;
import com.structurizr.model.Component;
import com.structurizr.model.Container;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;

public class ComponentsBuilder {
    private Workspace workspace;

    public ComponentsBuilder (Workspace workspace) {
        this.workspace = workspace;
    }

    public void createComponents () {
        createTransferAppComponents (workspace);
        createMovementsAppComponents (workspace);
        createUserInfoAppComponents (workspace);
        createNotificationsAppComponents (workspace);
        createNotificationsSenderAppComponents (workspace);
        createMobileAppComponents (workspace);
        createWebSPAComponents (workspace);
    }

    private void createTransferAppComponents (Workspace workspace) {
        Container transferAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.TRANSFER_APP);
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);
        Container databaseContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.AUDIT_DB);

        Component restController = transferAppContainer.addComponent("Transfer Rest Controller", "Transfer Rest Controller");
        Component transferAppLogicComponent = transferAppContainer.addComponent("Transfer App Logic Component", "Transfer App Logic Component");
        Component bankingCoreFacadeClient = transferAppContainer.addComponent("Banking Core Facade Client", "Banking Core Facade Client");
        Component auditComponent = transferAppContainer.addComponent("Audit component","Audit component");

        APIGatewayContainer.uses(restController, "Publish services provided by");
        restController.uses(transferAppLogicComponent, "Makes transfers using");
        transferAppLogicComponent.uses(bankingCoreFacadeClient, "Makes transfers using");
        transferAppLogicComponent.uses(auditComponent, "Creates audit records using");
        auditComponent.uses(databaseContainer, "Creates audit records using");
        Container bankingCoreFacadeAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace,
                InternetBankingContainerName.BANKING_CORE_FACADE_APP);
        bankingCoreFacadeClient.uses(bankingCoreFacadeAppContainer, "Makes transfers using");

    }

    private void createMovementsAppComponents (Workspace workspace) {
        Container movementsAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.MOVEMENTS_APP);
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);
        Container databaseContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.AUDIT_DB);

        Component restController = movementsAppContainer.addComponent("Movements Rest Controller", "Movements Rest Controller");
        Component bankingCoreFacadeClient = movementsAppContainer.addComponent("Banking Core Facade Client", "Banking Core Facade Client");
        Component movementsAppLogicComponent = movementsAppContainer.addComponent("Movements App Logic Component", "Movements App Logic Component");
        Component auditComponent = movementsAppContainer.addComponent("Audit component","Audit component");

        APIGatewayContainer.uses(restController, "Publish services provided by");
        restController.uses(movementsAppLogicComponent, "Queries movements using");
        movementsAppLogicComponent.uses(auditComponent, "Creates audit records using");
        auditComponent.uses(databaseContainer, "Creates audit records using");
        movementsAppLogicComponent.uses(bankingCoreFacadeClient, "Queries movements using");
        Container bankingCoreFacadeAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace,
                InternetBankingContainerName.BANKING_CORE_FACADE_APP);
        bankingCoreFacadeClient.uses(bankingCoreFacadeAppContainer, "Queries movements using");
    }

    private void createUserInfoAppComponents (Workspace workspace) {
        Container userInfoAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.USER_INFO_APP);
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);

        Component restController = userInfoAppContainer.addComponent("User Info Rest Controller", "User Info Rest Controller");
        Component bankingCoreFacadeClient = userInfoAppContainer.addComponent("Banking Core Facade Client", "Banking Core Facade Client");
        Component userInfoSystemClient = userInfoAppContainer.addComponent("User Info System Client", "User Info System Client");
        Component topClientsManager  = userInfoAppContainer.addComponent("Top Clients Manager", "Top Clients Manager");
        Component userInfoAppLogicComponent = userInfoAppContainer.addComponent("User Info Logic Component", "User Info Logic Component");

        APIGatewayContainer.uses(restController, "Publish services provided by");
        restController.uses(userInfoAppLogicComponent, "Gets user info using");
        userInfoAppLogicComponent.uses(bankingCoreFacadeClient, "Gets user info using");
        userInfoAppLogicComponent.uses(userInfoSystemClient, "Gets user info using");
        userInfoAppLogicComponent.uses(topClientsManager, "Registers top clients and retrieves them");
        Container bankingCoreFacadeAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace,
                InternetBankingContainerName.BANKING_CORE_FACADE_APP);
        bankingCoreFacadeClient.uses(bankingCoreFacadeAppContainer, "Gets user info using");
        SoftwareSystem userInformationSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.USER_INFO_SYSTEM);
        userInfoSystemClient.uses(userInformationSystem, "Gets user info from");
        Container topClientsDatabaseContainer =
                ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.TOP_CLIENTS_DATABASE);
        topClientsManager.uses(topClientsDatabaseContainer, "Registers top clients and retrieves them from ");
    }

    private void createNotificationsAppComponents (Workspace workspace) {
        Container notificationsAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.NOTIFICATIONS_APP);
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);

        Component restController = notificationsAppContainer.addComponent("Notifications Rest Controller", "Notifications Rest Controller");
        Component notificationsSenderAppClient = notificationsAppContainer.addComponent("Notifications Sender App Client", "Notifications Sender App Client");

        APIGatewayContainer.uses(restController, "Publish services provided by");
        restController.uses(notificationsSenderAppClient, "Requests notifications using");
        Container notificationsSenderAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.NOTIFICATIONS_SENDER_APP);
        notificationsSenderAppClient.uses(notificationsSenderAppContainer, "Send notification request messages to");
    }

    private void createNotificationsSenderAppComponents (Workspace workspace) {
        Container notificationsAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.NOTIFICATIONS_APP);
        Container notificationsSenderAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.NOTIFICATIONS_SENDER_APP);
        SoftwareSystem notificationsSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.NOTIFICATIONS_SYSTEM);

        Component queueProvider = notificationsSenderAppContainer.addComponent("Message Queue provider", "Message Queue provider for notifications ");
        Component messageProcessor = notificationsSenderAppContainer.addComponent ("Message Processor", "Message processor sends notifications requests ");

        notificationsAppContainer.uses(queueProvider, "Send notification request messages to");
        messageProcessor.uses(queueProvider, "Takes message from");
        messageProcessor.uses(notificationsSystem, "Creates notifications requests and send them to");
    }

    private void createMobileAppComponents (Workspace workspace) {
        Container mobileAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.MOBILE_APP);
        SoftwareSystem onboardingSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.ONBOARDING_SYSTEM);
        Person user = workspace.getModel().getPersonWithName(PersonName.BANK_CLIENT.getName());
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);
        SoftwareSystem authSystem = ComponentsFinder.getSystemByName(workspace, SoftwareSystemName.AUTH_SYSTEM);

        Component onboardingComponent = mobileAppContainer.addComponent("Onboarding Component", "Component for onboarding and registering device");
        Component loginComponent = mobileAppContainer.addComponent("Login Component", "Authenticates and authorizes user");
        Component securityComponent = mobileAppContainer.addComponent("Security Component", "Checks if the user and device are registered and verified");
        Component homeComponent = mobileAppContainer.addComponent("Home Component", "Contains main options for the user");
        Component transferComponent = mobileAppContainer.addComponent("Transfer Component", "Makes transfers");
        Component movementsComponent = mobileAppContainer.addComponent("Movements Component", "Queries movements");
        Component loginMethodsComponent = mobileAppContainer.addComponent("Login methods Component", "Login methods");
        Component deviceFileSystem = mobileAppContainer.addComponent("Device File System", "Device File System for uploading required id card for onboarding");
        Component faceRecognitionComponent = mobileAppContainer.addComponent("Face Recognition Component", "Face Recognition component for onboarding");

        securityComponent.uses(onboardingComponent, "Checks if device and user are registered and verified in");
        securityComponent.uses(onboardingComponent, "If device and user are not registered and verified, invokes onboarding process in");
        securityComponent.uses(loginComponent, "If device and user are registered and verified, invokes login process in");
        onboardingComponent.uses(faceRecognitionComponent, "Captures face recognition from device using");
        onboardingComponent.uses(deviceFileSystem, "Uploads user identification for face recognition using");
        onboardingComponent.uses(onboardingSystem, "Sends onboarding data for verification to");
        onboardingSystem.uses(onboardingComponent, "Notifies successful or failed verification");
        loginComponent.uses(loginMethodsComponent, "Gets login information, i.e. username and password, fingerprint, temporal password, from");
        loginComponent.uses(authSystem, "Authenticates user using");
        onboardingComponent.uses(securityComponent, "After a successful onboarding, notifies to");
        loginComponent.uses(homeComponent, "After a successful login, user can access to the options in");
        homeComponent.uses(transferComponent,"Authorized user can make transfers using");
        homeComponent.uses(movementsComponent,"Authorized user can query movements using");
        homeComponent.uses(APIGatewayContainer,"App loads Authorized user information using");
        user.uses(securityComponent, "User starts application, application launches the");
        transferComponent.uses(APIGatewayContainer,"Makes transfers using");
        movementsComponent.uses(APIGatewayContainer,"Queries movements using");
    }

    private void createWebSPAComponents (Workspace workspace) {
        Container mobileAppContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.WEB_SPA);
        SoftwareSystem authSystem =ComponentsFinder. getSystemByName(workspace, SoftwareSystemName.AUTH_SYSTEM);

        Person user = workspace.getModel().getPersonWithName(PersonName.BANK_CLIENT.getName());
        Container APIGatewayContainer = ComponentsFinder.getInternetBankingContainerByName(workspace, InternetBankingContainerName.API_GATEWAY);

        Component loginComponent = mobileAppContainer.addComponent("Login Component", "Authenticates and authorizes user");
        Component homeComponent = mobileAppContainer.addComponent("Home Component", "Contains main options for the user");
        Component transferComponent = mobileAppContainer.addComponent("Transfer Component", "Makes transfers");
        Component movementsComponent = mobileAppContainer.addComponent("Movements Component", "Queries movements");

        loginComponent.uses(authSystem, "Authenticates user using");
        loginComponent.uses(homeComponent, "After a successful login, user can access to the options in");
        homeComponent.uses(transferComponent,"Authorized user can make transfers using");
        homeComponent.uses(movementsComponent,"Authorized user can query movements using");
        homeComponent.uses(APIGatewayContainer,"App loads Authorized user information using");
        user.uses(loginComponent, "User starts web application, application launches the");
        transferComponent.uses(APIGatewayContainer,"Makes transfers using");
        movementsComponent.uses(APIGatewayContainer,"Queries movements using");
    }
}
