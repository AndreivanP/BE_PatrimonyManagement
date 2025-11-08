package com.andreivan.rest.webservices.patrimonymanagement.asset;

public enum AssetCategory {
    RENDA_FIXA_POS("Renda Fixa POS"),
    RENDA_FIXA_PRE("Renda Fixa PRE"),
    RENDA_FIXA_IPCA("Renda Fixa IPCA"),
    IMOVEIS("Imóveis"),
    ACOES("Ações"),
    CRIPTOMOEDAS("Criptomoedas"),
    CARROS("Carros");

    private final String displayName;

    AssetCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AssetCategory fromDisplayName(String displayName) {
        for (AssetCategory category : AssetCategory.values()) {
            if (category.displayName.equals(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid asset category: " + displayName);
    }
}
