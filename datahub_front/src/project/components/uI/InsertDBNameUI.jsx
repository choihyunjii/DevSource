import styles from '../createTableStyle.module.css';

export default function InsertDBNameUI() {
    return(
        <div className={styles.insertBox}>
            <input className={styles.insertDB} type="text" placeholder="[데이터베이스명]"/>

        </div>
    )
}