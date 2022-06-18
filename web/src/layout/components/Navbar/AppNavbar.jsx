import React from "react";
import { Collapse, Nav, NavbarBrand, NavbarToggler, NavItem, NavLink, Navbar } from "reactstrap";

export const AppNavbar = () => {
  return (
    <Navbar color="light" expand="md" light>
      <NavbarBrand href="/">IMentor</NavbarBrand>
      <NavbarToggler onClick={function noRefCheck() {}} />
      <Collapse navbar>
        <Nav className="me-auto" navbar>
          <NavItem>
            <NavLink href="/profile">
              Профиль
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink href="/register">
              Регистрация
            </NavLink>
          </NavItem>
        </Nav>
      </Collapse>
    </Navbar>
  );
};
