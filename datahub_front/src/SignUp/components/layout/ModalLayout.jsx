import styles from '../../../Modal/modalStyle.module.css';
import AvailableIDLayout from "../../../Modal/AvailableIDLayout";
export default function  ModalLayout(){
    return (
        <div className={styles.modalContainer}>
                  <AvailableIDLayout/>
        </div>
    )
}