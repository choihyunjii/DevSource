import styles from "../styles.module.css";

export default function({cardtitle, iconImage, buttontitle}){
    return (
        <div className={styles.ProjectViewCard}>
            <div className={styles.ViewCardTitle}>
                {cardtitle}
                <div className={styles.ViewCardIcon}>{iconImage}</div>
            </div>
            <button className={styles.ViewCardBtn}>{buttontitle}</button>
        </div>

    )
}