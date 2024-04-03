import styles from './sendButtonStyle.module.css';

export default function SendButton({text,onClick}){
    return(
        <div>
            <div className={styles.modalButtonBox}>
                <button type="button" onClick={onClick}>{text}</button>
            </div>
        </div>
    )
}