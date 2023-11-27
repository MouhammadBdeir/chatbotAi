import { Component } from '@angular/core';
import { ChatService } from './chatService';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
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
