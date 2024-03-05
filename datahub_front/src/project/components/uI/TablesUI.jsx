import styles from "../styles.module.css";
import {Image} from "react-bootstrap";

export default function TablesUI(){
    return(
        <div className={styles.Table}>
            <Image src="../image/Database.png" className={styles.TableIcon}/>
            {/*<small className={styles.TableName}>[테이블 이름]</small>*/}

        </div>

        )
}

