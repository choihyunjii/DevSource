import styles from "../styles.module.css";

export default function LinkUI({text}){
    return(
        <div className={styles.linkUI}>
            <a className={styles.link} href="#">{text}</a>
            {/*LinkTag 변경 필요*/}
        </div>
    )
}