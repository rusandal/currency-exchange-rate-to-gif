package ru.minikhanov.valuetogif.model;

import java.util.Map;

public class CurrencyInfo {
    private String disclaimer;
    private String license;
    private int timestamp;
    private String base;
    private Map<String, Double> rates;

    public CurrencyInfo() {
    }

    public String getDisclaimer() {
        return this.disclaimer;
    }

    public String getLicense() {
        return this.license;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public String getBase() {
        return this.base;
    }

    public Map<String, Double> getRates() {
        return this.rates;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CurrencyInfo)) return false;
        final CurrencyInfo other = (CurrencyInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$disclaimer = this.getDisclaimer();
        final Object other$disclaimer = other.getDisclaimer();
        if (this$disclaimer == null ? other$disclaimer != null : !this$disclaimer.equals(other$disclaimer))
            return false;
        final Object this$license = this.getLicense();
        final Object other$license = other.getLicense();
        if (this$license == null ? other$license != null : !this$license.equals(other$license)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        final Object this$base = this.getBase();
        final Object other$base = other.getBase();
        if (this$base == null ? other$base != null : !this$base.equals(other$base)) return false;
        final Object this$rates = this.getRates();
        final Object other$rates = other.getRates();
        if (this$rates == null ? other$rates != null : !this$rates.equals(other$rates)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CurrencyInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $disclaimer = this.getDisclaimer();
        result = result * PRIME + ($disclaimer == null ? 43 : $disclaimer.hashCode());
        final Object $license = this.getLicense();
        result = result * PRIME + ($license == null ? 43 : $license.hashCode());
        result = result * PRIME + this.getTimestamp();
        final Object $base = this.getBase();
        result = result * PRIME + ($base == null ? 43 : $base.hashCode());
        final Object $rates = this.getRates();
        result = result * PRIME + ($rates == null ? 43 : $rates.hashCode());
        return result;
    }

    public String toString() {
        return "CurrencyInfo(disclaimer=" + this.getDisclaimer() + ", license=" + this.getLicense() + ", timestamp=" + this.getTimestamp() + ", base=" + this.getBase() + ", rates=" + this.getRates() + ")";
    }

    public class CurrencyValue {
        private String currencyName;
        private double currencyValue;

        public CurrencyValue() {
        }

        public String getCurrencyName() {
            return this.currencyName;
        }

        public double getCurrencyValue() {
            return this.currencyValue;
        }

        public void setCurrencyName(String currencyName) {
            this.currencyName = currencyName;
        }

        public void setCurrencyValue(double currencyValue) {
            this.currencyValue = currencyValue;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof CurrencyValue)) return false;
            final CurrencyValue other = (CurrencyValue) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$currencyName = this.getCurrencyName();
            final Object other$currencyName = other.getCurrencyName();
            if (this$currencyName == null ? other$currencyName != null : !this$currencyName.equals(other$currencyName))
                return false;
            if (Double.compare(this.getCurrencyValue(), other.getCurrencyValue()) != 0) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof CurrencyValue;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $currencyName = this.getCurrencyName();
            result = result * PRIME + ($currencyName == null ? 43 : $currencyName.hashCode());
            final long $currencyValue = Double.doubleToLongBits(this.getCurrencyValue());
            result = result * PRIME + (int) ($currencyValue >>> 32 ^ $currencyValue);
            return result;
        }

        public String toString() {
            return "CurrencyInfo.CurrencyValue(currencyName=" + this.getCurrencyName() + ", currencyValue=" + this.getCurrencyValue() + ")";
        }
    }
}
