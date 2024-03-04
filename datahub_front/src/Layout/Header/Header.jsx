import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';
import './style.css'
function Header({ activeNavItem, handleNavItemClick, currentUser, isLoggedIn }) {
    const restoreColor = () => {
        // 원래 색상으로 복원하는 코드 작성
        document.querySelector('.logo').style.color = '#00A3FF';
    };

    const preventColorChange = (event) => {
        // 색상 변화를 막는 코드 작성
        event.preventDefault();
    };
    return (
        <div className="All">
            <Navbar>
                <Container>
                    <Navbar.Brand href="#home" className="logo" onMouseOver={restoreColor} onClick={preventColorChange}>DevSource</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="#home" className="nav-link" >Home</Nav.Link>
                        <Nav.Link href="#DevTool" className="nav-link">DevTool</Nav.Link>
                        <Nav.Link href="#DevTree" className="nav-link" >DevTree</Nav.Link>
                    </Nav>
                    <Navbar.Text className="navbar-user-info">{currentUser} 님</Navbar.Text>
                    <Navbar.Brand className="navbar-user-info">{isLoggedIn ? '로그인 중' : '로그아웃'}</Navbar.Brand>
                </Container>
            </Navbar>
        </div>
    );
}

export default Header;
