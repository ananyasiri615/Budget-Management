import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import CreateUser from './User/CreateUser';
import CreateLoan from "./Loan/CreateLoan";
import CreateInvestment from "./Investment/CreateInvestment";
import CreateExpense from "./Expenses/CreateExpense";
import CreateBudget from "./Budget/CreateBudget";
import CreateSavings from "./Savings/CreateSavings";
import Navbar from "./Navbar";
import User from "./User/User";
import ViewOneUser from "./User/ViewOneUser";

function App() {
  return (
    <div>
      <BrowserRouter>
      <Navbar /> {/* Navbar component is at the top */}
        <Routes>
          <Route path="/" element={<CreateUser />} />
          <Route path="/loans" element={<CreateLoan />} />
          <Route path="/investments" element={<CreateInvestment />} />
          <Route path="/expenses" element={<CreateExpense />} />
          <Route path="/budgets" element={<CreateBudget />} />
          <Route path="/savings" element={<CreateSavings />} />
          <Route path="/allusers" element={<User />} />
          <Route path="/ViewOneUser" element={<ViewOneUser />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
