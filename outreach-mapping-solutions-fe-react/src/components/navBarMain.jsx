import styles from "./navBarMain.module.css";
import { NavLink } from "react-router-dom";

export default function NavBarMain() {
  return (
    <nav className={styles.navBarMain}>
      <ul>
        <li>
          <NavLink to="/client-portal">Client Portal</NavLink>
        </li>
        <li>
          <NavLink to="/report-portal">Report Portal</NavLink>
        </li>
        <li>
          <NavLink to="/map-portal">Map Portal</NavLink>
        </li>
        <li>
          <NavLink to="/log-in">Login</NavLink>
        </li>
      </ul>
    </nav>
  );
}
