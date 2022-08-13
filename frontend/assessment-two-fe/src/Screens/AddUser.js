import { Link, Routes, Route, useNavigate, Router} from "react-router-dom";
import React, { useState } from "react";
import 'animate.css';
import axios from "axios";
import { useHistory } from "react-router-dom";
import "./Styles.css"


const AddUser = () => {



    const[email, setEmail] = useState('');
    const[accountType, setAccountType] = useState('');
    const[user, setUser] = useState('');

    const url = "http://localhost:8080/api/users"
    

    const handleSubmit = (e) => {
        e.preventDefault();
        addingUser(email, accountType);
     };

     const addingUser = (email, accountType) => {
        axios.post(url, {
            email: email,
            accountType: accountType
        })
        .then((response) => {
            setUser(response.data);
        });
        setEmail("");
        setAccountType("");

     }

  
return(
    <div>
        <br></br>
        <form>
            <div >
            <p className="label"> Enter email to add user!</p>
            <input className="form_input"
                name="email"
                type="text"
                placeholder="Enter email"
                value={email}
				onChange={(e) => setEmail(e.target.value)}
                required
                
        /> 
            </div>
            <br></br>
            <br></br>

            <div className="accountType">
                <p id="pq">Select account type: </p>

                <div className="radio-button">
                <input type="radio" id="r1" name="admin" value={accountType}
				    onChange={(e) => setAccountType("ADMIN")}/>
                <label className="btn" htmlFor="r1">admin</label>

                <input type="radio" id="r2" name="student" value={accountType}
				    onChange={(e) => setAccountType("STUDENT")}  />
                <label className="btn" htmlFor="r2">student</label>
                </div>

            </div>

            <br></br>           
            <div className="button">
                 <button onClick={handleSubmit} type="submit">Add User</button>
              
            </div>
            <Link to="/admin">
            <button id="back">Back</button>
            </Link>

            <br></br>
       
         
        </form>    
        
    </div>
)
}

 export default AddUser;



