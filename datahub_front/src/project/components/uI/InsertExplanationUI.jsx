import styles from '../createTableStyle.module.css';

export default function InsertExplanationUI(){
    return(
        <div className={styles.insertBox}>
            <input className={styles.insertExplanation}/>
        </div>
    )
}