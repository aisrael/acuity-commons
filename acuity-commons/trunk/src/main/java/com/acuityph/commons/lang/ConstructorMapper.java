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
 * Created May 27, 2009
 */
package com.acuityph.commons.lang;

import static com.acuityph.commons.lang.NamedParameter.parameter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import com.acuityph.commons.lang.ConstructorMapper.Builder.ToFrom;

/**
 * @param <S>
 *        the source type
 * @param <T>
 *        the target type
 * @author Alistair A. Israel
 * @since 0.4.1
 */
public class ConstructorMapper<S, T> extends Mapper.Base<S, T> {

    /**
     * @param <T>
     *        target type
     * @param targetClass
     *        class of <S>
     * @return Builder.ToFrom<T>
     */
    public static <T> ToFrom<T> construct(final Class<T> targetClass) {
        return new ToFrom<T>(targetClass);
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Builder<S, T> {

        private final Class<T> targetClass;

        private final BeanWrapperImpl beanWrapper;

        /**
         * @param sourceClass
         *        the class of type &lt;S&gt;
         * @param targetClass
         *        the class of type &lt;T&gt;
         */
        protected Builder(final Class<S> sourceClass, final Class<T> targetClass) {
            super();
            this.targetClass = targetClass;
            beanWrapper = new BeanWrapperImpl(sourceClass);
        }

        /**
         * @param propertyNames
         *        the source property names (varargs)
         * @return the {@link ConstructorMapper}
         */
        public final ConstructorMapper<S, T> using(final String... propertyNames) {
            final List<NamedParameter<?>> parameters = new ArrayList<NamedParameter<?>>();

            for (final String name : propertyNames) {
                final PropertyDescriptor propertyDescriptor =
                        beanWrapper.getPropertyDescriptor(name);
                if (propertyDescriptor != null) {
                    final Class<?> type = propertyDescriptor.getPropertyType();
                    parameters.add(parameter(name, type));
                }
            }

            final int nParameters = parameters.size();
            final Class<?>[] types = new Class<?>[nParameters];
            final String[] names = new String[nParameters];
            for (int i = 0; i < nParameters; ++i) {
                types[i] = parameters.get(i).getType();
                names[i] = parameters.get(i).getName();
            }
            try {
                final Constructor<T> constructor = targetClass.getConstructor(types);
                return new ConstructorMapper<S, T>(targetClass, constructor, types, names);
            } catch (final Exception e) {
                throw new ConstructionException(e.getMessage(), e);
            }
        }

        /**
         * A Builder builder.
         *
         * @param <S>
         *        the source type
         * @author Alistair A. Israel
         */
        public static final class ToFrom<T> {

            private final Class<T> targetClass;

            /**
             * @param targetClass
             *        the class of type &lt;T&gt;
             */
            private ToFrom(final Class<T> targetClass) {
                super();
                this.targetClass = targetClass;
            }

            /**
             * @param <X>
             *        the source type
             * @param sourceClass
             *        the class of type &lt;T&gt;
             * @return a Builder&lt;S, T&gt;
             */
            public <X> Builder<X, T> from(final Class<X> sourceClass) {
                return new Builder<X, T>(sourceClass, targetClass);
            }
        }
    }

    private final Constructor<T> constructor;

    private final Class<?>[] parameterTypes;

    private final String[] parameterNames;

    /**
     * @param targetClass
     *        the target class
     * @param constructor
     *        the target constructor
     * @param parameterTypes
     *        the parameter types
     * @param parameterNames
     *        the parameter names
     */
    protected ConstructorMapper(final Class<T> targetClass, final Constructor<T> constructor,
            final Class<?>[] parameterTypes, final String[] parameterNames) {
        super(targetClass);
        Assert.isTrue(parameterTypes.length > 0, "No constructor parameters defined!");
        this.constructor = constructor;
        this.parameterTypes = parameterTypes;
        this.parameterNames = parameterNames;
    }

    /**
     * {@inheritDoc}
     *
     * @see com.acuityph.commons.lang.Mapper#map(java.lang.Object)
     */
    public final T map(final S obj) {
        final Object[] args = new Object[parameterTypes.length];
        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(obj);
        for (int i = 0; i < parameterTypes.length; ++i) {
            final Object value = beanWrapper.getPropertyValue(parameterNames[i]);
            Assert.isTrue(parameterTypes[i].isAssignableFrom(value.getClass()));
            args[i] = value;
        }
        try {
            return constructor.newInstance(args);
        } catch (final Exception e) {
            throw new ConstructionException(e.getMessage(), e);
        }
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class ConstructionException extends RuntimeException {

        /**
         *
         */
        private static final long serialVersionUID = 5641199308770871122L;

        /**
         * @param msg
         *        Exception message
         * @param t
         *        Throwable cause
         */
        public ConstructionException(final String msg, final Throwable t) {
            super(msg, t);
        }

        /**
         * @param msg
         *        Exception message
         */
        public ConstructionException(final String msg) {
            super(msg);
        }

        /**
         * @param t
         *        Throwable cause
         */
        public ConstructionException(final Throwable t) {
            super(t);
        }

    }

}
