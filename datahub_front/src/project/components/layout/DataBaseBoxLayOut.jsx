import styles from "../styles.module.css";
import BoxUI from "../uI/BoxUI";
export default function DataBaseBoxLayOut(){
    return(
        <div className={styles.dataBaseContentBox}>
            <BoxUI header={"USER"} date={"2023 - 10 - 26 일"} imageSrc ={"../image/dataBase.png"} />
            <BoxUI header={"Client"} date={"2023 - 10 - 26 일"} imageSrc ={"../image/dataBase.png"} />
            <BoxUI header={"ERD"} date={"2023 - 10 - 26 일"} imageSrc ={"../image/dataBase.png"} />
            <BoxUI header={"Member"} date={"2023 - 10 - 26 일"} imageSrc ={"../image/dataBase.png"} />
        </div>
    )
}