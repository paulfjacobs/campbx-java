package com.cbx.response;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * For Bids and Asks there is a list of Price and Quantity.
 * @author  pjacobs
 */
public class Depth {
    private List<PriceQuantity> bidList;
    private List<PriceQuantity> askList;

    public Depth() {
        this.bidList = new ArrayList<PriceQuantity>();
        this.askList = new ArrayList<PriceQuantity>();
    }

    public Depth(String encodedDepth) {
        this();
        JsonObject jsonObject = new JsonParser().parse(encodedDepth).getAsJsonObject();
        decodeArray(jsonObject.get("Bids").getAsJsonArray(), this.bidList);
        decodeArray(jsonObject.get("Asks").getAsJsonArray(), this.bidList);
    }

    private void decodeArray(JsonArray jsonArray, List<PriceQuantity> pqList) {
        // every element of the array is another array of the price and quantity
        Iterator iter = jsonArray.iterator();
        while (iter.hasNext()) {
            JsonArray encodedPriceQuantity = (JsonArray)iter.next();
            pqList.add(new PriceQuantity(encodedPriceQuantity.get(0).getAsDouble(), encodedPriceQuantity.get(1).getAsDouble()));
        }
    }

    @Override
    public String toString() {
        String str = "{Bids : [";
        Iterator iter = bidList.iterator();
        while(iter.hasNext()) {
            str += ((PriceQuantity)iter.next()).toString();
            if(iter.hasNext()) str += ",";
        }
        str += "], Asks : [";
        iter = askList.iterator();
        while(iter.hasNext()) {
            str += ((PriceQuantity)iter.next()).toString();
            if(iter.hasNext()) str += ", ";
        }
        str += "]}";
        return str;
    }
}
