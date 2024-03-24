import styles from '../project/components/createTableStyle.module.css';

import React, {useState} from "react";
import MemberSearchLayOut from "../project/components/layout/MemberSearchLayOut";
import TableSearchLayout from "../project/components/layout/TableSearchLayout";


export default function SelectColumnLayout(){

    const [showSearch , setShowSearch] = useState(true);
    const renderSearch = () => {
       setShowSearch(prevState => !prevState);
    };

    function handleSelectChange(event) {
        const selectedValue = event.target.value;

        console.log("Selected value:", selectedValue);
    }

    return(

        <div>
            <table className={styles.selectHeaderTable}>
                <thead>
                <tr>
                    <th>컬럼이름</th>
                    <th>데이터타입</th>
                    <th>PK</th>
                    <th>FK</th>
                    <th>UK</th>
                    <th>조인</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input style={{width: '170px'}} type="text" className={styles.inputColumnName}/></td>
                    <td style={{width: '170px'}}>
                        <select className={styles.inputDataType} onChange={handleSelectChange}>
                            <optgroup label="Integer">
                                <option value="int">INT</option>
                                <option value="bigInt">BIGINT</option>
                                <option value="smallInt">SMALLINT</option>
                            </optgroup>
                            <optgroup label="String">
                                <option value="char">CHAR</option>
                                <option value="varchar">VARCHAR</option>
                            </optgroup>
                        </select>
                    </td>
                    <td style={{width: '45px'}}>
                        <input type="checkbox" value="pk" className={styles.checkBox}/>
                    </td>
                    <td style={{width: '43px'}}>
                        <input type="checkbox" value="fk" className={styles.checkBox}/>
                    </td>
                    <td style={{width: '43px'}}>
                        <input type="checkbox" value="uk" className={styles.checkBox}/>
                    </td>
                    <td style={{width: '250px'}}>
                        <button onClick={renderSearch} className={styles.searchButton}>검색 ▼</button>
                    </td>

                </tr>
                </tbody>

            </table>
            <div className={styles.searchBox}>
                {showSearch && <TableSearchLayout/>}
            </div>

        </div>

    )
}