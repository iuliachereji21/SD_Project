import React, { Component, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import NavBarLogIn from './navBarLogIn';
import axios from 'axios'

function Register() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { uname, pass, rep_pass, phone} = document.forms[0];
        axios.post("http://localhost:8080/sd_assignment2/register",{username: uname.value, password: pass.value, repeated_password: rep_pass.value, phone: phone.value})
        .then(response =>{
            console.log(response);
            if(response.data.userId)
                navigate(`/customer/:${response.data.userId}/:${response.data.token}`);
            //this.setState({errorMessage : response.data.message})
        })
        .catch(({ response }) => { 
            setMessage(response.data.message);
        })
      };
    return (
        <div>
                <NavBarLogIn></NavBarLogIn>
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
                        <div className="input-container">
                            <label>Repeated password </label>
                            <input type="password" name="rep_pass" required />
                        
                        </div>
                        <div className="input-container">
                            <label>Phone </label>
                            <input type="text" name="phone"/>
                        
                        </div>
                        <div>
                            <label>{errorMessage} </label>
                        </div>
                        <div className="button-container">
                            <input type="submit" value="register" />
                        </div>
                    </form>

                </div>
            </div>
      );
}
 
export default Register;