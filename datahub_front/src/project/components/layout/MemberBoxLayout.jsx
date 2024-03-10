import React from 'react'; // React를 임포트해야 합니다.
import styles from '../memberManagementStyle.module.css';
import MemberTitleUi from "../uI/MemberTitleUi";
import ButtonUI from "../uI/ButtonUI";
import SmallTitleUI from "../uI/SmallTitleUI";
import ProfileUI from "../uI/ProfileUI";
import minus from '../../image/redMinus.png';
import plus from '../../image/bluePlus.png';

import SearchUserNameUI from "../uI/SearchUserNameUI";

export default function MemberBoxLayout() {
    function handleClick() {
       console.log("뀨");
    }

    return (
        <div>
            <div className={styles.memberBox}>
                <div className={styles.titleBox}>
                    <MemberTitleUi text="[프로젝트 이름] 협업자 관리"/>
                </div>
                <div className={styles.memberContainer}>
                    <div className={styles.memberSmallBox}>
                        <SmallTitleUI title="현재 참여자" className={styles.smallTitle}/>
                        <hr className={styles.hrStyle}/>
                        <div className={styles.profileBigBox}>
                            <div className={styles.profileBox}>
                                <ProfileUI img={minus} name="김예지" email="123@gmail.com"/>
                            </div>
                            <div className={styles.profileBox}>
                                <ProfileUI img={minus} name="김보영" email="12345@gmail.com"/>
                            </div>
                        </div>
                    </div>
                    <div className={styles.memberSmallBox}>
                        <div className={styles.searchBigBox}>
                            <SearchUserNameUI/>
                        </div>
                        <div className={styles.profileBigBox}>
                            <div className={styles.profileBox}>
                                <div onClick={handleClick}> {/* +,- 기호 이미지 클릭시 프로필 삭제 or 나타남 +,- 이미지에만 버튼 줬어야했는데 귀찮쓰해서 걍 프로필 전체에 줌..~~ */}
                                    <ProfileUI img={plus} name="주동호" email="123@gmail.com"/>
                                </div>
                            </div>
                            <div className={styles.profileBox}>
                                <ProfileUI img={plus} name="최현지" email="12345@gmail.com"/>
                            </div>
                            <div className={styles.profileBox}>
                                <ProfileUI img={plus} name="박정우" email="12345@gmail.com"/>
                            </div>
                        </div>

                    </div>
                </div>
                <div className={styles.buttonBox}>
                    <ButtonUI children={"저장하기"} className={styles.button}/>
                </div>
            </div>

        </div>
    );
}
