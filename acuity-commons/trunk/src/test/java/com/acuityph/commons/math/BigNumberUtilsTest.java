/**
 * Copyright (c) 2008-2009 Acuity Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created Dec 8, 2009
 */
package com.acuityph.commons.math;

import static com.acuityph.commons.math.BigNumberUtils.isNotNullOrZero;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;

/**
 * JUnit test for {@link BigNumberUtils}.
 *
 * @author Alistair A. Israel
 */
public final class BigNumberUtilsTest {

    /**
     * Test for {@link BigNumberUtils#isNotNullOrZero(java.math.BigDecimal)} .
     */
    @Test
    public void testIsNotNullOrZero() {
        assertFalse(isNotNullOrZero(null));
        assertFalse(isNotNullOrZero(BigDecimal.ZERO));
        assertFalse(isNotNullOrZero(new BigDecimal(0)));
        assertFalse(isNotNullOrZero(new BigDecimal(new BigInteger("0"), 0)));
        assertTrue(isNotNullOrZero(BigDecimal.ONE));
        assertTrue(isNotNullOrZero(new BigDecimal(2)));
    }

}
