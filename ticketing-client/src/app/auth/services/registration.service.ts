import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root', // Provided at the root level
})
export class RegistrationService {
  private defaultProfileImg = 'assets/img/defaultProfileImg.jpg'; // Default profile image
  private apiEndpoint = 'https://your-backend-api.com/register'; // Replace with your backend API

  constructor(private http: HttpClient) {}

  // Get the default profile image
  getDefaultProfileImg(): string {
    return this.defaultProfileImg;
  }

  // Upload a profile picture (if backend requires it separately)
  uploadProfilePicture(file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);

    // Replace with the actual upload endpoint
    return this.http.post('https://your-backend-api.com/upload-profile', formData);
  }

  // Submit the registration form data
  registerUser(formData: any, profilePic: File | string): Observable<any> {
    const finalData = { ...formData, profilePic };

    // Send the data to the backend
    return this.http.post(this.apiEndpoint, finalData);
  }
}
