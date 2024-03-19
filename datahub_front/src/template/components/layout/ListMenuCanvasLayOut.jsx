import styles from '../../styleModule/canvas.module.css';
import ListMenuComponent from "./ListMenuLayOut";
import {useEffect, useState} from "react";

export default function ListMenuCanvas ({columnData}){
    const [listMenuData , setListMenuData] = useState({})
    const [tableID , setTableID] = useState(1)
    const fetchColumData = async () => {
        try {
            const response = await fetch(`http://localhost:8080/api/column/list/${tableID}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const responseData = await response.json();
            setListMenuData(responseData)
            console.log(responseData)
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    useEffect(() => {
        fetchColumData()
    }, []);


    return(
        <div className={styles.all}>
            <div className={styles.listGrid}>
                <ListMenuComponent title="ColumnName" menus={listMenuData}/>
            </div>

        </div>

    )
}