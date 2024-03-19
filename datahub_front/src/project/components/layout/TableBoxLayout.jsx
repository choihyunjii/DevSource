import styles from '../createTableStyle.module.css';
import TableTitleUI from "../uI/TableTitleUI";
import TableNameUI from "../uI/TableNameUI";

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


                    </div>

                    <div className={styles.nameTable}>

                    </div>


                </div>
            </div>
        </div>
    )
}