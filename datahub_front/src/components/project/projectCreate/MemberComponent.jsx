import { Image } from "react-bootstrap";
import styles from './styles.module.css';

export default function MemberComponent({ membersData }) {
    return (
        membersData.map((user, index) => (
            <div key={index} className={styles.member}>
                <button className={styles.customButton}> </button>
                <Image src={"image/user.png"} />
                <div>
                    <h4>{user.name}</h4>
                    <p>{user.email}</p>
                </div>
            </div>
        ))
    );
}
