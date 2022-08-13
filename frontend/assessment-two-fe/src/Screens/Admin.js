import React, { useEffect, useState } from 'react';
import axios from "axios";
import "./Styles.css";
import { Link } from "react-router-dom";

function Admin() {

    const [userList, setUserList] = useState([]);

    const API_URL = "http://localhost:8080/api/users"


    useEffect(() => {
        const getUsers = () => {
            axios.get(API_URL).then((response) => {
                setUserList(Array.from(response.data.data));
                console.log(response.data.data)
            });
        };

        getUsers();
    }, []);



    return (
        <>
            <br></br>
            <div className="users">
                <Link to="/addUser">
                    <button id="addUser"> + Add User </button>
                </Link>

                <div className="userList">
                    {userList.map((user) => {
                        return (
                            <div >
                                <div key={user.id} >

                                    <h5>
                                        {user.firstName} {user.lastName}
                                    </h5>
                                    <p >
                                        Account Type: {user.accountType}
                                    </p>
                                    <p >Email: {user.email}</p>
                                    <p >
                                        DOB: {user.dateOfBirth}
                                    </p>
                                    <p >
                                        Mobile No: {user.mobileNumber}
                                    </p>
                                        <Link to="/profile">
                                            <button id="edit">Edit User</button>
                                        </Link>
                                    
                                    <hr></hr>

                                </div>
                            </div>
                        );
                    })}


                </div>

            </div>
        </>

    )

}



export default Admin;   
