import styles from './textButtonStyle.module.css';

export default function TextButton({text}){
    return(
        <div>
            <div className={styles.buttonBox}>
                <button type="button" className={styles.button}>{text}</button>
            </div>
        </div>
    )
}