import styles from './SearchIDStyle.module.css';
import { useState } from "react";
import InputBoxUI from "../../Input/InputBoxUI";

export default function SelectUI() {
    const [name , setName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNum,setPhoneNum] = useState("");
    const [selectedButton, setSelectedButton] = useState('왼쪽');
    const sendData = () => {
        let obj = {
            name:name,
            email:email,
            phoneNum:phoneNum,
        }
    }

    const handleButtonClick = (buttonType) => {
        setSelectedButton(buttonType);
    };

    return (
        <div>
            <div className={styles.selectContainer}>
                <div className={styles.selectBox}>
                    <button
                        className={selectedButton === "왼쪽" ? styles.selectedButton : styles.notSelectedButton}
                        onClick={() => handleButtonClick("왼쪽")}
                    >
                       이메일로 찾기
                    </button>
                    <button
                        className={selectedButton === "오른쪽" ? styles.selectedButton : styles.notSelectedButton}
                        onClick={() => handleButtonClick("오른쪽")}
                    >
                       전화번호로 찾기
                    </button>
                </div>
            </div>
            {selectedButton === "왼쪽" ? (
                <div className={styles.box}>
                    <InputBoxUI name="이름" way="text"/>
                    <InputBoxUI name="이메일" way="text"/>
                </div>
            ) : (
                <div className={styles.box}>
                    <InputBoxUI name="이름" way="text"/>
                    <InputBoxUI name="전화번호" way="text"/>
                </div>
            )}
        </div>
    );
}
