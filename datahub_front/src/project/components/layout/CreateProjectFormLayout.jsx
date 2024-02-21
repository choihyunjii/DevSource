import React, { useState } from 'react';
import styles from '../styles.module.css';
import ProjectTeamLayOut from "./ProjectTeamLayOut";
import MemberSearchLayOut from "./MemberSearchLayOut";

export default function CreateProjectFormLayout() {
    const [showMemberGroupSearchBar, setShowMemberGroupSearchBar] = useState(false);

    const toggleMemberGroupSearchBar = () => {
        setShowMemberGroupSearchBar(prevState => !prevState);
    };

    return (
        <div>
            {/* 협업자 검색 바 */}
            {showMemberGroupSearchBar && <MemberSearchLayOut searchTitle={"협업자 검색"} />}

            {/* 프로젝트명 입력 폼 */}
            <div className={`"form-group" ${styles.customFormGroup}`}>
                <label htmlFor="projectNameForm"><h4 className={styles.formTitle}>프로젝트명</h4></label>
                <input type="text" className={`form-control ${styles.inputForm}`} id={styles.projectNameForm} />
            </div>

            {/* 설명 입력 폼 */}
            <div className={`"form-group" ${styles.customFormGroup}`}>
                <label htmlFor="inputField2" ><h4 className={styles.formTitle} >설명<small>(선택 사항)</small></h4></label>
                <input type="text" className={`form-control ${styles.inputForm}`}  id="inputField2" />
            </div>

            {/* 협업자 추가 버튼 */}
            <button type="button" className={` ${styles.addButton}`} onClick={toggleMemberGroupSearchBar}>협업자 추가</button>

            {/* 프로젝트 팀 */}
            <ProjectTeamLayOut/>

            {/* 프로젝트 생성 버튼 */}
            <button type="button" className={`btn btn-primary ${styles.createButton}` }>프로젝트 생성</button>
        </div>
    );
}
