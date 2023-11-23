import React, { useState } from 'react';
import axios from 'axios';
import './CreateUser.css'; // Import the CSS file

const CreateUser = () => {
  const [user, setUser] = useState({
    username: '',
    email: '',
    password: '',
    totalFixedIncome: 0,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(`http://localhost:8081/users/create`, user);

      // Store the userId in local storage
      localStorage.setItem('userId', response.data.userId);

      console.log('User created:', response.data);
      // Reset the form or perform any other action after successful user creation
    } catch (error) {
      console.error('Error creating user:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Create User</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            type="text"
            className="form-control"
            id="username"
            name="username"
            value={user.username}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            value={user.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="totalFixedIncome">Total Fixed Income</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="totalFixedIncome"
            name="totalFixedIncome"
            value={user.totalFixedIncome}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Create User
        </button>
      </form>
    </div>
  );
};

export default CreateUser;
