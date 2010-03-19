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
 * Created Oct 8, 2009
 */
package com.acuityph.commons.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * A decorator for {@link Field}.
 *
 * @author Alistair A. Israel
 */
public class FieldWrapper {

    private final Field field;

    /**
     * @param field
     *        the {@link Field} to wrap
     * @return {@link FieldWrapper}
     */
    public static FieldWrapper wrap(final Field field) {
        return new FieldWrapper(field);
    }

    /**
     * @param field
     *        the {@link Field} to wrap
     */
    public FieldWrapper(final Field field) {
        this.field = field;
    }

    /**
     * @return the field
     */
    public final Field getField() {
        return field;
    }

    /**
     * @return true if the underlying Field is static
     */
    public final boolean isStatic() {
        return Modifier.isStatic(field.getModifiers());
    }

}
