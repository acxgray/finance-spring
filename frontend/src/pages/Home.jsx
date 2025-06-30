import { Card, Col, Container, Placeholder, Row, Table } from "react-bootstrap";
import Header from "../components/Header";

import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
} from "chart.js";
import { Pie, Line } from "react-chartjs-2";

ChartJS.register(
  ArcElement,
  Tooltip,
  Legend,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement
);

export const pieData = {
  labels: ["Income", "Expenses"],
  datasets: [
    {
      label: "Total Amount (PHP)",
      data: [14000, 35000],
      backgroundColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
      borderColor: ["rgba(255, 99, 132, 0.2)", "rgba(54, 162, 235, 0.2)"],
      borderWidth: 0,
    },
  ],
};

export const lineOptions = {
  responsive: true,
  plugins: {
    legend: {
      position: "bottom",
    },
    title: {
      display: true,
      text: "Transactions",
    },
  },
};

const labels = ["January", "February", "March", "April", "May", "June", "July"];

export const lineData = {
  labels,
  datasets: [
    {
      label: "No of Transactions",
      data: [250, 17, 1, 6, 7, 8, 10],
      borderColor: "rgb(255, 99, 132)",
      backgroundColor: "rgba(255, 99, 132, 0.5)",
    },
  ],
};

const Home = () => {
  // Parts:
  // Pie Graph: Total Expense/Income
  // Bar: Expenses Income

  return (
    <>
      <Header />
      <Container className="mt-5">
        <Row>
          <Col lg="3">
            <Card>
              <Card.Body>
                <Pie data={pieData} />
              </Card.Body>
            </Card>
          </Col>
          <Col lg="4">
            <Card>
              <Card.Body>
                <Line options={lineOptions} data={lineData} />
              </Card.Body>
            </Card>
          </Col>
          <Col lg="5">
            <Card>
              <Card.Body>
                <Card.Title>Transactions</Card.Title>
                <Row className="mt-4">
                  <Col lg="12">
                    <Table variant="sm" striped hover bordered responsive width="100%">
                      <thead>
                        <tr>
                          <th>Date</th>
                          <th>Transaction</th>
                          <th>Amount</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td className="small">2025-06-20 11:00 PM</td>
                          <td className="small">GCASH</td>
                          <td className="small">1500.00</td>
                        </tr>
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
