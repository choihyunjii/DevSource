import styles from './textButtonStyle.module.css';
import { useNavigate } from "react-router-dom";

export default function TextButton({text}) {
   /* const navigate = useNavigate();

    const navigateToPurchase = () => {
        navigate("/main");
    };
*/
    return (
        <div>
            <div className={styles.buttonBox}>
                <button type="button" className={styles.button} >{text}</button>
            </div>
        </div>
    );
}
