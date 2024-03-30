import styles from '../modalStyle.module.css';
import AvailableIDLayout from "./AvailableIDLayout";
export default function  ModalLayout(){
    return (
        <div className={styles.modalContainer}>
                  <AvailableIDLayout/>
        </div>
    )
}