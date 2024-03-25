import styles from '../createTableStyle.module.css';
import TableTitleUI from "../uI/TableTitleUI";
import TableNameUI from "../uI/TableNameUI";
import InsertTableNameUI from "../uI/InsertTableNameUI";
import InsertDBNameUI from "../uI/InsertDBNameUI";
import InsertExplanationUI from "../uI/InsertExplanationUI";
import ExplanationTitleUI from "../uI/ExplanationTitleUI";
import SelectColumnLayout from "./SelectColumnLayout";
import React, {useState} from "react";
import TitleUI from "../uI/TitleUI";

export default function TableBoxLayout() {
    const [tableName , setTableName] = useState(" "); //TableName을 저장할 변수
    const [tableComment , setTableComment] = useState(" ");
    const [dataBaseID, setDataBaseID] = useState(1)
    const [columnData , setColumnData] = useState([{}])

    const sendTableData = () => {
        let obj = {
            tableName : tableName,
            comment : tableComment,
            dataBaseID : dataBaseID,
            columnList : columnData
        }

    }

    return(
        <div>
            <div className={styles.tableBox}>
                <div>
                    <TitleUI title={"새로운 테이블 생성"}/>
                </div>

                <div className={styles.namesContainer}>
                    <div className={styles.nameTable}>
                        <TableNameUI name="테이블명"/>
                        <InsertTableNameUI/>
                    </div>
                    <div className={styles.nameTable}>
                        <TableNameUI name="데이터베이스명"/>
                        <InsertDBNameUI/>
                    </div>
                </div>

                <div className={styles.explanationContainer}>
                    <ExplanationTitleUI title="설명"/>
                    <InsertExplanationUI/>
                </div>

                <div className={styles.dictionary}>
                    <div className={styles.selectHeader}>
                        <SelectColumnLayout/>
                    </div>
                </div>

            </div>
        </div>
    )
}