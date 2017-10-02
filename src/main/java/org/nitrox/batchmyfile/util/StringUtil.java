/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nitrox.batchmyfile.util;

/**
 *
 * @author Alessandro Lima
 */
public class StringUtil {

    public static String insertZeros(String string, int howMuchZeros) {
        StringBuilder result = new StringBuilder((string == null ? "" : string).trim());
        int difference = howMuchZeros - result.toString().length();

        for (int j = 0; j < difference; j++) {
            result.insert(0, '0');
        }

        return result.toString();
    }
}
