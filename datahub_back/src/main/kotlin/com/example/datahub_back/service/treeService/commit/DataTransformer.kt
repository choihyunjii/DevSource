package com.example.datahub_back.service.treeService.commit

import com.example.datahub_back.dto.toolDTO.Column
import com.example.datahub_back.dto.toolDTO.Data
import com.example.datahub_back.dto.toolDTO.Table
import com.example.datahub_back.dto.treeDTO.Commit
import com.example.datahub_back.dto.treeDTO.SourceColumn
import com.example.datahub_back.dto.treeDTO.SourceData
import com.example.datahub_back.dto.treeDTO.SourceTable

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
    }
}
