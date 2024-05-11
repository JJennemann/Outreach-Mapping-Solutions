import axios from "axios";

import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";

export default function ClientProfile() {
  const [searchParams, setSearchParams] = useSearchParams();
  const [clientData, setClientData] = useState({});
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

  return (
    <div>
      <p>Client Profile {clientId}</p>
    </div>
  );
}
