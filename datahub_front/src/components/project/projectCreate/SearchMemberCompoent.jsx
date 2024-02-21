import styles from "./styles.module.css";
export default function SearchMemberComponent({ members }) {
    return (
        <div>
            {members.map((member, index) => (
                <div key={index} className={styles.searchGroup}>
                    <small>{member.name}</small><br/>
                    <small>{member.email}</small>
                </div>
            ))}
        </div>
    );
}
