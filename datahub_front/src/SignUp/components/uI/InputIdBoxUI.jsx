import styles from '../../signUpStyle.module.css';
export default function InputIdBoxUI() {
    return(
        <div>
            <div className={styles.inputBox}>
                <input type="text" className={styles.inputId} placeholder="아이디"/>
                <button type="button" className={styles.confirmId}>중복확인</button>
            </div>
        </div>
    )
}