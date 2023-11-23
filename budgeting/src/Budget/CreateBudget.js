import React, { useState } from 'react';
import axios from 'axios';
import './CreateBudget.css';

const CreateBudget = () => {
  const [budget, setBudget] = useState({
    totalExpensesInCategory: 0,
    budgetedAmount: 0,
  });

  const [categoryId, setCategoryId] = useState(''); // To capture category input
  const [createdMessage, setCreatedMessage] = useState(''); // To display the "Budget created" message

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBudget({
      ...budget,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Fetch the userId from local storage
      const userId = localStorage.getItem('userId');

      if (!userId) {
        console.error('User ID not found in local storage.');
        return;
      }

      // Check if categoryId is entered
      if (!categoryId) {
        console.error('Category ID is required.');
        return;
      }

      const response = await axios.post(
        `http://localhost:8081/budget/forUser/${userId}/withCategory/${categoryId}/create`,
        budget
      );

      console.log('Budget created:', response.data);

      // Display the "Budget created" message
      setCreatedMessage('Budget created successfully.');

      // Display the spendingPercentage received in the response
      if (response.data.spendingPercentage) {
        setBudget({
          ...budget,
          spendingPercentage: response.data.spendingPercentage,
        });
      }

    //   // Clear the form
    //   setBudget({
    //     totalExpensesInCategory: 0,
    //     budgetedAmount: 0,
    //   });
    } catch (error) {
      console.error('Error creating budget:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Create Budget</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="budgetedAmount">Budgeted Amount</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="budgetedAmount"
            name="budgetedAmount"
            value={budget.budgetedAmount}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="categoryId">Category ID</label>
          <input
            type="text"
            className="form-control"
            id="categoryId"
            value={categoryId}
            onChange={(e) => setCategoryId(e.target.value)}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Create Budget
        </button>
      </form>

      {createdMessage && <p className="text-success mt-3">{createdMessage}</p>}
      {budget.spendingPercentage !== undefined && (
        <p className="mt-3">
          Spending Percentage: {budget.spendingPercentage}%
        </p>
      )}
    </div>
  );
};

export default CreateBudget;
