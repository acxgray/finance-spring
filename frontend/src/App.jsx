import { Route, Routes } from "react-router-dom"
import Home from "./pages/Home"
import Error from "./pages/Error"
import Transaction from "./pages/Transaction"
import Bills from "./pages/Bills"

function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} index />
        <Route path="/transactions" element={<Transaction />} />
        <Route path="/bills" element={<Bills />} />
        <Route path="*" element={<Error />} errorElement={<Error />} />
      </Routes>
    </>
  )
}

export default App
