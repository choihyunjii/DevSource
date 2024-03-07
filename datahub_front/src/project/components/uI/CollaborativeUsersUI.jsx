import styles from "../styles.module.css";
import {Image} from "react-bootstrap";

export default function CollborativeUsers(){
    const exampleProfileList = [
        {
            id: 1,
            username: "user1",
            phoneNumber: "010-1234-5678",
            email: "user1@example.com",
            password: "password123"
        },
        {
            id: 2,
            username: "user2",
            phoneNumber: "010-2345-6789",
            email: "user2@example.com",
            password: "password456"
        },
        {
            id: 3,
            username: "user3",
            phoneNumber: "010-3456-7890",
            email: "user3@example.com",
            password: "password789"
        },
        {
            id: 4,
            username: "user4",
            phoneNumber: "010-4567-8901",
            email: "user4@example.com",
            password: "passwordabc"
        },
        {
            id: 5,
            username: "user5",
            phoneNumber: "010-5678-9012",
            email: "user5@example.com",
            password: "passworddef"
        }
    ];


    return (
        <div  style={{ overflowY: 'scroll',overflowX: 'hidden', height: '230px'}}>
            <ul>
                {exampleProfileList.map(member => (
                    <li key={member.id} style={{listStyleType: "none"}}>
                        <div className={styles.CollaborativeUser}>
                            <Image src={"../image/user.png"} className={styles.CollaborativeUserProfile}/>
                            <small className={styles.CollaborativeUserName}>{member.username}</small>

                        </div>
                        <p className={styles.CollaborativeUserTotal}> 총 협업자 수 :  {exampleProfileList.length}</p>


                    </li>
                ))}
            </ul>
        </div>
    );


}

