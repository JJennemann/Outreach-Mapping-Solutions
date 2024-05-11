import ClientBaseRecord from "./clientInformationComponents/clientBaseRecord";
import ClientDemographics from "./clientInformationComponents/clientDemographics";
import styles from "./clientInformation.module.css";

export default function ClientInformation({ clientData }) {
  // console.log(clientData.clientDemographicsDTO);
  return (
    <div>
      <ClientBaseRecord clientData={clientData} />
      <ClientDemographics clientData={clientData} />
    </div>
  );
}
