package com.brodskyi.assignment06.util;

import org.junit.Assert;
import org.junit.Test;

public class PeselValidatorTest {

    @Test
    public void shouldBeValid() {
        PeselValidator validator = new PeselValidator("98020712959");
        Assert.assertEquals(true, validator.isValid());
    }

    @Test
    public void shouldBeInvalid() {
        PeselValidator validator = new PeselValidator("980207129");
        Assert.assertEquals(false, validator.isValid());
        PeselValidator validator2 = new PeselValidator("90000000000");
        Assert.assertEquals(false, validator.isValid());
    }
}