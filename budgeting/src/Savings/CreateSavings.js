import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './CreateSavings.css';

const CreateSavings = () => {
  const [savings, setSavings] = useState({
    netSavings: 0,
  });

  const [userId, setUserId] = useState(''); // To capture user ID input
  const [createdMessage, setCreatedMessage] = useState(''); // To display the "Savings created" message

  useEffect(() => {
    // Fetch the user ID from local storage when the component mounts
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      setUserId(storedUserId);
    }
  }, []); // The empty array [] ensures this effect runs only once when the component mounts

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSavings({
      ...savings,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Check if user ID is entered
      if (!userId) {
        console.error('User ID is required.');
        return;
      }

      const response = await axios.post(
        `http://localhost:8081/savings/forUser/${userId}/create`,
        savings
      );

      console.log('Savings created:', response.data);

      // Display the "Savings created" message
      setCreatedMessage('Savings created successfully.');

      // Display the net savings received in the response
      if (response.data.netSavings !== undefined) {
        setSavings({
          ...savings,
          netSavings: response.data.netSavings,
        });
      }
    } catch (error) {
      console.error('Error creating savings:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Create Savings</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="netSavings">Net Savings</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="netSavings"
            name="netSavings"
            value={savings.netSavings}
            onChange={handleChange}
            disabled
          />
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Create Savings
        </button>
      </form>

      {createdMessage && <p className="text-success mt-3">{createdMessage}</p>}
    </div>
  );
};

export default CreateSavings;
