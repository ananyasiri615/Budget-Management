// import React, { useState } from 'react';
// import axios from 'axios';

// const CreateLoan = () => {
//   const [loan, setLoan] = useState({
//     principal: 0,
//     annualInterestRate: 0,
//     numberOfMonths: 0,
//   });

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setLoan({
//       ...loan,
//       [name]: value,
//     });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       const response = await axios.post('http://localhost:8081/loan-emi/ForUser/{userId}/create', loan);
//       console.log('Loan EMI calculated:', response.data);
//       // Reset the form or perform any other action after successful loan calculation
//     } catch (error) {
//       console.error('Error calculating Loan EMI:', error);
//     }
//   };

//   return (
//     <div className="container">
//       <h2 className="mt-4 mb-4">Calculate Loan EMI</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label htmlFor="principal">Principal Amount</label>
//           <input
//             type="number"
//             step="0.01"
//             className="form-control"
//             id="principal"
//             name="principal"
//             value={loan.principal}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div className="form-group">
//           <label htmlFor="annualInterestRate">Annual Interest Rate (%)</label>
//           <input
//             type="number"
//             step="0.01"
//             className="form-control"
//             id="annualInterestRate"
//             name="annualInterestRate"
//             value={loan.annualInterestRate}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div className="form-group">
//           <label htmlFor="numberOfMonths">Number of Months</label>
//           <input
//             type="number"
//             className="form-control"
//             id="numberOfMonths"
//             name="numberOfMonths"
//             value={loan.numberOfMonths}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <button type="submit" className="btn btn-primary mt-3">
//           Calculate Loan EMI
//         </button>
//       </form>
//     </div>
//   );
// };

// export default CreateLoan;
import React, { useState } from 'react';
import axios from 'axios';

const CreateLoan = () => {
  const [loan, setLoan] = useState({
    principal: 0,
    annualInterestRate: 0,
    numberOfMonths: 0,
  });
  const [emi, setEmi] = useState(null); // State to store calculated EMI

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLoan({
      ...loan,
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

      const response = await axios.post(`http://localhost:8081/loan-emi/ForUser/${userId}/create`, loan);
      console.log('Loan EMI calculated:', response.data);

      // Update the EMI state with the calculated value
      setEmi(response.data.emi);
    } catch (error) {
      console.error('Error calculating Loan EMI:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Calculate Loan EMI</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="principal">Principal Amount</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="principal"
            name="principal"
            value={loan.principal}
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
            value={loan.annualInterestRate}
            onChange={handleChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="numberOfMonths">Number of Months</label>
          <input
            type="number"
            className="form-control"
            id="numberOfMonths"
            name="numberOfMonths"
            value={loan.numberOfMonths}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary mt-3">
          Calculate Loan EMI
        </button>
      </form>
      {emi !== null && (
        <div className="mt-4">
          <p><strong>Calculated EMI Per Month:</strong> {emi}</p>
        </div>
      )}
    </div>
  );
};

export default CreateLoan;


