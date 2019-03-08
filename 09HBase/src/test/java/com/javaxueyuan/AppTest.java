package com.javaxueyuan;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
        BigDecimal x = new BigDecimal("123.0");
        BigDecimal y = new BigDecimal("123.0");
        System.out.println(x.add(y));
        System.out.println(new BigDecimal(Math.round(x.add(y).doubleValue())));
    }
}
