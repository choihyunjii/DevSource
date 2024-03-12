import styles from '../headerStyle.module.css';
export default function BarUI({name}){
    return(
        <div className={styles.navBar}>
            <li>
                <a href="">{name}</a>
            </li>
        </div>
    );
}