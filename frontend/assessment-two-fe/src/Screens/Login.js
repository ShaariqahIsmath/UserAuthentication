import React, { useState } from "react";
import axios from "axios";
import jwt_decode from "jwt-decode";
import { useNavigate } from "react-router-dom";
import swal from "sweetalert";
import "./Styles.css"

function Login () {

    const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const navigate = useNavigate();
	const url = "http://localhost:8080/";

	const onHandleLoginSubmit = async () => {
		if (email === "" || password === "" || "") {
			swal("Error!", "please fill the fields!", "error");
		} else {
			async function login(email, password) {
				try {
					const qs = require("qs");
					const response = await axios.post(
						url + "login",
						qs.stringify({ email: email, password: password })
					);
					const token = response.data.access_token;
					const decoded = jwt_decode(token);
					if (response.data.accessToken) {
						localStorage.setItem("1", "true");
						localStorage.setItem("2", decoded);
					}

					if (decoded.accountType[0] === "ADMIN") {
						navigate("/admin");
					} else {
						navigate("/student");
					}
				} catch (err) {
					swal(
						"Error!"
					);
				}
			}
			login(email, password);
		}
	};

   
    return(
        <div className="form">
        <br></br>
            
        <div className="form-body">
            <div className="email">
            <label className="labelE">Email: </label>
            <input className="form_inputTwo"
                name="email"
                type="text"
                placeholder="Enter email"
                onChange={(e) => {
                    setEmail(e.target.value);
                }}
                required
        /> 

            </div><br></br><br></br>

            <div className="pw">
            <label className="labelP">Password: </label>
            <input className="form_inputTwo"
                name="password"
                type="password"
                placeholder="Enter password"
                onChange={(e) => {
                    setPassword(e.target.value);
                }}
                required
        /> 

            </div><br></br><br></br>


            <div >
                 <button className="buttonLogin" onClick={onHandleLoginSubmit} type="submit"> Login</button>
            </div>
         
        </div>
       
        <br></br>
        

        
        

    </div>
    );
};

export default Login;
