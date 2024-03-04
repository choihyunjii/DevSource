import styles from "../styles.module.css";
import {Image} from "react-bootstrap";
export default function DataBaseBoxUI(){
    return(
        <div>
            <div className={styles.dataBaseBox}>
                <h3 className={styles.dataBaseBoxTitle}>Title</h3>
                <Image src="../image/dataBase.png" alt="Search Database" className={styles.dataBaseBoxImage}/>
                <div className={styles.dataBaseLogo}>
                    <div className={styles.cardIcon}>
                        <Image src={"image/star.png"}/><Image src={"image/trash.png"}/>
                    </div>
                </div>
            </div>
            <div className={styles.dataBaseBoxTime}>
                <h6>2023 - 10 - 26 일 접속</h6>
            </div>
        </div>

    )
}