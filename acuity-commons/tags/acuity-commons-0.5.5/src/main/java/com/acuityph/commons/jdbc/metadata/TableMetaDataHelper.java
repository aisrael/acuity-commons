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

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.acuityph.commons.jdbc.SqlType;

/**
 * TableMetaDataHelper.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public class TableMetaDataHelper extends MetaDataHelper {

    private final String tableName;

    /**
     * @param dataSource
     *        {@link DataSource}
     * @param tableName
     *        The table name this TableMetaDataHelper object represents.
     */
    public TableMetaDataHelper(final DataSource dataSource, final String tableName) {
        super(dataSource);
        this.tableName = tableName;
    }

    /**
     * @return the tableName
     */
    public final String getTableName() {
        return this.tableName;
    }

    private List<ColumnMetaData> columnMetaDataList;

    /**
     * @return List < ColumnMetaData >
     * @throws SQLException
     *         on exception
     */
    private List<ColumnMetaData> retrieveColumnMetaData() throws SQLException {
        if (this.columnMetaDataList == null) {
            this.columnMetaDataList = new ArrayList<ColumnMetaData>();
            final Connection conn = getDataSource().getConnection();
            try {
                final DatabaseMetaData dmd = conn.getMetaData();
                final ResultSet rs = dmd.getColumns(null, null, this.tableName, null);
                try {
                    while (rs.next()) {
                        final ColumnMetaData column = ColumnMetaData.extractColumnMetaData(rs);
                        this.columnMetaDataList.add(column);
                    }
                } finally {
                    rs.close();
                }
            } finally {
                conn.close();
            }
        }
        return this.columnMetaDataList;
    }

    /**
     * @return String[]
     * @throws SQLException
     *         on exception
     */
    public final String[] listColumnNames() throws SQLException {
        final List<ColumnMetaData> columns = retrieveColumnMetaData();
        final String[] names = new String[columns.size()];
        int i = 0;
        for (final ColumnMetaData column : columns) {
            names[i++] = column.getColumnName();
        }
        return names;
    }

    /**
     * @return array of {@link SqlType}
     * @throws SQLException
     *         on exception
     */
    public final SqlType[] listColumnSqlTypes() throws SQLException {
        final List<ColumnMetaData> columns = retrieveColumnMetaData();
        final SqlType[] types = new SqlType[columns.size()];
        int i = 0;
        for (final ColumnMetaData column : columns) {
            types[i++] = column.getSqlType();
        }
        return types;
    }

}
