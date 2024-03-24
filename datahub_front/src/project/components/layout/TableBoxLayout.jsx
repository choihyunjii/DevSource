import styles from '../createTableStyle.module.css';
import TableTitleUI from "../uI/TableTitleUI";
import TableNameUI from "../uI/TableNameUI";
import InsertTableNameUI from "../uI/InsertTableNameUI";
import InsertDBNameUI from "../uI/InsertDBNameUI";
import InsertExplanationUI from "../uI/InsertExplanationUI";
import ExplanationTitleUI from "../uI/ExplanationTitleUI";
import Button2UI from "../uI/Button2UI";
import plus from '../../image/plus.png';
import minus from '../../image/minus.png';
import SelectHeaderUI from "../uI/SelectHeaderUI";
import SelectInputUI from "../uI/SelectInputUI";
import CreateTableButtonUI from "../uI/CreateTableButtonUI";
import TableSearchLayout from "./TableSearchLayout";
import TableData from "../data/TableData";
import ButtonUI from "../uI/ButtonUI";

export default function TableBoxLayout() {
    const handleClick = () => {
        console.log("플러스~~~");
    }
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
                <div className={styles.metaContainer}>
                    <div className={styles.button}>
                        <Button2UI img={plus} onClick={handleClick}/>
                        <Button2UI img={minus}/>
                    </div>
                    <div className={styles.dictionary}>
                        <div className={styles.selectHeader}>
                            <SelectHeaderUI/>
                        </div>

                    </div>
                    <div className={styles.buttonContainer}>
                            <CreateTableButtonUI/>
                    </div>
                    <div className={styles.boxx}>
                        <TableSearchLayout/>

                    </div>


                </div>


            </div>
        </div>
    )
}