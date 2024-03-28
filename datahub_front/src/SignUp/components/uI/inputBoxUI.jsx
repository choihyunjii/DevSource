import styles from '../../signUpStyle.module.css';


export default function InputBoxUI({name}){

    return(
        <div className={styles.inputBox}>
            <input type="text" className={styles.input} placeholder={name} />
        </div>
    )
}