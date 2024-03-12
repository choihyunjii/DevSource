
import styles from './headerStyle.module.css';
import BarUI from "./UI/BarUI";
function Header({ activeNavItem, handleNavItemClick, currentUser, isLoggedIn }) {

    return (
        <div className={styles.All}>
                <div className={styles.container}>
                    <div className={styles.logo} >DevSource</div>
                        <div className={styles.bar}>
                            <div className={styles.navLink}>
                                    <BarUI name="Home"/>
                            </div>
                            <div className={styles.info}>
                                <div >{currentUser} 최현지님</div>
                                <div >{isLoggedIn ? '로그인 중' : '로그아웃'}</div>
                            </div>
                        </div>
                </div>
        </div>

    );
}

export default Header;