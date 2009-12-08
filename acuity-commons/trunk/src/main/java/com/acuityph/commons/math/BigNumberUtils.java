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

import java.math.BigDecimal;

/**
 *
 * @author Alistair A. Israel
 */
public final class BigNumberUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private BigNumberUtils() {
        // noop
    }

    /**
     * @param number
     *            a BigDecimal number
     * @return true if <code>bigDecimal</code> is not null and non-zero
     */
    public static boolean isNotNullOrZero(final BigDecimal number) {
        return number != null && !BigDecimal.ZERO.equals(number);
    }
}
