import styles from "../styles.module.css";
import DataBaseBoxUI from "../uI/DataBaseBoxUI";
export default function DataBaseBoxLayOut(){
    return(
        <div className={styles.dataBaseContentBox}>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
            <DataBaseBoxUI/>
        </div>
    )
}