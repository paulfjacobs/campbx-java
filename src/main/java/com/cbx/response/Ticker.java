package com.cbx.response;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * The response back from /xticker.
 * @author
 */
public class Ticker {
    private final double lastTrade;
    private final double bestAsk;
    private final double bestBid;

    public Ticker(String jsonEncoded) {
        JsonObject jsonObject = new JsonParser().parse(jsonEncoded).getAsJsonObject();
        this.lastTrade = jsonObject.get("Last Trade").getAsDouble();
        this.bestBid = jsonObject.get("Best Bid").getAsDouble();
        this.bestAsk = jsonObject.get("Best Ask").getAsDouble();
    }

    public Ticker(double lastTrade, double bestAsk, double bestBid) {
        this.lastTrade = lastTrade;
        this.bestAsk = bestAsk;
        this.bestBid = bestBid;
    }

    public double getLastTrade() {
        return this.lastTrade;
    }
    public double getLastAsk() {
        return this.bestAsk;
    }
    public double getBestBid() {
        return this.bestBid;
    }

    @Override
    public String toString() {
        return "Last Trade:"+this.lastTrade+" Best Bid:"+this.bestBid+" Best Ask:"+this.bestAsk;
    }
}
