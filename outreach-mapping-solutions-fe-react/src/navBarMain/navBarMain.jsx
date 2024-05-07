import "./navBarMain.css";
import { NavLink } from "react-router-dom";

export function NavBarMain() {
  return (
    <nav className="nav-bar-main">
      <ul className="nav-bar-links">
        <NavLink
          to="/client-portal"
          className={({ isActive }) =>
            isActive ? "nav-bar-link active" : "nav-bar-link"
          }
        >
          Client Portal
        </NavLink>

        <NavLink
          to="/report-portal"
          className={({ isActive }) =>
            isActive ? "nav-bar-link active" : "nav-bar-link"
          }
        >
          Report Portal
        </NavLink>

        <NavLink
          to="/map-portal"
          className={({ isActive }) =>
            isActive ? "nav-bar-link active" : "nav-bar-link"
          }
        >
          Map Portal
        </NavLink>

        <NavLink
          to="/log-in"
          className={({ isActive }) =>
            isActive ? "nav-bar-link active" : "nav-bar-link"
          }
        >
          Login
        </NavLink>
      </ul>
    </nav>
  );
}
