import React, { useState } from "react";
import './Profile.css';
import axios from "axios";


const UpdateUser = () => {

    const [firstName, setFirstName] = useState("");
	const [lastName, setLastName] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [dateOfBirth, setdateOfBirth] = useState("");
	const [mobileNumber, setMobileNumber] = useState("");
	const [accountType, setAccountType] = useState("");
    const API_URL = "http://localhost:8080/api/user";


    const[editUser, setEditUser]= useState();

    const handleSubmit = (e) => {
        e.preventDefault();
        updateUser(firstName, lastName, email, password, dateOfBirth, mobileNumber, accountType);
     };

    const updateUser = (id) => (
		firstName,
		lastName,
		email,
		password,
		dateOfBirth,
		mobileNumber,
        accountType,
		
	) => {
		axios
			.put(`${API_URL}${id}`, {
				firstName: firstName,
				lastName: lastName,
				email: email,
				password: password,
				dateOfBirth: dateOfBirth,
				mobileNumber: mobileNumber,
				accountType: accountType,
			})
			.then((response) => {
				setEditUser([response.data, ...editUser]);
			});
		setFirstName("");
		setLastName("");
		setEmail("");
		setPassword("");
		setdateOfBirth("");
		setMobileNumber("");
		setAccountType("");
	};
    // const [startDate, setStartDate] = useState(new Date());
    return (

        <>
            <br></br>

            <div className="profile">

                <p className="para">Profile Settings - admin/student</p>
                <div className="names">

                    <div className="fname">
                        <label className="labelName">  First Name</label>
                        <input className="form_inputThree"
                            name="firstName"
                            type="text"
                            placeholder="Enter First Name"
                            value={firstName}
							onChange={(e) => setFirstName(e.target.value)}
                            required />

                    </div><br></br>

                    <div className="lname">
                        <label className="labelName">Surname </label>
                        <input className="form_inputThree"
                            name="lastName"
                            type="text"
                            placeholder="Enter Last Name"
                            value={lastName}
							onChange={(e) => setLastName(e.target.value)}

                            required/>

                    </div><br></br>

                </div>

                <div className="personal">
                <div className="email">
                    <label className="labelEmail">Email: </label>
                    <input className="form_inputPersonal"
                        name="email"
                        type="text"
                        value={email}
						onChange={(e) => setEmail(e.target.value)}

                        /> 

                </div><br></br>


                <div className="number">
                    <label className="labelNum">Mobile Number: </label>
                    <input className="form_inputPersonal"
                        name="number"
                        type="text"
                        placeholder="Enter Mobile Number"
                        value={mobileNumber}
						onChange={(e) => setMobileNumber(e.target.value)}
                        required
                    />

                </div>
                <br></br>


                <div className="account">
                    <label className="labelDate">Date Of Birth: </label>
                    <input className="form_inputPersonal"
                        name="dob"
                        type="text"
                        placeholder="Enter Date of Birth"
                        value={dateOfBirth}
						onChange={(e) => setdateOfBirth(e.target.value)}
                        required
                    />

                    <br></br> <br></br>
                </div>
                <div className="pw1">
                        <label className="labelPass">Password </label>
                        <input className="form_inputThree"
                            name="pwOne"
                            type="password"
                            value={password}
							onChange={(e) => setPassword(e.target.value)}
                            required
                        />

                    </div>
                </div>

                

                    <br></br>

                <br></br>
                

                <div className="button">
                 <button onClick={handleSubmit} type="submit">Complete Profile</button>

                </div>

                


            </div>

        </>
    );
};

export default UpdateUser;