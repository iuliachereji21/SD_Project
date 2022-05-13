import React, { Component, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'

function LogIn(){
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { uname, pass} = document.forms[0];
        axios.post("http://localhost:8080/sd_assignment2/login",{username: uname.value, password: pass.value})
        .then(response =>{
            if(response.data.customer)
                navigate(`/customer/:${response.data.userId}/:${response.data.token}`,{state : "HELOOOO"});
            else
                navigate(`/admin/:${response.data.userId}/:${response.data.token}`);
            
        })
        .catch(({ response }) => { 
            setMessage(response.data.message);
        })
      };
    return (
        <div className="form">
        <form onSubmit= {handleSubmit}>
            <div className="input-container">
                <label>Username </label>
                <input type="text" name="uname" required />
                
            </div>
            <div className="input-container">
                <label>Password </label>
                <input type="password" name="pass" required />
            
            </div>
            <div>
                <label>{errorMessage} </label>
            </div>
            <div className="button-container">
                <Link to="/register">Register</Link>
                <input type="submit" />
            </div>
        </form>
        </div>
    );
    
}
 
export default LogIn;