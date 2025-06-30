import React from "react";
import Header from "../components/Header";
import { Col, Container, Row } from "react-bootstrap";

const Error = () => {
  return (
    <>
      <Header />
      <Container>
        <Row>
          <Col lg="12"><h1>Error</h1><p>The requested page could not be found.</p></Col>
        </Row>
      </Container>
    </>
  );
};

export default Error;
