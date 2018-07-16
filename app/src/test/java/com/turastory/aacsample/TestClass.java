package com.turastory.aacsample;

/**
 * Created by tura on 2018-07-16.
 */
public class TestClass {
    
    public String returnsNullIfZero(int number) {
        if (number == 0)
            return null;
        else
            return String.valueOf(number);
    }
}
