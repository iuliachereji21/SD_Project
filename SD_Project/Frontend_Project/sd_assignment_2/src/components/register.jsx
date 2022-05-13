import React, { Component, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios'

function Register() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");

    let handleSubmit = (event) => {
        // Prevent page reload
        event.preventDefault();
        var { name, email, pass, rep_pass, phone, occupation} = document.forms[0];
        axios.post("http://localhost:8080/sd_project/register",
            {name: name.value, email: email.value, password: pass.value, repeatedPassword: rep_pass.value, phone: phone.value, occupation: occupation.value})
        .then(response =>{
            navigate(`/products`,{state : {token: response.data.token}});
            //this.setState({errorMessage : response.data.message})
        })
        .catch(({ response }) => { 
            setMessage(response.data.message);
        })
      };
    return (
        <div>
            <Link to='/'>Home </Link>
            <Link to='/login'>Log in </Link>
                <div className="form">
                    <form onSubmit= {handleSubmit}>
                        <div className="input-container">
                            <label>Name </label>
                            <input type="text" name="name" required />
                            
                        </div>
                        <div className="input-container">
                            <label>Email </label>
                            <input type="text" name="email" required />
                            
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
                        <div className="input-container">
                            <label>Occupation </label>
                            <input type="text" name="occupation"/>
                        
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