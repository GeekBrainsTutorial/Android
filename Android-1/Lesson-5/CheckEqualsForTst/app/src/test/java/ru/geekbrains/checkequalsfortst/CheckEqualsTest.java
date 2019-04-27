package ru.geekbrains.checkequalsfortst;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CheckEqualsTest {
    @Test
    public void CheckEquals_Compare_Test() {
        CheckEquals checkEquals = new CheckEquals();
        String actual = checkEquals.Compare(2, 2);
        assertEquals("Равно!", actual);
    }
}