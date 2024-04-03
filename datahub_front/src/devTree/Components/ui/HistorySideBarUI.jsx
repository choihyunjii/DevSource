import styles from "../../styles/styles.module.css";
import {useEffect, useState} from "react";
import CommitChartUI from "./CommitChartUI";

export default function  HistorySideBarUI({ onSelect }){
    const [userId, setUserId] = useState(1); // 로그인 만들면 수정하기
    const [project, setProject] = useState([]); // 초기값을 일반 객체로 설정
    const [selectedProjectId, setSelectedProjectId] = useState(null);

    const handleProjectChange = (e) => {
        const projectId = parseInt(e.target.value);
        setSelectedProjectId(projectId);
        onSelect(projectId)
    };

    const projectData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/history/${userId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();
            setProject(responseData);
            console.log(responseData)

        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        projectData();
    }, [userId]);

    useEffect(() => {
        if (project.length > 0) {
            const defaultProjectId = project[0].id; // 첫 번째 프로젝트의 ID를 선택
            setSelectedProjectId(defaultProjectId); // 선택된 프로젝트 ID 설정
            onSelect(defaultProjectId); // 선택된 프로젝트 ID를 onSelect 콜백으로 전달
        }
    }, [project]);

    return(
        <>
            <select className={styles.ProjectSelectBox} value={selectedProjectId} onChange={handleProjectChange}>
                {project.map(project => (
                    <option key={project.id} value={project.id}>
                        {project.name}
                    </option>
                ))}
            </select>
            <div className={styles.HistorySideBar}>
                <div className={styles.HistorySideBarMenu}>
                    <p className={styles.HistorySideMenuState}>현재 상태</p>
                    <p className={styles.HistorySideMenuHistory}>히스토리</p>
                    <p className={styles.HistorySideMenuSearch}>커밋 선택</p>
                    <p className={styles.HistorySideMenuRequest}>풀 리퀘스트</p>
                </div>
            </div>
        </>
    )
}