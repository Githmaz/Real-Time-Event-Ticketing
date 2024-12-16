import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [], 
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  profilePreview !: string ; 
  uploadedFile: File | null = null;
  constructor(){
    this.setDefalutProfileImage()
  }



  // Handle file selection and update the preview
  onFileSelected(event: any): void {
    const file = event.target.files[0]; // Get the selected file    
    if (file) {
      this.uploadedFile = file; // Store the file for future use (e.g., form submission)
      const reader = new FileReader(); // Create a FileReader to read the file
      reader.onload = () => {
        this.profilePreview = reader.result as string; // Set the preview to the base64 result
      };
      reader.readAsDataURL(file); // Read the file as a DataURL
    }
  }

  // Trigger the hidden file input
  triggerFileUpload(input: HTMLInputElement): void {
    input.click();
  }

  setDefalutProfileImage():void{
    this.profilePreview = 'assets/img/defaultProfileImg.jpg';
    this.uploadedFile = null;
  }

}
