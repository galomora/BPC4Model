package com.bp.banking.c4.model;

import com.bp.banking.c4.export.ExportFileName;
import com.bp.banking.c4.export.ViewExporter;
import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.*;

public class ViewsBuilder {

    private Workspace workspace;

    public ViewsBuilder (Workspace workspace) {
        this.workspace = workspace;
    }

    public void createViews () {
        ViewExporter.exportToPlantUml (createSystemView (workspace), ExportFileName.SYSTEM_VIEW);
        ViewExporter.exportToPlantUml (createContainerView(workspace), ExportFileName.INTERNET_BANKING_CONTAINER_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.TRANSFER_APP),
                ExportFileName.TRANSFER_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.MOVEMENTS_APP),
                ExportFileName.MOVEMENTS_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.USER_INFO_APP),
                ExportFileName.USER_INFO_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.NOTIFICATIONS_APP),
                ExportFileName.NOTIFICATIONS_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.NOTIFICATIONS_SENDER_APP),
                ExportFileName.NOTIFICATIONS_SENDER_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.MOBILE_APP),
                ExportFileName.MOBILE_APP_COMPONENT_VIEW);
        ViewExporter.exportToPlantUml (createInternetBankingSystemComponentView(workspace,InternetBankingContainerName.WEB_SPA),
                ExportFileName.WEB_SPA_COMPONENT_VIEW);
    }

    private ComponentView createInternetBankingSystemComponentView (Workspace workspace,
                                                                    InternetBankingContainerName containerName) {

        Container container = ComponentsFinder.getInternetBankingContainerByName(workspace, containerName);
        ComponentView componentView = workspace.getViews().createComponentView(
                container, containerName.getName() + " Component View", containerName.getName() + " Component View");
        componentView.addAllContainers();
        componentView.addAllComponents();
        componentView.addAllSoftwareSystems();
        componentView.addAllPeople();

        return componentView;
    }

    private ContainerView createContainerView (Workspace workspace) {
        SoftwareSystem internetBankingSystem = workspace.getModel().getSoftwareSystemWithName(
                SoftwareSystemName.INTERNET_BANKING_SYSTEM.getName());
        ContainerView containerView = workspace.getViews().createContainerView(internetBankingSystem,
                "Internet Banking System Container View", "Internet Banking System Container View" );
        containerView.addAllContainers();
        containerView.addAllSoftwareSystems();
        containerView.addAllPeople();
        return containerView;

    }


    private View createSystemView (Workspace workspace) {
        SoftwareSystem internetBankingSystem = workspace.getModel().getSoftwareSystemWithName(SoftwareSystemName.INTERNET_BANKING_SYSTEM.getName());
        ViewSet viewSet = workspace.getViews();
        SystemContextView contextView = viewSet.createSystemContextView(
                internetBankingSystem, "internetBankingContextView", "Banking system view");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();
        return contextView;
    }
}
