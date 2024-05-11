import { useEffect } from "react";
import styles from "./clientDemographics.module.css";

export default function ClientDemographics({ clientData }) {
  //   console.log(clientData);

  //   //   useEffect(
  //   //     function () {
  //   //       async function destructureDemographics() {
  //   //         const { gender, ethnicity, racePrimary, raceSecondary, veteranStatus } =
  //   //           clientDemographicData;
  //   //       }
  //   //       destructureDemographics();
  //   //     },
  //   //     [clientDemographicData]
  //   //   );

  return (
    <div className={styles.clientDemographics}>
      <h3>Client Demographics</h3>
      <table>
        <tr>
          <th>Gender</th>
          {/* <td>{gender}</td> */}
        </tr>
      </table>
    </div>
  );
}
