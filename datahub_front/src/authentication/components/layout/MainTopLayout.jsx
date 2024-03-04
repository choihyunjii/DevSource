import styles from "../styleModule/mainStyles.module.css";
import MainTopButtonUI from "../uI/MainTopButtonUI";

export default function MainTopLayout(){
    return(
        <div className={styles.mainTopLayout}>
            <div className={styles.mainTopText}>웹 서비스를 구현하고 관리해보세요</div>
            <div className={styles.mainTopButtons}>
                <MainTopButtonUI buttonName={"프로젝트 생성하러 가기"}/>
                <MainTopButtonUI buttonName={"협업 소스 관리하러 가기"}/>
                <MainTopButtonUI buttonName={"ERD 툴 보러가기"}/>
            </div>
        </div>
    )
}