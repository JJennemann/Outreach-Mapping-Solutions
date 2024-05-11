import styles from "./clientProfileNavBar.module.css";

export default function ClientProfileNavBar({ onNavSelection, navSelection }) {
  return (
    <nav className={styles.clientProfileNavBar}>
      <ul>
        <li
          style={{
            backgroundColor:
              navSelection === "client information" ? "darkGray" : "",
          }}
          onClick={() => onNavSelection("client information")}
        >
          Client Information
        </li>
        <li
          style={{
            backgroundColor: navSelection === "map" ? "darkGray" : "",
          }}
          onClick={() => onNavSelection("map")}
        >
          Map
        </li>
        <li
          style={{
            backgroundColor: navSelection === "case notes" ? "darkGray" : "",
          }}
          onClick={() => onNavSelection("case notes")}
        >
          Case Notes
        </li>
        <li
          style={{
            backgroundColor: navSelection === "assessments" ? "darkGray" : "",
          }}
          onClick={() => onNavSelection("assessments")}
        >
          Assessments
        </li>
      </ul>
    </nav>
  );
}
