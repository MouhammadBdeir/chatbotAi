// chat.component.ts
import { Component } from '@angular/core';
import { ChatService } from '../chat-service/chat-service.component';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  
})
export class ChatComponent {
  messages: string[] = [];
  inputMessage: string = '';

  constructor(private chatService: ChatService) {}

  sendMessage() {
    this.messages.push(`User: ${this.inputMessage}`);
    this.chatService.askQuestion(this.inputMessage).subscribe(response => {
      this.messages.push(`ChatGPT: ${response}`);
    });
    this.inputMessage = '';
  }
}
