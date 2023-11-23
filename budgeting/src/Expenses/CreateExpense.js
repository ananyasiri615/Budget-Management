// import React, { useState } from 'react';
// import axios from 'axios';
// import './CreateExpense.css';

// const CreateExpense = () => {
//   const [expense, setExpense] = useState({
//     totalExpensesInCategory: 0,
//     expendingPercentage: 0,
//   });

//   const [categoryId, setCategoryId] = useState(''); // To capture category input
//   const [createdMessage, setCreatedMessage] = useState(''); // To display the "Expense created" message

//   const handleChange = (e) => {
//     const { name, value } = e.target;
//     setExpense({
//       ...expense,
//       [name]: value,
//     });
//   };

//   const handleSubmit = async (e) => {
//     e.preventDefault();

//     try {
//       // Fetch the userId from local storage
//       const userId = localStorage.getItem('userId');

//       if (!userId) {
//         console.error('User ID not found in local storage.');
//         return;
//       }

//       // Check if categoryId is entered
//       if (!categoryId) {
//         console.error('Category ID is required.');
//         return;
//       }

//       const response = await axios.post(
//         `http://localhost:8081/expenses/forUser/${userId}/withCategory/${categoryId}/create`,
//         expense
//       );

//       console.log('Expense created:', response.data);

//       // Display the "Expense created" message
//       setCreatedMessage('Expense created successfully.');

//             // Display the expendingPercentage received in the response
//             if (response.data.expendingPercentage) {
//               setExpense({
//                 ...expense,
//                 expendingPercentage: response.data.expendingPercentage,
//               });
//             }

//       // Clear the form
//       // setExpense({
//       //   totalExpensesInCategory: 0,
//       //   expendingPercentage: 0,
//       // });
//     } catch (error) {
//       console.error('Error creating expense:', error);
//     }
//   };

//   return (
//     <div className="container">
//       <h2 className="mt-4 mb-4">Create Expense</h2>
//       <form onSubmit={handleSubmit}>
//         <div className="form-group">
//           <label htmlFor="totalExpensesInCategory">Total Expenses in Category</label>
//           <input
//             type="number"
//             step="0.01"
//             className="form-control"
//             id="totalExpensesInCategory"
//             name="totalExpensesInCategory"
//             value={expense.totalExpensesInCategory}
//             onChange={handleChange}
//             required
//           />
//         </div>
//         <div className="form-group">
//           <label htmlFor="categoryId">Category ID</label>
//           <input
//             type="text"
//             className="form-control"
//             id="categoryId"
//             value={categoryId}
//             onChange={(e) => setCategoryId(e.target.value)}
//             required
//           />
//         </div>
//         <button type="submit" className="btn btn-primary mt-3">
//           Create Expense
//         </button>
//       </form>

//       {createdMessage && <p className="text-success mt-3">{createdMessage}</p>}
//       {expense.expendingPercentage !== undefined && (
//         <p className="mt-3">
//           Expending Percentage: {expense.expendingPercentage}%
//         </p>
//       )}
//     </div>
//   );
// };

// export default CreateExpense;
import React, { useState } from 'react';
import axios from 'axios';
import './CreateExpense.css';

const CreateExpense = () => {
  const [expense, setExpense] = useState({
    totalExpensesInCategory: 0,
    expendingPercentage: 0,
  });

  const [categoryId, setCategoryId] = useState(''); // To capture category input
  const [createdMessage, setCreatedMessage] = useState(''); // To display the "Expense created" message

  const handleChange = (e) => {
    const { name, value } = e.target;
    setExpense({
      ...expense,
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
        `http://localhost:8081/expenses/forUser/${userId}/withCategory/${categoryId}/create`,
        expense
      );

      console.log('Expense created:', response.data);

      // Display the "Expense created" message
      setCreatedMessage('Expense created successfully.');

      // Display the expendingPercentage received in the response
      if (response.data.expendingPercentage) {
        setExpense({
          ...expense,
          expendingPercentage: response.data.expendingPercentage,
        });
      }

      // Clear the form (you can clear the form if needed)
      setCategoryId('');
    } catch (error) {
      console.error('Error creating expense:', error);
    }
  };

  return (
    <div className="container">
      <h2 className="mt-4 mb-4">Create Expense</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="totalExpensesInCategory">Total Expenses in Category</label>
          <input
            type="number"
            step="0.01"
            className="form-control"
            id="totalExpensesInCategory"
            name="totalExpensesInCategory"
            value={expense.totalExpensesInCategory}
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
          Create Expense
        </button>
      </form>

      {createdMessage && <p className="text-success mt-3">{createdMessage}</p>}
      {expense.expendingPercentage !== undefined && (
        <p className="mt-3">
          Expending Percentage: {expense.expendingPercentage}%
        </p>
      )}
    </div>
  );
};

export default CreateExpense;
