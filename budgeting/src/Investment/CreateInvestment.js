import React, { useState } from 'react';
import axios from 'axios';
import './CreateInvestment.css';

const CreateInvestment = () => {
  const [investment, setInvestment] = useState({
    initialInvestment: 0,
    annualInterestRate: 0,
    compoundingFrequency: 0,
    investmentDuration: 0,
  });
  const [futureValue, setFutureValue] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setInvestment({
      ...investment,
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

      const response = await axios.post(`http://localhost:8081/investment/ForUser/${userId}/create`, investment);
      console.log('Future Value calculated:', response.data.futureValue);

      // Update the future value state with the calculated value
      setFutureValue(response.data.futureValue);
    } catch (error) {
      console.error('Error calculating Future Value:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Calculate Future Value of Investment</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="initialInvestment">Initial Investment</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="initialInvestment"
            name="initialInvestment"
            value={investment.initialInvestment}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="annualInterestRate">Annual Interest Rate (%)</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="annualInterestRate"
            name="annualInterestRate"
            value={investment.annualInterestRate}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="compoundingFrequency">Compounding Frequency</label>
          <input
            type="number"
            className="form-control"
            id="compoundingFrequency"
            name="compoundingFrequency"
            value={investment.compoundingFrequency}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="investmentDuration">Investment Duration (in years)</label>
          <input
            type="number"
            className="form-control"
            id="investmentDuration"
            name="investmentDuration"
            value={investment.investmentDuration}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Calculate Future Value
        </button>
      </form>
      {futureValue !== null && (
        <div className="mt-4">
          <p><strong>Calculated Future Value:</strong> {futureValue}</p>
        </div>
      )}
    </div>
  );
};

export default CreateInvestment;
