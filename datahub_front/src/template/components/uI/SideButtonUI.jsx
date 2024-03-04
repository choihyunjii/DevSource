import styles from '../../styleModule/sidebarStyle.module.css';


export default function SideButtonUI({ category, onClick }) {
    const handleClick = () => {
        onClick(category); // 클릭될 때 onClick prop으로 전달된 함수 호출
    };

    return (
        <div className={styles.buttonContainer}>
            <button style={{ display: "flex", justifyContent: "space-between" }} onClick={handleClick}>
                {category} <span>▼</span> {/* 이 부분을 적절한 아이콘으로 바꿔주세요 */}
            </button>
        </div>
    );
}
