import { Link } from "react-router";
import image from "../../assets/logo.png";
import "./Register.css";
import { useState } from "react";
import axios from "axios";

function Register() {
  const [username, setUsername] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [password, setPassword] = useState('');

  const handleRegisterRequest = () => {
    axios.post("http://localhost:8080/api/v1/users/register", {username, firstName, lastName, password})
    .then((res) => {
      console.log(res)
    }).catch((err) => {
      console.log(err)
    })
  }

  return (
    <div className="register-container">
      <div className="logo-container">
        <img src={image} alt="logo" height="80px" />
      </div>

      <div className="form-field">
        <input
          type="text"
          placeholder="username"
          className="form-field-input"
          name="username"
          id="username"
          onChange={() => setUsername(event.target.value)}
        />
      </div>

      <div className="form-field">
        <input
          type="text"
          placeholder="first name"
          className="form-field-input"
          name="firstName"
          id="firstName"
          onChange={() => setFirstName(event.target.value)}
        />
      </div>

      <div className="form-field">
        <input
          type="text"
          placeholder="last name"
          className="form-field-input"
          name="lastName"
          id="lastName"
          onChange={() => setLastName(event.target.value)}
        />
      </div>

      <div className="form-field">
        <input
          type="password"
          placeholder="password"
          className="form-field-input"
          name="password"
          id="password"
          onChange={() => setPassword(Event.target.value)}
        />
      </div>

      <div className="form-button">
        <input type="button" value="REGISTER" className="btn" onClick={handleRegisterRequest}/>
      </div>

    </div>
  );
}

export default Register;
