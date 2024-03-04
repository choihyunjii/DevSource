import MainBottomUI from "../uI/MainBottomUI";
import styles from "../styleModule/mainStyles.module.css";

export default function MainBottomLayout(){
    return(
        <div>
            <div className={styles.mainBottomBoxes}>
                <span className={styles.mainUiBox1}>
                    <MainBottomUI
                        stepNum={1}
                        bigText={"웹 프로젝트 \n생성 및 관리"}
                        smallText={"자신의 프로젝트 생성"}
                        buttonText={"Project"}
                        circleColor={"#B3E4FF"}
                    />
                </span>
                <span className={styles.mainUiBox2}>
                    <MainBottomUI
                        stepNum={2}
                        bigText={"웹 프로젝트 \n생성 및 관리"}
                        smallText={"자신의 프로젝트 생성"}
                        buttonText={"New Project"}
                        circleColor={"#62C6FF"}
                    />
                </span>
                <span className={styles.mainUiBox3}>
                    <MainBottomUI
                        stepNum={3}
                        bigText={"웹 프로젝트 \n생성 및 관리"}
                        smallText={"자신의 프로젝트 생성"}
                        buttonText={"New Project"}
                        circleColor={"#4EBFFF"}
                    />
                </span>
                <span className={styles.mainUiBox4}>
                    <MainBottomUI
                        stepNum={4}
                        bigText={"웹 프로젝트 \n생성 및 관리"}
                        smallText={"자신의 프로젝트 생성"}
                        buttonText={"New Project"}
                        circleColor={"#00A3FF"}
                    />
                </span>
            </div>
        </div>
    )
}