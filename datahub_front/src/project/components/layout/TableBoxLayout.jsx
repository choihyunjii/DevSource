import styles from '../createTableStyle.module.css';
import TableTitleUI from "../uI/TableTitleUI";
import TableNameUI from "../uI/TableNameUI";
import InsertTableNameUI from "../uI/InsertTableNameUI";
import InsertDBNameUI from "../uI/InsertDBNameUI";
import InsertExplanationUI from "../uI/InsertExplanationUI";
import ExplanationTitleUI from "../uI/ExplanationTitleUI";

import SelectColumnLayout from "./SelectColumnLayout";
import React from "react";




export default function TableBoxLayout() {

    return(
        <div>
            <div className={styles.tableBox}>
                <div className={styles.titleBox}>
                    <TableTitleUI title="새로운 테이블 생성"/>
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