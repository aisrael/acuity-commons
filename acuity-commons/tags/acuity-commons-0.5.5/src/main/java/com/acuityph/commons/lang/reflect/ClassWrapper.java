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
import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public class ClassWrapper<T> {

    private final Class<T> clazz;

    /**
     * @param clazz
     *        the {@link Class} to wrap
     */
    public ClassWrapper(final Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return array {@link FieldWrapper}
     */
    public final FieldWrapper[] getDeclaredInstanceFields() {
        final List<FieldWrapper> fields = new ArrayList<FieldWrapper>();
        for (final Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(FieldWrapper.wrap(field));
            }
        }
        return fields.toArray(new FieldWrapper[fields.size()]);
    }
}
