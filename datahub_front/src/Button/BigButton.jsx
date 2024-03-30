import styles from './bigButtonStyle.module.css';
export default function BigButton({text}){
    return(
        <div>
            <div className={styles.ButtonBox}>
                <button type="button" className={styles.button}>{text}</button>
            </div>
        </div>
    )
}
