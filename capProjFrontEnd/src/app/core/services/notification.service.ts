import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private messageService: MessageService) { }

  successMsg(msg:string){
    this.messageService.add({ severity: 'success', summary: 'Success', detail: msg });
  }
}
