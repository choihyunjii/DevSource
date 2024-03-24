import styles from '../createTableStyle.module.css';
import searchIcon from '../../image/glass.png';
import TableData from "../data/TableData";
import tableData from "../data/TableData";

export default function TableSearchLayout(){
    return(
        <div>
            <div className={styles.searchContainer}>
                <div className={styles.searchTitle}>
                    조인할 테이블 PK 검색
                </div>
                <div className={styles.searchName}>
                    <img src={searchIcon} alt="Search Icon"  style={{width:'25px', height:'25px'}}/>
                    <input type="text" placeholder="테이블명 또는 컬럼명을 검색하세요." className={styles.sn}/>
                </div>
                <div className={styles.attribute}>

                    <div className={styles.list}>
                        {TableData.map((item, index) => (
                            <div key={item.id}>
                                <div>{item.tableName}</div>
                                <div>{item.pkName}</div>
                                <div>{item.type}</div>
                                {/* 추가적인 속성이 있다면 여기에 더 추가할 수 있습니다. */}
                            </div>
                        ))}
                    </div>


                </div>

            </div>

        </div>
    )
}