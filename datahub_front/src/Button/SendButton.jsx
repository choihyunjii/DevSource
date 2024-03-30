import styles from './sendButtonStyle.module.css';

export default function SendButton({text}){
    return(
        <div>
            <div className={styles.modalButtonBox}>
                <button type="button" onClick={SendButton}>{text}</button>
            </div>
        </div>
    )
}