import styles from "../../styles/styles.module.css";
import CommitChartUI from "../ui/CommitChartUI";
import HistoryCanvasLayOut from "../layout/HistoryCanvasLayOut";
import ChangeCommitLayOut from "../layout/ChangeCommitLayOut";
import HistorySideBarUI from "../ui/HistorySideBarUI";
import React, {useState} from "react";

export default function HistoryViewPage(){
    const [selectedProjectId, setSelectedProjectId] = useState(null);
    const [selectedCommitId, setSelectedCommitId] = useState(null);

    const handleSelectProject = (projectId) => {
        setSelectedProjectId(projectId);
    };

    const handleSelectCommit = (commitId) => {
        setSelectedCommitId(commitId);
    };

    return(
        <div className={styles.HistoryPage}>
            <div className={styles.HistoryCanverLayOut}>
                <HistoryCanvasLayOut />
            </div>

            <div className={styles.HistoryCanverBack} >
                <div className={styles.HistoryCanver}>
                    <CommitChartUI
                        projectId={selectedProjectId}
                        onSelect={handleSelectCommit}
                    />
                    <ChangeCommitLayOut
                        commitId={selectedCommitId}
                    />
                </div>
            </div>
            <HistorySideBarUI
                onSelect={handleSelectProject}
            />
        </div>
    )

}