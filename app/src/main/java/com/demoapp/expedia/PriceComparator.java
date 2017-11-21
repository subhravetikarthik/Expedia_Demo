package com.demoapp.expedia;

import java.util.Comparator;

class PriceComparator implements Comparator<Hotel> {
    @Override
    public int compare(Hotel o1, Hotel o2) {
        double price1 = Double.parseDouble(o1.getPrice()
                .replaceAll("[^\\d.]+", ""));

        double price2 = Double.parseDouble(o2.getPrice()
                .replaceAll("[^\\d.]+", ""));

        if(price1 > price2)
            return 1;
        else if(price1 < price2)
            return -1;
        else
            return 0;
    }
}
