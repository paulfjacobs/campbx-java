package com.cbx.response;

/**
 * The response back from /xticker.
 * @author
 */
public class Ticker {
    private final double lastTrade;
    private final double bestAsk;
    private final double bestBid;

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
}
