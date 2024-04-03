import styles from './InputStyle.module.css';


export default function InputBoxUI({name,way,setName}){

    return(
        <div className={styles.inputBox}>
            <input type={way} className={styles.input} placeholder={name}  onChange={(e)=>setName(e.target.value)} />
        </div>
    )
}