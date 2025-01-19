import { Component, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user.model';
import { AlertService } from '../../services/alert-service/alert.service';
import { AuthService } from '../services/auth-service.service';

interface Payload {
  user: User;
}

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule,FormsModule], 
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  profilePreview !: string ; 
  uploadedFile: File | null = null;
  usernameAvailable: boolean | null = null
  emailAvailable: boolean | null = null; 

  user: User = {
      userId: '',
      username: '',
      password: '',
      email: '',
      name: '',
      userRole: 'GUEST', 
    }

  constructor( 
    private readonly authService: AuthService,
    private readonly alertService: AlertService 
    ){
    this.setDefalutProfileImage()
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];     
    if (file) {
      this.uploadedFile = file; 
      const reader = new FileReader(); 
      reader.onload = () => {
        this.profilePreview = reader.result as string; 
      };
      reader.readAsDataURL(file); 
    }
  }

  triggerFileUpload(input: HTMLInputElement): void {
    input.click();
  }

  setDefalutProfileImage():void{
    this.profilePreview = 'assets/img/defaultProfileImg.jpg';
    this.uploadedFile = null;
  }

  checkValidation(): boolean {
    try {
      if (!this.user.name.trim()) {
        throw new Error('Full Name cannot be empty.');
      }
      if (!this.user.username.trim()) {
        throw new Error('Username cannot be empty.');
      }
      if (!this.user.password.trim()) {
        throw new Error('Password cannot be empty.');
      }
      if (!this.user.email.trim()) {
        throw new Error('Email cannot be empty.');
      }
      if (!this.user.userRole || this.user.userRole === 'GUEST') {
        throw new Error('Please select a valid role.');
      }
      return true;
    } catch (error) {
      if (error instanceof Error) {
        this.alertService.showAlert('error', error.message, 5000);
      }
      return false;
    }
  }
  

   // Check if username is available
  checkUserName(): void {
    console.log(this.user.username);
    if (this.user.username.trim() !== '') {
      this.authService.checkUsername(this.user.username).subscribe(
        (isAvailable) => {
          this.usernameAvailable = isAvailable;
          if (!isAvailable) {
            this.alertService.showAlert(
              'error',
              'Username is already taken. Please choose another one.',
              5000
            );
          }
        },
        (error) => {
          console.log(error)
          this.alertService.showAlert('error', 'Error checking username availability. Please try again.', 5000);
        }
      );
    }
  }

  // Check if email is available
  checkEmail(): void {
    if (this.user.email.trim() !== '') {
      this.authService.checkEmail(this.user.email).subscribe(
        (isAvailable) => {
          this.emailAvailable = isAvailable;
          if (!isAvailable) {
            this.alertService.showAlert('error', 'Email is already in use. Please use a different email.', 5000);
          }
        },
        (error) => {
          this.alertService.showAlert('error', 'Error checking email availability. Please try again.', 5000);
          console.error('Error checking email:', error.message);
        }
      );
    }
  }

  async onSubmit(): Promise<void> {

    const x: Payload = {
      user: {
        userId : "",
        username: "jodoe",
        password: "SecureP@ssw0rd",
        email: "jonwdoe@example.com",
        name: "John Doe",
        userRole: 'CUSTOMER'
      }
    };
    
    if (this.checkValidation()) {
      try {
        const isRegistered = await this.authService.register(x).toPromise();
        if (isRegistered) {
          this.alertService.showAlert('success', 'Registration successful!', 5000);
        }
      } catch (error) {
        console.error('Error during registration:', error);
        this.alertService.showAlert('error', 'Registration failed. Please try again.', 5000);
      }
    }
  }
  

}
