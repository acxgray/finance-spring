import { Card, Col, Container, Placeholder, Row, Table } from "react-bootstrap";
import Header from "../components/Header";
import { useState, useEffect } from "react";
import { axiosInstance } from "../utils/AxiosInstance";
import { getTransactions } from "../utils/FetchFormData";
import { BarChart, PieChart } from "@mui/x-charts";

const Home = () => {
  const [transactionData, setTransactionData] = useState({
    income: 0,
    expense: 0,
    total: [],
  });

  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    async function getData() {
      const response = await axiosInstance.get(
        "/api/v1/transactions/dashboard?user=1"
      );

      if (response) {
        setTransactionData({
          income: response.data.total_transaction_income,
          expense: response.data.total_transaction_expenses,
          total: response.data.total_transactions,
        });
      }
    }

    async function getAllTransactions() {
      setTransactions(await getTransactions());
    }
    getData();
    getAllTransactions();
  }, []);

  // format amount
  const formatter = new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "PHP",
  });

  return (
    <>
      <Header />
      <Container className="mt-5">
        <Row>
          <Col lg="3">
            <Card>
              <Card.Body>
                <PieChart
                  series={[
                    {
                      data: [
                        {
                          id: 0,
                          value:
                            transactionData.income != 0
                              ? transactionData.income
                              : 0,
                          label: "Income",
                          color: "#16C47F",
                        },
                        {
                          id: 1,
                          value:
                            transactionData.expense != 0
                              ? transactionData.expense
                              : 0,
                          label: "Expenses",
                          color: "#F93827",
                        },
                      ],
                      highlightScope: { fade: "global", highlight: "item" },
                      faded: {
                        innerRadius: 30,
                        additionalRadius: -30,
                        color: "gray",
                      },
                      paddingAngle: 5,
                      innerRadius: 60,
                      outerRadius: 80,
                    },
                  ]}
                  width={200}
                  height={200}
                />
              </Card.Body>
            </Card>
          </Col>
          <Col lg="4">
            <Card>
              <Card.Body>
                <BarChart
                  series={[{ data: transactionData.total.map((total) => total.total) }]}
                  height={290}
                  xAxis={[{ data: transactionData.total.map((total) => total.month), label: "Transactions Per Month", width: 60 }]}
                />
              </Card.Body>
            </Card>
          </Col>
          <Col lg="5">
            <Card>
              <Card.Body>
                <Card.Title>Transactions</Card.Title>
                <Row className="mt-4">
                  <Col lg="12">
                    <Table
                      variant="sm"
                      striped
                      hover
                      bordered
                      responsive
                      width="100%"
                    >
                      <thead>
                        <tr>
                          <th>Date</th>
                          <th>Transaction</th>
                          <th>Amount</th>
                        </tr>
                      </thead>
                      <tbody>
                        {Array.isArray(transactions) &&
                          transactions.map((transaction) => (
                            <tr key={transaction.id}>
                              <td>
                                {new Intl.DateTimeFormat("en-PH", {
                                  dateStyle: "full",
                                  timeStyle: "short",
                                  timeZone: "Asia/Manila",
                                }).format(transaction.created_date)}
                              </td>
                              <td>{transaction.note}</td>
                              <td>{formatter.format(transaction.amount)}</td>
                            </tr>
                          ))}
                      </tbody>
                    </Table>
                  </Col>
                </Row>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Home;
