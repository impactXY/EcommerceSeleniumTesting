package com.example.setup;

public enum LaptopFilter {
    // Operating System filters
    OS_WINDOWS_10_PRO("Sistem de operare", "Windows 10 Pro", "6427398"),
    OS_WINDOWS_10_HOME("Sistem de operare", "Windows 10 Home", "12235089"),

    // Brand filters
    BRAND_APPLE("Brand", "Apple", "151"),
    BRAND_DELL("Brand", "Dell", "104"),

    // RAM filters
    RAM_8GB("Capacitate memorie", "6 - 8 GB", "25916"),
    RAM_16GB("Capacitate memorie", "12 - 16 GB", "25916");

    private final String filterType;
    private final String filterValue;
    private final String optionId;

    LaptopFilter(String filterType, String filterValue, String optionId) {
        this.filterType = filterType;
        this.filterValue = filterValue;
        this.optionId = optionId;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public String getOptionId() {
        return optionId;
    }

    public String getXPath() {
        return String.format("//a[@class='js-filter-item filter-item' and @data-name='%s']", filterValue);
    }

    public String getCssSelector() {
        return String.format("a.js-filter-item.filter-item[data-option-id='%s']", optionId);
    }

    // Generate URL fragment for the filter
    public String getUrlFragment() {
        // Normalize the filterValue for the URL based on the filter type
        String normalizedValue;
        switch (filterType) {
            case "Brand":
                normalizedValue = filterValue.toLowerCase();
                return String.format("%s", normalizedValue, optionId);
            case "Operating System":
                // Replace spaces and adjust case for Operating System
                normalizedValue = filterValue.toLowerCase().replace(" ", "-");
                break;
            case "Capacitate memorie":
                // Replace spaces and the " - " with "-" for RAM
                normalizedValue = filterValue.toLowerCase().replace(" - ", "-").replace(" ", "-");
                return String.format("%s-v%s", normalizedValue, optionId);
            default:
                // Default normalization (replace spaces with hyphens)
                normalizedValue = filterValue.toLowerCase().replace(" ", "-");
                break;
        }

        // Return the URL fragment in the format: normalized-value-v-optionId
        return String.format("%s-v-%s", normalizedValue, optionId);
    }
}