import './App.css';
import React from 'react';

import Login from './Screens/Login';


import { BrowserRouter, Route, Routes } from "react-router-dom";
import Admin from './Screens/Admin';
import AddUser from './Screens/AddUser';
import UpdateUser from './Screens/UpdateUser';
import AddNote from './Screens/AddNote';
import Student from './Screens/Student';

function App() {
  return (
    
    <div className="App">
     

      <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<Login/>} />
          <Route path="/admin" element={<Admin/>} />
          <Route path="/addUser" element={<AddUser/>} />
          <Route path="/profile" element={<UpdateUser/>} />
          <Route path="/addNote" element={<AddNote/>} />
          <Route path="/student" element={<Student/>} />




        </Routes>
      </BrowserRouter>
 
    </div>

    
  );
}

export default App;
