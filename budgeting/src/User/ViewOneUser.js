import React, { useEffect, useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

const ViewOneUser = () => {
  const [user, setUser] = useState({});
  const location = useLocation();
  const { userId } = location.state;
  const navigate = useNavigate();

  const getUser = (userId) => {
    axios
      .get(`http://localhost:8081/users/id/${userId}`)
      .then((response) => {
        if (response.data) {
          setUser(response.data);
        } else {
          console.log("User not found");
        }
      })
      .catch((err) => console.log(err));
  };

  useEffect(() => {
    getUser(userId);
  }, [userId]);

  return (
    <>
      <div className="container-md">
        <div className="container-md mt-3 px-5 text-center">
          <div className="container shadow border pb-3 bg-body-tertiary">
            <p className="fw-semibold mt-3 h2 text-secondary-emphasis text-center">
              User
            </p>
            <hr />
            <div className="row mb-3">
              <div className="col">
                <p className="fw-semibold   text-end">ID : </p>
              </div>
              <div className="col">
                <p className="fw-semibold   text-start">{user.userId} </p>
              </div>
            </div>
            <div className="row mb-3">
              <div className="col">
                <p className="fw-semibold   text-end">Username : </p>
              </div>
              <div className="col">
                <p className="fw-semibold   text-start">{user.username} </p>
              </div>
            </div>
            <div className="row mb-3">
              <div className="col">
                <p className="fw-semibold   text-end">Email : </p>
              </div>
              <div className="col">
                <p className="fw-semibold   text-start">{user.email} </p>
              </div>
            </div>
            <div className="row mb-3">
              <div className="col">
                <p className="fw-semibold   text-end">Total Fixed Income : </p>
              </div>
              <div className="col">
                <p className="fw-semibold   text-start">{user.totalFixedIncome} </p>
              </div>
            </div>
            <button
              className="btn btn-dark fw-semibold btn-lg btn-block text-light"
              type="button"
              onClick={() => {
                navigate("/Admin/UpdateUser", { state: { user } });
              }}
            >
              Edit User
            </button>
          </div>
        </div>
      </div>
    </>
  );
};

export default ViewOneUser;
