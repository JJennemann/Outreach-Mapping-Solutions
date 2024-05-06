export interface ClientBaseFormData {
  firstName: string;
  middleName: string;
  lastName: string;
  nameDataQuality: string;
  dobMonth: string;
  dobDay: number;
  dobYear: number;
  dobDataQuality: string;
  firstThreeSsn: number;
  middleTwoSsn: number;
  lastFourSsn: number;
  ssnDataQuality: string;
}

export const initialClientBaseFormData: ClientBaseFormData = {
  firstName: "",
  middleName: "",
  lastName: "",
  nameDataQuality: "",
  dobMonth: "",
  dobDay: 0,
  dobYear: 0,
  dobDataQuality: "",
  firstThreeSsn: 0,
  middleTwoSsn: 0,
  lastFourSsn: 0,
  ssnDataQuality: "",
};
