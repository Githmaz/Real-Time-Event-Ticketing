import { Component, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { User } from '../../models/user.model';

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

    user: User = {
      userId: '',
      username: '',
      password: '',
      email: '',
      name: '',
      userRole: 'GUEST', 
    }

  constructor(){
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

  onSubmit(): void {
      console.log('User object ready:', this.user);
  }

 
}
