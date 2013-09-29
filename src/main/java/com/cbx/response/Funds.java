package com.cbx.response;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Fund information
 * @author  pjacobs
 */
public class Funds {
    private double totalUSD;
    private double totalBTC;
    private double liquidUSD;
    private double liquidBTC;
    private double marginUSD;
    private double marginBTC;

    public Funds(String encodedFunds) {
        JsonObject jsonObject = new JsonParser().parse(encodedFunds).getAsJsonObject();
        this.totalUSD = jsonObject.get("Total USD").getAsDouble();
        this.totalBTC = jsonObject.get("Total BTC").getAsDouble();
        this.liquidUSD = jsonObject.get("Liquid USD").getAsDouble();
        this.liquidBTC = jsonObject.get("Liquid BTC").getAsDouble();
        this.marginUSD = jsonObject.get("Margin Account USD").getAsDouble();
        this.marginBTC = jsonObject.get("Margin Account BTC").getAsDouble();
    }

    @Override
    public String toString() {
        return "Total USD:"+totalUSD+" Total BTC:"+totalBTC+" Liquid USD:"+liquidUSD+" Liquid BTC:"+liquidBTC+
                " Margin USD:"+marginUSD+" Margin BTC:"+marginBTC;
    }
}
