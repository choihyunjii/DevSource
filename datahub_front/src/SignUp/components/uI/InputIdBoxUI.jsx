import styles from '../signUpStyle.module.css';
import {useState} from "react";
export default function InputIdBoxUI({setName}) {
    const [inputValue, setInputValue] = useState('');

    const handleButtonClick = async () => {
        try {
            const response = await fetch('http://localhost:8080/api/project/profile/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ id: inputValue })
            });
            const data = await response.json();
            if (response.ok) {
                // 서버에서 받은 데이터를 기반으로 중복 여부를 판단하여 처리
                if (data.isDuplicate) {
                    alert('이미 사용 중인 아이디입니다.');
                } else {
                    alert('사용 가능한 아이디입니다.');
                    setName(inputValue); // 아이디를 상태로 설정
                }
            } else {
                throw new Error(data.message);
            }
        } catch (error) {
            console.error('Error checking ID:', error);
        }
    };

    const handleInputChange = (e) => {
        setInputValue(e.target.value);
    };

    return(
        <div>
            <div className={styles.inputBox}>
                <input type="text" className={styles.inputId} placeholder="아이디" onChange={(e)=>setName(e.target.value)}/>
                <button type="button" className={styles.confirmId} onClick={handleButtonClick}>중복확인</button>
            </div>
        </div>
    )
}