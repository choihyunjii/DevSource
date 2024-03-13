package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.dto.treeDTO.*

class DataTransformer {
    companion object {
        fun Table.toSourceTable(commit: Commit): SourceTable {
            return SourceTable(
                tableId = this.id,
                tableName = this.name,
                comment = this.comment,
                isFavorite = this.isFavorite,
                isDelete = this.isDelete,
                updateTime = this.updateTime,
                commit = commit
            )
        }

        fun Column.toSourceColumn(commit: Commit): SourceColumn {
            return SourceColumn(
                columnId = this.id,
                table = this.table.toSourceTable(commit),
                columnName = this.name,
                comment = this.comment,
                dataType = this.dataType,
                isPrimaryKey = this.isPrimaryKey,
                isForeignKey = this.isForeignKey,
                isUniqueKey = this.isUniqueKey,
                joinSourceTableId = this.joinTable?.id
            )
        }

        fun Data.toSourceData(commit: Commit): SourceData {
            return SourceData(
                dataId = this.id,
                column = this.column.toSourceColumn(commit),
                columnLine = this.columLine,
                data = this.data
            )
        }

        fun SourceTable.toChangeTable(commit: Commit): ChangeTable {
            return ChangeTable(
                tableId = this.tableId,
                tableName = this.tableName,
                comment = this.comment,
                isFavorite = this.isFavorite,
                isDelete = this.isDelete,
                updateTime = this.updateTime,
                commit = commit,
            )
        }

        fun SourceColumn.toChangeColumn(commit: Commit): ChangeColumn {
            return ChangeColumn(
                columnId = this.columnId,
                table = this.table.toChangeTable(commit),
                columnName = this.columnName,
                comment = this.comment,
                dataType = this.dataType,
                isPrimaryKey = this.isPrimaryKey,
                isForeignKey = this.isForeignKey,
                isUniqueKey = this.isUniqueKey,
                joinSourceTable = this.joinSourceTableId?.let { this.table }
            )
        }

        fun SourceData.toChangeData(commit: Commit, action: Int): ChangeData {
            return ChangeData(
                dataId = this.dataId,
                column = this.column.toChangeColumn(commit),
                columnLine = this.columnLine,
                data = this.data,
                action = action
            )
        }

    }
}
