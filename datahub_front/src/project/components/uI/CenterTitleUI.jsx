import {Image} from "react-bootstrap";
import styles from "../styles.module.css";

export default function CenterTitleUI(){
    return(
        <div className={styles.dataBaseTitle}>
            <h1>
                <Image src="../image/searchDataBase.png" alt="Search Database" className={styles.dataBaseTitleImage}/>
                [ 프로젝트 이름 ]
            </h1>
        </div>
    )
}