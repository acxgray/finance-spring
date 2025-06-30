import { Container, Nav, Navbar, NavDropdown } from "react-bootstrap";
import { NavLink } from "react-router-dom";

const Header = () => {
  return (
    <Navbar expand="lg" bg="secondary" data-bs-theme="dark">
      <Container>
        <Navbar.Brand className="text-white">Tracker</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto me-auto">
            <Nav.Link as={NavLink} to="/" >Home</Nav.Link>
            <Nav.Link as={NavLink} to="/transactions">Transactions</Nav.Link>
            <Nav.Link as={NavLink} to="/bills">Bills</Nav.Link>
            <Nav.Link>Link</Nav.Link>
            {/* <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item>Action</NavDropdown.Item>
              <NavDropdown.Item>Another action</NavDropdown.Item>
              <NavDropdown.Item>Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item>Separated link</NavDropdown.Item>
            </NavDropdown> */}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default Header;
