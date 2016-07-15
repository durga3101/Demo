package com.trailblazers.freewheelers.model;

public class Country {
    private final String name;
    private double vat_rate;
    private double duty_rate;

    public Country(String name, double vat_rate, double duty_rate) {
        this.name = name;
        this.vat_rate = vat_rate;
        this.duty_rate = duty_rate;
    }

    public double getVat_rate() {
        return vat_rate;
    }

    public double getDuty_rate() {
        return duty_rate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (Double.compare(country.vat_rate, vat_rate) != 0) return false;
        if (Double.compare(country.duty_rate, duty_rate) != 0) return false;
        return name != null ? name.equals(country.name) : country.name == null;

    }

}
