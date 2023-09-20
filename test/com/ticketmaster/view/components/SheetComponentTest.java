package com.ticketmaster.view.components;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SheetComponentTest {

    @Test
    public void splitInLines_shouldWorkAsIntended(){
        SheetComponent sheetComponent = new SheetComponent();
        List<String> lis1t = sheetComponent.splitInLines(5, "lines");
        List<String> list = sheetComponent.splitInLines(5, "This is a text that I want to split in multiple lines");
        List<String> list2 = sheetComponent.splitInLines(5, "12345678901234567890123456789");
    }
}