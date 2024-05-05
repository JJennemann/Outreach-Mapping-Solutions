import { useState } from "react";
import "./navBarMain.css";
import { NavLink } from "react-router-dom";

export function NavBarMain() {
  const [selected, setSelected] = useState(null);

  function handleSelectNavLink() {}

  return (
    <nav className="nav-bar-main">
      <ul className="nav-bar-links">
        <li>
          <NavLink
            to="/client-portal"
            className={({ isActive }) =>
              isActive ? "nav-bar-link active" : "nav-bar-link"
            }
          >
            Client Portal
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/report-portal"
            className={({ isActive }) =>
              isActive ? "nav-bar-link active" : "nav-bar-link"
            }
          >
            Report Portal
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/map-portal"
            className={({ isActive }) =>
              isActive ? "nav-bar-link active" : "nav-bar-link"
            }
          >
            Map Portal
          </NavLink>
        </li>
        <li>
          <NavLink
            to="/log-in"
            className={({ isActive }) =>
              isActive ? "nav-bar-link active" : "nav-bar-link"
            }
          >
            Client Portal
          </NavLink>
        </li>
      </ul>
    </nav>
  );
}
