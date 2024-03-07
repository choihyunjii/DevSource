import styles from "../styles.module.css";
import ProjectInformationLayOut from "../layout/ProjectInformationLayOut";
import ProjectCollaborativeLayOut from "../layout/ProjectCollaborativeLayOut";
import ProjectTablesLayOut from "../layout/ProjectTablesLayOut";
import TitleUI from "../uI/TitleUI";
import {Image} from "react-bootstrap";


export default function ProjectViewPage(){
    return(
        <div className={styles.ProjectViewCardPage}>
            <div className={styles.ProjectViewCardPageHeader}>
                <Image src={"../image/webSite.png"} className={styles.WebSiteIcon}/>
                <div className={styles.ProjectTitle}><TitleUI title={"[프로젝트 이름]"}/></div>
            </div>
            <div className={styles.ProjectViewCards}>
                <ProjectInformationLayOut/>
                <ProjectCollaborativeLayOut/>
                <ProjectTablesLayOut/>
            </div>
            <div className={styles.PageFooterBtn}>
                <Image src="../image/star.png" className={styles.BottomStar}/>
                <Image src="../image/trash.png" className={styles.BottomTrash}/>
            </div>
        </div>
    )
}