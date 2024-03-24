package com.hei.wallet.wallety.utils;

import com.hei.wallet.wallety.fjpa.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsUnitTest {
    @Test
    public void doCamelCase(){
        Assertions.assertEquals(Utils.toSQLName("hei"), "\"hei\"");
    }
}
