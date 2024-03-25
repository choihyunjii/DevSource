import styles from "../../styles/styles.module.css";
import HistoryButtonUI from "../ui/HistoryButtonUI";
import HistorySideBarUI from "../ui/HistorySideBarUI";
import React from "react";
import HistoryButtonWhitever from "../ui/HistoryButtonWhitever";

export default function HistoryCanvasLayOut(){
    return(
        <>
            <div className={styles.buttonContainerF}>
                <HistoryButtonUI title={"Commit"}/>
                <HistoryButtonWhitever title={"Merge reqeust"} icon={"↓"} number={"3"} />
            </div>
            <div className={styles.buttonContainerT}>
                <HistoryButtonUI title={"Reset"}/>
                <HistoryButtonUI title={"Merge"}/>
            </div>
        </>
    )
}