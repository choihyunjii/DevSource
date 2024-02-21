import styles from './styles.module.css';

export default function TitleComponent ({title}){
    return(
        <div>
            <h1 className={styles.title}>{title}</h1>
            <hr className={styles.textLine}/>
        </div>
    )

}