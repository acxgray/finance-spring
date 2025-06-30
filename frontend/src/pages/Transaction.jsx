import React, { Suspense, useEffect, useState } from "react";
import Header from "../components/Header";
import {
  Badge,
  Button,
  Card,
  Col,
  Container,
  Form,
  Modal,
  Row,
  Table,
} from "react-bootstrap";
import { axiosInstance } from "../utils/AxiosInstance";
import {
  getCategories,
  getTransactionById,
  getTransactions,
  getUser,
} from "../utils/FetchFormData";

const Transaction = () => {
  const [transactions, setTransactions] = useState([]);
  const [categories, setCategories] = useState([]);
  const [user, setUser] = useState({});
  const [showCreate, setShowCreate] = useState(false);
  const [showUpdate, setShowUpdate] = useState(false);

  const handleCreateClose = () => setShowCreate(false);
  const handleUpdateClose = () => setShowUpdate(false);

  const getData = async () => {
    setTransactions(await getTransactions());
    setCategories(await getCategories());
    setUser(await getUser());
  };

  useEffect(() => {
    getData();
  }, []);

  // Form
  const [newTransaction, setNewTransaction] = useState({
    note: "",
    amount: 0,
    category: { id: "" },
    user: { id: "" },
    status: "",
  });

  const [editTransaction, setEditTransaction] = useState({
    note: "",
    amount: 0,
    category: { id: "" },
    user: { id: "" },
    status: "",
  });

  const resetForm = () => {
    setNewTransaction({
      note: "",
      amount: 0,
      category: { id: "" },
      user: { id: "" },
      status: "",
    });
  };

  const fetchEditData = async (id) => {
    // fetch data
    const data = await getTransactionById(id);
    if (!data) {
      alert("No ID Provided");
      return;
    }

    setEditTransaction({
      note: data.note,
      amount: data.amount,
      category: { id: data.category.id },
      user: { id: data.user.id },
      status: data.status,
    });

    setShowUpdate(true);
  };

  const handleCreateChange = (e) => {
    if (e.target.name === "user") {
      setNewTransaction({
        ...newTransaction,
        [e.target.name]: { ...newTransaction.user, id: e.target.value },
      });
    } else if (e.target.name === "category") {
      setNewTransaction({
        ...newTransaction,
        [e.target.name]: { ...newTransaction.category, id: e.target.value },
      });
    } else {
      setNewTransaction({ ...newTransaction, [e.target.name]: e.target.value });
    }
  };

  const handleEditChange = (e) => {
    if (e.target.name === "user") {
      setEditTransaction({
        ...editTransaction,
        [e.target.name]: { ...editTransaction.user, id: e.target.value },
      });
    } else if (e.target.name === "category") {
      setEditTransaction({
        ...editTransaction,
        [e.target.name]: { ...editTransaction.category, id: e.target.value },
      });
    } else {
      setEditTransaction({
        ...editTransaction,
        [e.target.name]: e.target.value,
      });
    }
  };

  const handleCreateSubmit = async (e) => {
    e.preventDefault();
    console.log(newTransaction);
    try {
      const saveData = await axiosInstance.post(
        "/api/v1/transactions",
        newTransaction
      );
      if (saveData) {
        alert("success");
        resetForm();
        getData();
        handleCreateClose();
      } else {
        alert("failed");
      }
    } catch (error) {
      console.log(error);
    }

    resetForm();
  };

  const handleRemove = async (e, id) => {
    e.preventDefault();

    try {
      if (confirm("Do you want to remove this transaction?")) {
        const removeData = await axiosInstance.delete(
          `/api/v1/transactions/${id}`
        );
        if (removeData) {
          alert("success");
          getData();
        } else {
          alert("error");
        }
      }
    } catch (error) {
      console.log("Error on Remove: " + error);
    }
  };

  return (
    <>
      <Header />

      <Container className="mt-5">
        <Row>
          <Col>
            <Card className="rounded-0 shadow-lg border-0">
              <Card.Header className="bg-secondary text-white rounded-0">
                Table
              </Card.Header>
              <Card.Body>
                <Button
                  className="mb-2"
                  variant="primary"
                  onClick={() => setShowCreate(true)}
                >
                  Create Transaction
                </Button>
                <Table striped hover bordered responsive width="100%">
                  <thead>
                    <tr>
                      <th>#</th>
                      <th>Transaction</th>
                      <th>Amount</th>
                      <th>Category</th>
                      <th width="8%">Status</th>
                      <th>Created Date</th>
                      <th width="15%">Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {transactions.length > 0 ? (
                      Array.isArray(transactions) &&
                      transactions.map((transaction) => (
                        <tr key={transaction.id}>
                          <td>{transaction.id}</td>
                          <td>{transaction.note}</td>
                          <td>{transaction.amount}</td>
                          <td className="fw-bold">
                            {transaction.category.title}
                          </td>
                          <td>
                            <Badge
                              bg={
                                transaction.status === "UNPAID"
                                  ? "danger"
                                  : transaction.status === "PAID"
                                  ? "success"
                                  : "secondary"
                              }
                            >
                              {transaction.status}
                            </Badge>
                          </td>
                          <td>{transaction.created_at}</td>
                          <td className="text-center">
                            <Button
                              className="me-1 rounded-0 text-white"
                              size="sm"
                              variant="info"
                              onClick={() => fetchEditData(transaction.id)}
                            >
                              Update
                            </Button>
                            <Button
                              className="rounded-0"
                              size="sm"
                              variant="danger"
                              onClick={(e) => handleRemove(e, transaction.id)}
                            >
                              Remove
                            </Button>
                          </td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td
                          colSpan={7}
                          className="text-center fw-medium text-secondary"
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

      {/* Create Modal */}
      <Modal
        show={showCreate}
        onHide={handleCreateClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Create Transaction</Modal.Title>
        </Modal.Header>
        <Form method="POST" onSubmit={(e) => handleCreateSubmit(e)}>
          <Modal.Body>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">User</Form.Label>
              <Form.Select
                name="user"
                value={newTransaction.user.id}
                onChange={handleCreateChange}
              >
                <option>Select an User</option>
                <option value={user.id}>
                  {user.first_name +
                    " " +
                    user.last_name +
                    " (" +
                    user.email +
                    ")"}
                </option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Note</Form.Label>
              <Form.Control
                type="text"
                name="note"
                value={newTransaction.note}
                onChange={handleCreateChange}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Amount</Form.Label>
              <Form.Control
                type="text"
                name="amount"
                value={newTransaction.amount}
                onChange={handleCreateChange}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Status</Form.Label>
              <Form.Select
                name="status"
                value={newTransaction.status}
                onChange={handleCreateChange}
              >
                <option>Select Status</option>
                <option value="UNPAID">Unpaid</option>
                <option value="PAID">Paid</option>
                <option value="PENDING">Pending</option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Category</Form.Label>
              <Form.Select
                name="category"
                value={newTransaction.category.id}
                onChange={handleCreateChange}
              >
                <option>Select Category</option>
                {Array.isArray(categories) &&
                  categories.map((category) => (
                    <option value={category.id}>{category.title}</option>
                  ))}
              </Form.Select>
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCreateClose}>
              Close
            </Button>
            <Button variant="primary" type="submit">
              Save
            </Button>
          </Modal.Footer>
        </Form>
      </Modal>

      {/* Update Modal */}
      <Modal
        show={showUpdate}
        onHide={handleUpdateClose}
        backdrop="static"
        keyboard={false}
      >
        <Modal.Header closeButton>
          <Modal.Title>Edit Transaction</Modal.Title>
        </Modal.Header>
        <Form method="PUT">
          <Modal.Body>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">User</Form.Label>
              <Form.Select
                name="user"
                value={editTransaction.user.id}
                onChange={handleEditChange}
              >
                <option>Select an User</option>
                <option value={user.id}>
                  {user.first_name +
                    " " +
                    user.last_name +
                    " (" +
                    user.email +
                    ")"}
                </option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Note</Form.Label>
              <Form.Control
                type="text"
                name="note"
                value={editTransaction.note}
                onChange={handleEditChange}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Amount</Form.Label>
              <Form.Control
                type="text"
                name="amount"
                value={editTransaction.amount}
                onChange={handleEditChange}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Status</Form.Label>
              <Form.Select
                name="status"
                value={editTransaction.status}
                onChange={handleEditChange}
              >
                <option>Select Status</option>
                <option value="UNPAID">Unpaid</option>
                <option value="PAID">Paid</option>
                <option value="PENDING">Pending</option>
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label className="fw-semibold">Category</Form.Label>
              <Form.Select
                name="category"
                value={editTransaction.category.id}
                onChange={handleEditChange}
              >
                <option>Select Category</option>
                {Array.isArray(categories) &&
                  categories.map((category) => (
                    <option value={category.id}>{category.title}</option>
                  ))}
              </Form.Select>
            </Form.Group>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleUpdateClose}>
              Close
            </Button>
            <Button variant="primary">Save</Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </>
  );
};

export default Transaction;
