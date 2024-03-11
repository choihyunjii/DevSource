import TitleUI from "../uI/TitleUI";
import LinkUI from "../uI/LinkUI";
import styles from "../styles.module.css";
import ProjectCardLayOut from "../layout/ProjectCardLayOut";
import ProjectSideBarLayOut from "../layout/ProjectSideBarLayOut";

export default function ProjectShowCasePage(){
    return(
        <div className={styles.projectShowCasePage}>
            <ProjectSideBarLayOut/>
            <div className={styles.showCase}>
                <TitleUI title={"프로젝트 선택"}/>
                <LinkUI text={"프로젝트 생성"} redirect={"/createProject"}/>
                <ProjectCardLayOut/>
            </div>
        </div>
    )
}