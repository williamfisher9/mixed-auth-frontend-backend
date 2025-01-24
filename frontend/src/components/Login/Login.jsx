import { useState } from "react";
import image from "../../assets/logo.png";
import "./Login.css";
import { Link } from "react-router";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleLoginRequest = () => {
    axios.post("http://localhost:8080/api/v1/users/login")
    .then((res) => {
      console.log(res)
    })
    .catch((err) => {
      console.log(err)
    })
  }

  return (
    <div className="login-container">
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
          type="password"
          placeholder="password"
          className="form-field-input"
          name="password"
          id="password"
          onChange={() => setPassword(event.target.value)}
        />
      </div>

      <div className="form-button">
        <input type="button" value="LOGIN" className="btn" onClick={handleLoginRequest}/>
      </div>

      <div className="horizontal-split-line">
        <hr />
        <div className="horizontal-split-line-text">OR</div>
      </div>

      <div className="form-button-group">
        <div className="btn-group">
          <i className="icon fa-brands fa-github"></i> Login with Github
        </div>
      </div>

      <div className="form-button-group">
        <div className="btn-group">
          <i className="icon fa-brands fa-facebook"></i> Login with Facebook
        </div>
      </div>
    </div>
    
  );
}

export default Login;
