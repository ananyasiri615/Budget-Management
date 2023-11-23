import React, { useState, useEffect } from "react";
import { Navigate, useNavigate } from "react-router-dom";
import axios from "axios";

function User() {
  const [users, setUsers] = useState([]);
  const [tableItems, setTableItems] = useState([]);
  const navigate = useNavigate();
  const [newUserData, setNewUserData] = useState({
    username: "",
    email: "",
    // Add other user-related fields as needed
  });

  useEffect(() => {
    axios
      .get("http://localhost:8081/users/all")
      .then((response) => {
        console.log(response.data);
        setTableItems(response.data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  const handleViewUser = (userId) => {
    // Navigate to the ViewOneUser component and pass the userId as state
    navigate("/ViewOneUser", { state: { userId } });
  };

  return (
    <div className="wrapper">
      <div className="container user-admin-container">
        <div className="row">
          <div className="col-md-10 mx-auto">
            <h2>List Users</h2>
            <div className="table-responsive">
              <table className="table table-striped ">
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>totalFixedIncome</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {tableItems.map((user) => (
                    <tr key={user.userId}>
                      <th scope="row">{user.userId}</th>
                      <td>{user.username}</td>
                      <td>{user.email}</td>
                      <td>{user.totalFixedIncome}</td>
                      <th scope="col">
                        <button
                          type="button"
                          onClick={() => handleViewUser(user.userId)}
                          className="btn btn-outline-success"
                        >
                          View
                        </button>
                      </th>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default User;
