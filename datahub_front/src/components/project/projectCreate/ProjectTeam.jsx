import React from 'react';
import styles from './styles.module.css';
import MemberComponent from "./MemberComponent";
import membersData from "./MembersData";

export default function ProjectTeam() {
    return (
        <div>
            <div className={`"form-group" ${styles.customFormGroup} `}>
                <label htmlFor="inputField2" ><h4 className={styles.formTitle} >협업자</h4></label>
                <div className={`"form-group" ${styles.memberGroupBox}`}>
                    <MemberComponent membersData={membersData}/>
                </div>
            </div>
        </div>
    );
}
