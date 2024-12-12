package com.example.setup;
import java.util.HashMap;
import java.util.Map;

public enum filtersArea {
    DELIVERY_ESTIMATE("Termen de livrare estimat", "//div[@data-name='Termen de livrare estimat']"),
    PRODUS_LA_OFERTA("Produs la oferta", "//div[@data-name='Produs la oferta']"),
    SYSTEM("Sistem de operare", "//div[@data-name='Sistem de operare']"),
    SUPER_PRET("Super Pret", "//div[@data-name='Super Pret']"),
    EMAG_GENIUS("eMAG Genius", "//div[@data-name='eMAG Genius']"),
    PROCESSOR_TYPE("Tip procesor", "//div[@data-name='Tip procesor']"),
    MEMORY_CAPACITY("Capacitate memorie", "//div[@data-name='Capacitate memorie']"),
    BRAND("Brand", "//div[@data-name='Brand']"),
    PROCESSOR_MANUFACTURER("Producator procesor", "//div[@data-name='Producator procesor']"),
    PRICE("Pret", "//div[@data-name='Pret']"),
    SCREEN_DIAGONAL("Diagonala display (inch)", "//div[@data-name='Diagonala display (inch)']"),
    STORAGE_TYPE("Tip stocare", "//div[@data-name='Tip stocare']"),
    VIDEO_CARD_TYPE("Tip placa video", "//div[@data-name='Tip placa video']"),
    AVAILABILITY("Disponibilitate", "//div[@data-name='Disponibilitate']"),
    VIDEO_CHIPSET("Chipset video", "//div[@data-name='Chipset video']"),
    VENDOR("Livrat de", "//div[@data-name='Livrat de']"),
    RATING("Rating minim", "//div[@data-name='Rating minim']"),
    DISPLAY_FORMAT("Format display", "//div[@data-name='Format display']"),
    DISPLAY_CHARACTERISTICS("Caracteristici cheie", "//div[@data-name='Caracteristici cheie']"),
    RESOLUTION("Rezolutie", "//div[@data-name='Rezolutie']"),
    SHOWROOM_AVAILABILITY("Disponibil in showroom", "//div[@data-name='Disponibil in showroom']"),
    TOUCHSCREEN("Touchscreen", "//div[@data-name='Touchscreen']"),
    DISPLAY_TECHNOLOGY("Tehnologii display", "//div[@data-name='Tehnologii display']"),
    PORTS("Porturi", "//div[@data-name='Porturi']"),
    CASE_TYPE("Greutate", "//div[@data-name='Greutate']"),
    BATTERY_AUTONOMY("Autonomie baterie", "//div[@data-name='Autonomie baterie']"),
    KEYBOARD_LANGUAGE("Limba tastatura", "//div[@data-name='Limba tastatura']"),
    DISPLAY_FINISH("Finisaj display", "//div[@data-name='Finisaj display']"),
    SSD_HDD_CAPACITY("Capacitate HDD/SSD", "//div[@data-name='Capacitate HDD/SSD']"),
    LIVRARE_TAZ("Livrat prin Tazz", "//div[@data-name='Livrat prin Tazz']"),
    EASYBOX_AVAILABILITY("Disponibil prin easybox", "//div[@data-name='Disponibil prin easybox']");

    private final String displayName; // Human-readable name for the filter
    private final String xpath;       // XPath for locating the filter

    private static final Map<String, filtersArea> FILTER_AREA_MAP = new HashMap<>();

    static {
        for (filtersArea area : filtersArea.values()) {
            FILTER_AREA_MAP.put(area.getDisplayName(), area);
        }
    }

    filtersArea(String displayName, String xpath) {
        this.displayName = displayName;
        this.xpath = xpath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getXpath() {
        return xpath;
    }

    public static filtersArea fromDisplayName(String displayName) {
        filtersArea area = FILTER_AREA_MAP.get(displayName);
        if (area == null) {
            throw new IllegalArgumentException("No matching filtersArea found for display name: " + displayName);
        }
        return area;
    }
}

