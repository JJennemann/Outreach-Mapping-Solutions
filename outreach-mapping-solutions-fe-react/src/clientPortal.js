import { ClientBaseForm } from "./clientBaseForm/clientBaseForm";
import { ClientSearchResults } from "./clientSearchResults/clientSearchResults";

export function ClientPortal() {
  return (
    <div className="client-portal-container">
      <ClientBaseForm />
      <ClientSearchResults />
    </div>
  );
}
