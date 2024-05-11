import axios from "axios";

import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import ClientProfileNavBar from "../components/clientProfileComponents/clientProfileNavBar";
import ClientInformation from "../components/clientProfileComponents/clientInformation";
import ClientMap from "../components/clientProfileComponents/clientMap";
import ClientCaseNotes from "../components/clientProfileComponents/clientCaseNotes";
import ClientAssessments from "../components/clientProfileComponents/clientAssessments";

export default function ClientProfile() {
  const [searchParams, setSearchParams] = useSearchParams();
  const [clientData, setClientData] = useState({});
  const [navSelection, setNavSelection] = useState("client information");

  const clientId = searchParams.get("clientId");

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
        return <ClientInformation clientData={clientData} />;
      case "map":
        return <ClientMap clientData={clientData} />;
      case "case notes":
        return <ClientCaseNotes clientData={clientData} />;
      case "assessments":
        return <ClientAssessments clientData={clientData} />;
      default:
        return <ClientInformation clientData={clientData} />;
    }
  };

  return (
    <div>
      <ClientProfileNavBar
        onNavSelection={handleNavSelection}
        navSelection={navSelection}
      />
      {renderProfileSection()}
    </div>
  );
}
