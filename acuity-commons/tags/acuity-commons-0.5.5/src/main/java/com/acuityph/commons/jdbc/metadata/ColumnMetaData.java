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

import java.sql.ResultSet;
import java.sql.SQLException;

import com.acuityph.commons.jdbc.SqlType;

/**
 * Represents column metadata.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class ColumnMetaData {

    private String catalogName;

    private String schemaName;

    private String tableName;

    private String columnName;

    private SqlType sqlType;

    private String dataTypeName;

    private Integer columnSize;

    private Integer decimalDigits;

    private Integer numPrecRadix;

    private Integer nullable;

    private String remarks;

    private String defaultValue;

    private Integer charOctetlength;

    private Integer ordinalPosition;

    private String isNullable;

    private String scopeCatalogName;

    private String scopeSchemaName;

    private String scopeTableName;

    private SqlType sourceDataType;

    /**
     *
     */
    private ColumnMetaData() {
        // noop
    }

    /**
     * @return the catalogName
     */
    public String getCatalogName() {
        return this.catalogName;
    }

    /**
     * @return the charOctetlength
     */
    public Integer getCharOctetlength() {
        return this.charOctetlength;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return this.columnName;
    }

    /**
     * @return the columnSize
     */
    public Integer getColumnSize() {
        return this.columnSize;
    }

    /**
     * @return the dataTypeName
     */
    public String getDataTypeName() {
        return this.dataTypeName;
    }

    /**
     * @return the decimalDigits
     */
    public Integer getDecimalDigits() {
        return this.decimalDigits;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * @return the isNullable
     */
    public String getIsNullable() {
        return this.isNullable;
    }

    /**
     * @return the nullable
     */
    public Integer getNullable() {
        return this.nullable;
    }

    /**
     * @return the numPrecRadix
     */
    public Integer getNumPrecRadix() {
        return this.numPrecRadix;
    }

    /**
     * @return the ordinalPosition
     */
    public Integer getOrdinalPosition() {
        return this.ordinalPosition;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * @return the schemaName
     */
    public String getSchemaName() {
        return this.schemaName;
    }

    /**
     * @return the scopeCatalogName
     */
    public String getScopeCatalogName() {
        return this.scopeCatalogName;
    }

    /**
     * @return the scopeSchemaName
     */
    public String getScopeSchemaName() {
        return this.scopeSchemaName;
    }

    /**
     * @return the scopeTableName
     */
    public String getScopeTableName() {
        return this.scopeTableName;
    }

    /**
     * @return the sourceDataType
     */
    public SqlType getSourceDataType() {
        return this.sourceDataType;
    }

    /**
     * @return the sqlType
     */
    public SqlType getSqlType() {
        return this.sqlType;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * @param catalogName
     *        the catalogName to set
     */
    private void setCatalogName(final String catalogName) {
        this.catalogName = catalogName;
    }

    /**
     * @param charOctetlength
     *        the charOctetlength to set
     */
    private void setCharOctetlength(final Integer charOctetlength) {
        this.charOctetlength = charOctetlength;
    }

    /**
     * @param columnName
     *        the columnName to set
     */
    private void setColumnName(final String columnName) {
        this.columnName = columnName;
    }

    /**
     * @param columnSize
     *        the columnSize to set
     */
    private void setColumnSize(final Integer columnSize) {
        this.columnSize = columnSize;
    }

    /**
     * @param dataTypeName
     *        the dataTypeName to set
     */
    private void setDataTypeName(final String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    /**
     * @param decimalDigits
     *        the decimalDigits to set
     */
    private void setDecimalDigits(final Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * @param defaultValue
     *        the defaultValue to set
     */
    private void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * @param isNullable
     *        the isNullable to set
     */
    private void setIsNullable(final String isNullable) {
        this.isNullable = isNullable;
    }

    /**
     * @param nullable
     *        the nullable to set
     */
    private void setNullable(final Integer nullable) {
        this.nullable = nullable;
    }

    /**
     * @param numPrecRadix
     *        the numPrecRadix to set
     */
    private void setNumPrecRadix(final Integer numPrecRadix) {
        this.numPrecRadix = numPrecRadix;
    }

    /**
     * @param ordinalPosition
     *        the ordinalPosition to set
     */
    private void setOrdinalPosition(final Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    /**
     * @param remarks
     *        the remarks to set
     */
    private void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * @param schemaName
     *        the schemaName to set
     */
    private void setSchemaName(final String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @param scopeCatalogName
     *        the scopeCatalogName to set
     */
    private void setScopeCatalogName(final String scopeCatalogName) {
        this.scopeCatalogName = scopeCatalogName;
    }

    /**
     * @param scopeSchemaName
     *        the scopeSchemaName to set
     */
    private void setScopeSchemaName(final String scopeSchemaName) {
        this.scopeSchemaName = scopeSchemaName;
    }

    /**
     * @param scopeTableName
     *        the scopeTableName to set
     */
    private void setScopeTableName(final String scopeTableName) {
        this.scopeTableName = scopeTableName;
    }

    /**
     * @param sourceDataType
     *        the sourceDataType to set
     */
    private void setSourceDataType(final SqlType sourceDataType) {
        this.sourceDataType = sourceDataType;
    }

    /**
     * @param sqlType
     *        the sqlType to set
     */
    private void setSqlType(final SqlType sqlType) {
        this.sqlType = sqlType;
    }

    /**
     * @param tableName
     *        the tableName to set
     */
    private void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * @param rs
     *        ResultSet
     * @return ColumnMetaData
     * @throws SQLException
     *         on Exception
     */
    public static ColumnMetaData extractColumnMetaData(final ResultSet rs) throws SQLException {
        final SqlType sqlType = SqlType.lookup(rs.getInt("DATA_TYPE"));
        String scopeCatalogName = null;
        String scopeSchemaName = null;
        String scopeTableName = null;
        SqlType sourceDataType = null;
        if (sqlType == SqlType.REF) {
            scopeCatalogName = rs.getString("SCOPE_CATLOG");
            scopeSchemaName = rs.getString("SCOPE_SCHEMA");
            scopeTableName = rs.getString("SCOPE_TABLE");
            sourceDataType = SqlType.lookup(rs.getShort("SOURCE_DATA_TYPE"));
        }
        final ColumnMetaData result = new ColumnMetaData();
        result.setCatalogName(rs.getString("TABLE_CAT"));
        result.setSchemaName(rs.getString("TABLE_SCHEM"));
        result.setTableName(rs.getString("TABLE_NAME"));
        result.setColumnName(rs.getString("COLUMN_NAME"));
        result.setSqlType(sqlType);
        result.setDataTypeName(rs.getString("TYPE_NAME"));
        result.setColumnSize(Integer.valueOf(rs.getInt("COLUMN_SIZE")));
        result.setDecimalDigits(Integer.valueOf(rs.getInt("DECIMAL_DIGITS")));
        result.setNumPrecRadix(Integer.valueOf(rs.getInt("NUM_PREC_RADIX")));
        result.setNullable(Integer.valueOf(rs.getInt("NULLABLE")));
        result.setRemarks(rs.getString("REMARKS"));
        result.setDefaultValue(rs.getString("COLUMN_DEF"));
        result.setCharOctetlength(Integer.valueOf(rs.getInt("CHAR_OCTET_LENGTH")));
        result.setOrdinalPosition(Integer.valueOf(rs.getInt("ORDINAL_POSITION")));
        result.setIsNullable(rs.getString("IS_NULLABLE"));
        result.setScopeCatalogName(scopeCatalogName);
        result.setScopeSchemaName(scopeSchemaName);
        result.setScopeTableName(scopeTableName);
        result.setSourceDataType(sourceDataType);
        return result;
    }

}
