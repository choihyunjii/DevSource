import styles from "../createTableStyle.module.css";
import TableSearchLayout from "../layout/TableSearchLayout";
import React, {useState} from "react";

export default function JoinColumnUI(){
    const [showSearch, setShowSearch] = useState(false); // searchBox 렌더링
    const [joinTable , setJoinTable] = useState("")

    const renderSearch = () => { //
        setShowSearch(prevState => !prevState);
    };

    return(
        <div>
            <input value={joinTable.tableName}/>
            <button onClick={renderSearch} className={styles.searchButton}>검색 ▼</button>
            <div className={styles.searchBox}>
                {showSearch && <TableSearchLayout handleJoinTableSelect={setJoinTable} setShowSearch={renderSearch}/>}
            </div>
        </div>
    )
}