import { UserRole } from './user-role.model';

export interface User {
  userId: string;
  username: string;
  password: string;
  email: string;
  name: string;
  userRole: UserRole;
}