import React from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  return (
    <div>
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <Link to="/" class="navbar-brand">
            Budget
          </Link>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item dropdown">
                <a
                  class="nav-link dropdown-toggle"
                  href="#"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Calculations
                </a>
                <ul class="dropdown-menu">
                  <li>
                    <Link className="nav-link" to="/loans">
                      Loan
                    </Link>
                  </li>
                  <li>
                    <Link className="nav-link" to="/investments">
                      Investments
                    </Link>
                  </li>
                  <li>
                    <Link className="nav-link" to="/expenses">
                      Expenses
                    </Link>
                  </li>
                  <li>
                    <Link className="nav-link" to="/budgets">
                      Budget
                    </Link>
                  </li>
                  <li>
                    <Link className="nav-link" to="/savings">
                      Savings
                    </Link>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
