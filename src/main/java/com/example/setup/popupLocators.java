package com.example.setup;

public enum popupLocators {
    //GDPR_COOKIE_BANNER("//div[@class='gdpr-cookie-banner js-gdpr-cookie-banner py-2 px-0 show']//div[@class='container']//div[@class='col-xs-12 col-sm-5 col-md-4 col-lg-3 cookie-banner-buttons']//button[@class='btn btn-primary btn-block js-refuse gtm_bxxzbgwexm']"),
    GDPR_COOKIE_BANNER("//div[contains(@class, 'cookie-banner')]//button[contains(@class, 'js-refuse')]"),
    LOGIN_NOTICE_BANNER("//div[@class='gdpr-cookie-banner js-gdpr-cookie-banner py-2 px-0 login-view login-view-ro show']//div[@class='container']//button[@class='js-dismiss-login-notice-btn dismiss-btn btn btn-link py-0 px-0']"),
    ADDON_POPUP1("//div[@class='ns-wrap-bottom-right']//div[@class='ns-box ns-effect-slide ns-type-campaign ns-show']//div[@class='ns-box-inner']//button[@class='close']"),
    ADDON_POPUP2("//div[@class='ns-wrap-bottom-right']//div[@class='ns-box ns-effect-slide ns-type-voucher ns-show']//div[@class='ns-box-inner']//button[@class='close']");

    private final String locator;

    popupLocators(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
