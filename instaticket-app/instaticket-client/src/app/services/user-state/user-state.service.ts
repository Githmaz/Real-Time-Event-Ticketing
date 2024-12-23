import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from '../../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class UserStateService {
  private readonly guestUser: User = {
    name: 'Guest',
    email: '',
    userId: 'G-1',
    username: 'Guest',
    password: '',
    userRole: 'GUEST'
  };

  private readonly userSubject = new BehaviorSubject<User>(this.guestUser); 
  user$ = this.userSubject.asObservable(); 

  setUser(user: User): void {
    this.userSubject.next(user);
  }

  getUser(): User {
    return this.userSubject.getValue();
  }

  resetToGuest(): void {
    this.setUser(this.guestUser);
  }
}
