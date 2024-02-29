import React from 'react';
import styles from '../styles.module.css'; // 스타일 파일 import

const Modal = ({ isOpen, onClose, header, data }) => {
    const handleCreate = () => {
        onClose(); // 모달을 닫음
    };

    // isOpen이 false이면 모달을 렌더링하지 않음
    if (!isOpen) return null;

    return (
        <div className={styles.modalOverlay} onClick={onClose}>
            <div className={styles.modal}>
                <h2>{header}</h2>
                {/* 프로젝트 정보 표시 */}
                {data && (
                    <div className={styles.commentBox}>
                        <p>프로젝트 이름 : {data.projectName}</p>
                        <p>프로젝트 설명 : {data.comment}</p>
                        <p>프로젝트 생성자 : {data.profile && data.profile.username}</p>
                        <p>프로젝트 팀: {data.teamProfile && data.teamProfile.map(member => member.username).join(', ')}</p>
                    </div>
                )}
                <div className={styles.modalBtnBox}>
                    <button onClick={handleCreate} className={styles.modalConfirmBtn}> 확인 </button>
                </div>
            </div>
        </div>
    );
};

export default Modal;
