import styles from "../styles.module.css";

export default function SideBar(){
    return(
        <div>
            <div className={styles.titleBarSide} style={{}}>
                <h3>DataBase </h3>
                <h4>▼</h4>
            </div>
        </div>
    )
}