package com.trailblazers.freewheelers.model;

public class Country {
    private Long country_id;
    private String country_name;
    private double vat_rate;
    private double duty_rate;


//    public Country(String country_name, double vat_rate, double duty_rate) {
//        this.country_id = 0L;
//        this.country_name = country_name;
//        this.vat_rate = vat_rate;
//        this.duty_rate = duty_rate;
//    }

    public double getVat_rate() {
        return vat_rate;
    }

    public double getDuty_rate() {
        return duty_rate;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_id=" + country_id +
                ", country_name='" + country_name + '\'' +
                ", vat_rate=" + vat_rate +
                ", duty_rate=" + duty_rate +
                '}';
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public void setVat_rate(double vat_rate) {
        this.vat_rate = vat_rate;
    }

    public void setDuty_rate(double duty_rate) {
        this.duty_rate = duty_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (Double.compare(country.vat_rate, vat_rate) != 0) return false;
        if (Double.compare(country.duty_rate, duty_rate) != 0) return false;
        return country_name != null ? country_name.equals(country.country_name) : country.country_name == null;

    }

    public String getName() {
        return country_name;
    }
}
