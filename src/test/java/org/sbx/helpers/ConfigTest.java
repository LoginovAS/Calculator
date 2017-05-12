package org.sbx.helpers;

import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    @Test
    public void getPropertiesWithoutParametersSizeTest() {
        Assert.assertEquals(4, Config.getProperties().size());
    }

}
