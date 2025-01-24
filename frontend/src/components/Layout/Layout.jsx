import { useState } from "react";
import "./Layout.css";
import { Link, Outlet } from "react-router";

function Layout() {
  const [activeLink, setActiveLink] = useState("login");

  return (
    <div className="forms-container">
      <div className="tabs-group">
        <Link
          className={
            activeLink == "login"
              ? "tab-item login-tab-item active-link"
              : "tab-item login-tab-item"
          }
          to="/login"
          onClick={() => setActiveLink("login")}
        >
          LOGIN
        </Link>
        <Link
          className={
            activeLink == "register"
              ? "tab-item register-tab-item active-link"
              : "tab-item register-tab-item"
          }
          to="/register"
          onClick={() => setActiveLink("register")}
        >
          REGISTER
        </Link>
      </div>

      <Outlet />
    </div>
  );
}

export default Layout;
