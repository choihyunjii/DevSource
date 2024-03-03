import SideBar from "../uI/SideBar";
import SideBarContentUI from "../uI/SideBarContentUI";
import styles from "../styles.module.css";

export default function DataBaseSideBarLayOut(){
    return(
        <div>
            <div className={styles.dataBaseSideBar}>
                <SideBar/>
                <SideBarContentUI/>
            </div>
        </div>
    )
}