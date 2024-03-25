import styles from '../createTableStyle.module.css';

export default function InsertTableNameUI(){
    return(
        <div className={styles.insertBox}>
                <input className={styles.insertTable} type="text" placeholder=""/>
        </div>
    )
}