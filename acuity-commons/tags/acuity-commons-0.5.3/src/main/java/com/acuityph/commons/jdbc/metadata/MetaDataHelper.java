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
 * Created Mar 7, 2007
 */
package com.acuityph.commons.jdbc.metadata;

import javax.sql.DataSource;

/**
 * (Abstract) Base class for all MetaDataHelpers. Requires that a DataSource be
 * set.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public abstract class MetaDataHelper {

    private final DataSource dataSource;

    /**
     * @param dataSource
     *        {@link DataSource}
     */
    public MetaDataHelper(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * @return the dataSource
     */
    public final DataSource getDataSource() {
        return this.dataSource;
    }

}
