import styles from "../styles.module.css";
import {Image} from "react-bootstrap";

export default function TablesUI() {

    const exampleTableList = [
        {
            id: 1,
            tablename: "table1"
        },
        {
            id: 2,
            tablename: "table2"

        },
        {
            id: 3,
            tablename: "table3"

        },
        {
            id: 4,
            tablename: "table4"

        },
        {
            id: 5,
            tablename: "table5"

        }
    ];

    return (
        <div style={{overflowY: 'scroll' ,overflowX: 'hidden', height: '230px', width: '380px'}}>
            <ul>
                {exampleTableList.map(member => (
                    <li key={member.id} style={{listStyleType: "none"}}>
                        <div className={styles.Table}>
                            <Image src="../image/Database.png" className={styles.TableIcon}/>
                            <small className={styles.TableName}>{member.tablename}</small>
                        </div>
                        <p className={styles.TablesTotal}> 총 테이블 수 : {exampleTableList.length}</p>

                    </li>

                ))}
            </ul>
        </div>


    )
}

