import styles from "../styles.module.css";
import React from "react";
import membersData from "../data/MembersData";
import SearchMemberUI from "../uI/SearchMemberUI";

export default function MemberSearchLayOut({ searchTitle }){
    return(
        <div className={`${styles.searchComponent} `}>
            <div className={`"form-group"`}>
                <label htmlFor="projectNameForm"><h4 className={styles.formTitle}>{searchTitle}</h4></label>
                <input placeholder={"이름 또는 이메일을 검색하세요"} type="text" className={`form-control ${styles.inputForm}`} id={styles} />
                <div className={styles.searchMembers}>
                    <SearchMemberUI members={membersData}/>
                </div>

            </div>

        </div>
    )
}