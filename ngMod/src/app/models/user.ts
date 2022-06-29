export class User {
  id: number | null;
  username: string | null;
  password: string | null;
  email: string | null;
  dateCreated: String | null;
  role: string | null;
  enabled: number | null;


  constructor(
    id: number | null = 0,
    username: string | null = '',
    password: string | null = '',
    email: string | null='',
    role: string | null = '',
    dateCreated: String | null= "",
    enabled: number | null = 0
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.dateCreated = dateCreated;
    this.enabled = enabled;
  }
}
