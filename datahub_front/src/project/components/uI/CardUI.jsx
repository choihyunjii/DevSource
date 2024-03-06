    import {Image} from "react-bootstrap";
import styles from "../styles.module.css";

export default function CardUI({ name , comment , projectID }){
    function clickHandler() {
        alert(projectID)
    }

    return(
        <div className={styles.cardBox}>
            <div className={styles.cardLogo}>
                <Image src={"image/webSite.png"}/>
                <div className={styles.cardIcon}>
                    <Image src={"image/star.png"}/><Image src={"image/trash.png"}/>
                </div>
            </div>
            <h3 className={styles.cardTitle}>{name}</h3>
            <p className={styles.cardComment}>{comment}</p>
            <a href="#" className={styles.cardLink} onClick={clickHandler}>이동하기</a>
        </div>
    )
}