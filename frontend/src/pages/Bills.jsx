import { useEffect, useState } from "react";
import Header from "../components/Header";
import { Button, Card, Col, Container, Row, Table } from "react-bootstrap";
import { getBills } from "../utils/FetchFormData";
import { FaPenAlt, FaTrash } from "react-icons/fa";

const Bills = () => {
  const [bills, setBills] = useState([]);

  const getData = async () => {
    setBills(await getBills());
  };

  useEffect(() => {
    getData();
  }, []);

  return (
    <>
      <Header />
      <Container className="mt-5">
        <Row>
          <Col lg={12}>
            <Card className="rounded-0">
              <Card.Header className="bg-secondary text-white rounded-0">
                <Card.Title className="rounded-0">Test</Card.Title>
              </Card.Header>
              <Card.Body>
                {/* <Button><FaPlus /></Button> */}
                <Table striped bordered hover responsive width={"100%"}>
                  <thead>
                    <tr>
                      <th>Bill Id</th>
                      <th>Transaction Name</th>
                      <th>Amount</th>
                      <th>Status</th>
                      <th>Payment Date</th>
                      <th>Payment Amount</th>
                      <th>Payment Reference</th>
                      <th>Payment Method</th>
                      <th width="15%">Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                    {Array.isArray(bills) && bills.length > 0 ? (
                      bills.map((bill) => (
                        <tr>
                          <td>{bill.id}</td>
                          <td>{bill.transaction.note}</td>
                          <td>{bill.transaction.amount}</td>
                          <td>{bill.transaction.status}</td>
                          <td>{bill.paid_date}</td>
                          <td>
                            {bill.paid_amount === 0 &&
                            bill.transaction.status === "UNPAID"
                              ? ""
                              : bill.paid_amount}
                          </td>
                          <td>{bill.paid_ref}</td>
                          <td>{bill.paid_method}</td>
                          <td>
                            <Button size="sm" variant="info" className="me-1">
                              <FaPenAlt />
                            </Button>
                            <Button size="sm" variant="danger">
                              <FaTrash color="white" />
                            </Button>
                          </td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td
                          colSpan={9}
                          className="text-center small fw-semibold"
                        >
                          No Records Found
                        </td>
                      </tr>
                    )}
                  </tbody>
                </Table>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>

      {/* View Modal */}

      {/* Update Modal */}
    </>
  );
};

export default Bills;
