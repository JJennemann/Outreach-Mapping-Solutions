import axios from "axios";

import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import ClientProfileNavBar from "../components/clientProfileNavBar";
import ClientInformation from "../components/clientInformation";
import ClientMap from "../components/clientMap";
import ClientCaseNotes from "../components/clientCaseNotes";
import ClientAssessments from "../components/clientAssessments";

export default function ClientProfile() {
  const [searchParams, setSearchParams] = useSearchParams();
  const [clientData, setClientData] = useState({});
  const [navSelection, setNavSelection] = useState("");

  const clientId = searchParams.get("id");

  useEffect(
    function () {
      async function fetchClient() {
        try {
          const response = await axios.get(
            `http://localhost:8080/clientBase/return/${clientId}`,
            {
              headers: {
                "Content-Type": "application/json",
              },
            }
          );
          console.log(response.data);
          setClientData(response.data);
        } catch (error) {
          console.log("Error:", error);
        }
      }
      fetchClient();
    },
    [clientId]
  );

  function handleNavSelection(selection) {
    setNavSelection(selection);
  }

  const renderProfileSection = () => {
    switch (navSelection) {
      case "overview":
        return <ClientInformation />;
      case "map":
        return <ClientMap />;
      case "case notes":
        return <ClientCaseNotes />;
      case "assessments":
        return <ClientAssessments />;
      default:
        return <ClientInformation />;
    }
  };

  return (
    <div>
      <ClientProfileNavBar
        onNavSelection={handleNavSelection}
        navSelection={navSelection}
      />
      {renderProfileSection()}
      <p>Client Profile {clientId}</p>
    </div>
  );
}
