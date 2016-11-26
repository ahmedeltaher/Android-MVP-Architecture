
package com.foodora.data.remote.dto;

import com.google.gson.annotations.SerializedName;


public class Price {

    @SerializedName("current")
    private Double current;
    @SerializedName("original")
    private Double original;
    @SerializedName("currency")
    private String currency;

    /**
     * 
     * @return
     *     The current
     */
    public Double getCurrent() {
        return current;
    }

    /**
     * 
     * @param current
     *     The current
     */
    public void setCurrent(Double current) {
        this.current = current;
    }

    /**
     * 
     * @return
     *     The original
     */
    public Double getOriginal() {
        return original;
    }

    /**
     * 
     * @param original
     *     The original
     */
    public void setOriginal(Double original) {
        this.original = original;
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
