export default function ClientBaseRecord({ clientData }) {
  return (
    <>
      <p>Name:</p> <span>{clientData.firstName}</span>
    </>
  );
}
