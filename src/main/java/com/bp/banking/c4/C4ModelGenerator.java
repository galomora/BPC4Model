package com.bp.banking.c4;

import com.bp.banking.c4.model.BPInternetBankingDiagramBuilder;

public class C4ModelGenerator {
    public static void main(String[] args) throws Exception {
        BPInternetBankingDiagramBuilder builder = new BPInternetBankingDiagramBuilder();
        builder.createWorkspaceAndExportDiagrams();
    }
}
