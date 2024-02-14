import React, { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.min.css';
import './style.css'
function Header({ activeNavItem, handleNavItemClick, currentUser, isLoggedIn }) {

    return (
        <>
            <Navbar bg="#000" data-bs-theme="light">
                <Container>
                    <Navbar.Brand href="#home" className="navbar-brand">Data Source</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="#home" className="nav-link" style={{ color: activeNavItem === 'home' ? '#FFF' : '#000', backgroundColor: activeNavItem === 'home' ? '#00A3FF' : 'transparent' }} onClick={() => handleNavItemClick('home')}>Home</Nav.Link>
                        <Nav.Link href="#DevTool" className="nav-link" style={{ color: activeNavItem === 'DevTool' ? '#FFF' : '#000', backgroundColor: activeNavItem === 'DevTool' ? '#00A3FF' : 'transparent' }} onClick={() => handleNavItemClick('DevTool')}>DevTool</Nav.Link>
                        <Nav.Link href="#DevTree" className="nav-link" style={{ color: activeNavItem === 'DevTree' ? '#FFF' : '#000', backgroundColor: activeNavItem === 'DevTree' ? '#00A3FF' : 'transparent' }} onClick={() => handleNavItemClick('DevTree')}>DevTree</Nav.Link>
                    </Nav>
                    <Navbar.Text className="navbar-user-info">{currentUser} 님</Navbar.Text>
                    <Navbar.Brand className="navbar-user-info">{isLoggedIn ? '로그인 중' : '로그아웃'}</Navbar.Brand>
                </Container>
            </Navbar>
        </>
    );
}

export default Header;
