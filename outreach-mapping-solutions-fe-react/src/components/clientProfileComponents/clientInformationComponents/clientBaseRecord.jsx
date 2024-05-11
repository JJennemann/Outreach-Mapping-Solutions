import styles from "./clientBaseRecord.module.css";

export default function ClientBaseRecord({ clientData }) {
  const {
    displayName,
    displayDob,
    displaySsn,
    nameDataQuality,
    dobDataQuality,
    ssnDataQuality,
  } = clientData;

  return (
    <div className={styles.clientBaseRecord}>
      <h3>Client Record</h3>
      <table>
        <tr>
          <th>Name</th>
          <td>{displayName}</td>
        </tr>
        <tr>
          <th>Name Data Quality</th>
          <td>{nameDataQuality}</td>
        </tr>
        <tr>
          <th>Date of Birth</th>
          <td>{displayDob}</td>
        </tr>
        <tr>
          <th>Date of Birth Data Quality</th>
          <td>{dobDataQuality}</td>
        </tr>
        <tr>
          <th>Social Security Number</th>
          <td>{displaySsn}</td>
        </tr>
        <tr>
          <th>Social Security Number Data Quality</th>
          <td>{ssnDataQuality}</td>
        </tr>
      </table>
    </div>
  );
}
