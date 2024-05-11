import { useParams } from "react-router-dom";

export default function ClientProfile() {
  const { id } = useParams();

  return (
    <div>
      <p>Client Profile {id}</p>
    </div>
  );
}
