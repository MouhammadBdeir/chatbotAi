import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChatComponent } from './chat/chat.component';
import { ChatService } from './chat-service/chat-service.component'; 

@NgModule({
  declarations: [
    AppComponent,
    ChatComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [ChatService], 
  bootstrap: [AppComponent]
})
export class AppModule { }
