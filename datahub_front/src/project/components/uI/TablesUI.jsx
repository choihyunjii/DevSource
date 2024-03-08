import styles from "../styles.module.css";
import { Image } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

export default function TablesUI() {
    const { projectId } = useParams();
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const projectResponse = await fetch(`http://localhost:8080/api/table/projectId/${projectId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            const projectData = await projectResponse.json();
            setData(projectData);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div style={{ overflowY: 'scroll', overflowX: 'hidden', height: '230px', width: '380px' }}>
            <ul>
                {data && data.map((member) => (
                    <li key={member.id} style={{ listStyleType: "none" }}>
                        <div className={styles.Table}>
                            <Image src="../image/Database.png" className={styles.TableIcon} />
                            <small className={styles.TableName}>{member.name}</small>
                        </div>
                        <p className={styles.TablesTotal}> 총 테이블 수 : {data.length}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
