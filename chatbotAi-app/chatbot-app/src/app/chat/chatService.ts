// chat.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChatService {
  private apiUrl = 'http://localhost:8080/api/chat/ask';

  constructor(private http: HttpClient) {}

  askQuestion(question: string): Observable<string> {
    return this.http.post<string>(this.apiUrl, question);
  }
}
