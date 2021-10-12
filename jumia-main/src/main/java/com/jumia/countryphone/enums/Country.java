package com.jumia.countryphone.enums;

import lombok.Getter;

@Getter
public enum Country {

    CAMEROON("Cameroon","^\\(237\\).*$", "\\(237\\)\\ ?[2368]\\d{7,8}$"),
    ETHIOPIA("Ethiopia","^\\(251\\).*$", "\\(251\\)\\ ?[1-59]\\d{8}$"),
    MOROCCO("Morocco","^\\(212\\).*$", "\\(212\\)\\ ?[5-9]\\d{8}$"),
    MOZAMBIQUE("Mozambique","^\\(258\\).*$", "\\(258\\)\\ ?[28]\\d{7,8}$"),
    UGANDA("Uganda","^\\(256\\).*$", "\\(256\\)\\ ?\\d{9}$");

    private String countryName;
    private String codeRegex;
    private String phoneRegex;

    Country(String countryName, String codeRegex, String phoneRegex) {
        this.countryName = countryName;
        this.codeRegex = codeRegex;
        this.phoneRegex = phoneRegex;
    }

    /**
     * Returns country based on phone.
     *
     * @param phone
     * @return Country
     */
    public static Country getCountryByPhone(String phone) {
        for (Country country : Country.values()) {
            if (phone.matches(country.getCodeRegex())) {
                return country;
            }
        }
        return null;
    }
}
