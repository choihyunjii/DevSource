import styles from '../createTableStyle.module.css';

export default function Button2UI({ img, onClick }) {
    return (
        <div className={styles.buttonImg} onClick={onClick}>
            <img src={img} alt="Button Image" />
        </div>
    );
}
