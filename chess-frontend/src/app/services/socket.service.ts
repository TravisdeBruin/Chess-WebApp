import { Injectable } from '@angular/core';
import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

@Injectable({
  providedIn: 'root',
})
export class SocketService {
  constructor() {}
  webSocketEndPoint: string = 'http://localhost:8080/game/';
  topic: string = '/topic/game-progress/';
  stompClient: any;
  _connect() {
    console.log('Initialize WebSocket Connection');
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect(
      {},
      function (frame: any) {
        _this.stompClient.subscribe(_this.topic, function (sdkEvent: any) {
          _this.onMessageReceived(sdkEvent);
        });
        //_this.stompClient.reconnect_delay = 2000;
      },
      (error: string) => {
        console.log('errorCallBack -> ' + error);
        setTimeout(this._connect, 5000);
      }
    );
  }

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  _send(message: any) {
    console.log('calling logout api via web socket');
    const data = this.stompClient.send(
      '/game/create',
      {},
      JSON.stringify(message)
    );
    console.log(data);
  }

  onMessageReceived(message: string) {
    console.log('Message Recieved from Server :: ' + message);
  }
}
