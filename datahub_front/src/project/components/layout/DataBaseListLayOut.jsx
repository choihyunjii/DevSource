import styles from "../styles.module.css";
import CenterTitleUI from "../uI/CenterTitleUI";
import LinkUI from "../uI/LinkUI";
import DataBaseBoxLayOut from "./DataBaseBoxLayOut";
import ButtonUI from "../uI/ButtonUI";

export default function DataBaseListLayOut(){
    const handleClick = () => {
        alert('버튼이 클릭되었습니다!');
    };

    return(
        <div className={styles.dataBaseMenuBox}>
            <CenterTitleUI text={"[ 프로젝트 이름 ]"}/>
            <LinkUI text={"데이터 추가하기"}/>
            <DataBaseBoxLayOut/>
            <div className={styles.dataBaseBoxButton}>
                <ButtonUI className={styles.blueButton} onClick={handleClick} children={"데이터 관리"}/>
            </div>
        </div>
    )
}