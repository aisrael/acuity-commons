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
 * Created May 12, 2009
 */
package com.acuityph.commons.jpa;

import static java.text.MessageFormat.format;

import java.lang.reflect.Field;

import javax.persistence.Id;

import org.springframework.util.Assert;

import com.acuityph.commons.util.StringUtility;

/**
 * Provides a set of static utility methods for JPA.
 *
 * @author Alistair A. Israel
 * @since 0.3.1
 */
public final class JpaUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private JpaUtils() {
        // noop
    }

    /**
     * "Select all" message format string: {@value JpaUtils#SELECT_ALL_FORMAT}.
     *
     * @see java.text.MessageFormat#format(String, Object...)
     */
    public static final String SELECT_ALL_FORMAT = "SELECT {1} FROM {0} {1}";

    /**
     * For an entity named "MyEntity", returns a string of the form
     * "SELECT myEntity FROM MyEntity myEntity".
     *
     * @param entityName
     *        the entity name
     * @return the 'listAll' JPQL query string
     */
    public static String constructSelectAllQuery(final String entityName) {
        Assert.hasText(entityName, "Invalid entity name \"" + entityName + "\"!");
        return format(SELECT_ALL_FORMAT, entityName, StringUtility.decapitalize(entityName));
    }

    /**
     * @param entityClass
     *        the entity class to inspect
     * @return the {@link Field} annotated with @Id, or null if no such field
     *         was found
     */
    public static Field determineIdField(final Class<?> entityClass) {
        Field idField = null;
        Class<?> cl = entityClass;
        while (idField == null && cl != null && cl != Object.class) {
            for (final Field field : cl.getDeclaredFields()) {
                if (field.getAnnotation(Id.class) != null) {
                    idField = field;
                    break;
                }
            }
            if (idField == null) {
                cl = cl.getSuperclass();
            }
        }
        return idField;
    }
}
